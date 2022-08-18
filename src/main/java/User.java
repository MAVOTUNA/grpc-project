import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class User {

    public static void main(String[] args) throws IOException {

        //서버연결
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost" , 50051)
                .usePlaintext().build();
        SignUpServiceGrpc.SignUpServiceBlockingStub stub =
                SignUpServiceGrpc.newBlockingStub(channel);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        //User가 사용하는 TUI 초기
        while (true) {
            try {
                //검증을 위한 테이블의 CourseNum , StudentNum을 가져온다.
                List<String> courseNumList = getCourseNumList(stub);
                List<String> studentNumList = getStudentNumList(stub);
                printMenu();
                String choice = bufferedReader.readLine().trim();
                switch (choice) {
                    case "1" :
                        ShowCourseList(stub);
                        break;
                    case "2" :
                        ShowStudentList(stub);
                        break;
                    case "3" :
                        AddCourse(stub ,bufferedReader , courseNumList);
                        break;
                    case "4" :
                        DeleteCourse(stub ,bufferedReader , courseNumList);
                        break;
                    case "5" :
                        AddStudent(stub , bufferedReader , studentNumList);
                        break;
                    case "6" :
                        DeleteStudent(stub , bufferedReader , studentNumList);
                        break;
                    case "7" :
                        SignUp(stub ,bufferedReader , studentNumList ,courseNumList);
                        break;
                    case "99":
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @NotNull
    private static List<String> getStudentNumList(SignUpServiceGrpc.SignUpServiceBlockingStub stub) {
        Signup.getAllStudResponse getAllStudListResponse
                = stub.getAllStudents(Signup.getAllStudRequest.newBuilder().setIdentifier(4).build());
        List<String> studentNumList = getAllStudListResponse.getStudentNumList();
        return studentNumList;
    }

    @NotNull
    private static List<String> getCourseNumList(SignUpServiceGrpc.SignUpServiceBlockingStub stub) {
        Signup.getAllCourseResponse getAllCourseListResponse
                = stub.getAllCourses(Signup.getAllCourseRequest.newBuilder().setIdentifier(4).build());
        List<String> courseNumList = getAllCourseListResponse.getCourseNumList();
        return courseNumList;
    }

    private static void ShowStudentList(SignUpServiceGrpc.SignUpServiceBlockingStub stub) {
        Signup.getAllStudResponse getAllStudResponse =
                stub.getAllStudents(Signup.getAllStudRequest.newBuilder().setIdentifier(1).build());
        showList(getAllStudResponse.getStudentList());
    }

    private static void ShowCourseList(SignUpServiceGrpc.SignUpServiceBlockingStub stub) {
        Signup.getAllCourseResponse getAllCourseResponse
                = stub.getAllCourses(Signup.getAllCourseRequest.newBuilder().setIdentifier(1).build());
        showList(getAllCourseResponse.getCourseList());
    }

    private static void SignUp(SignUpServiceGrpc.SignUpServiceBlockingStub stub, BufferedReader bufferedReader, List<String> studentNumList, List<String> courseNumList) throws IOException {
        System.out.print("학번 입력 : ");
        String studentNum = bufferedReader.readLine().trim();
        studentNum = TotalVaildStudentNum(bufferedReader, studentNumList, studentNum);

        System.out.print("강의번호 입력 : ");
        String courseNum = bufferedReader.readLine().trim();
        courseNum = TotalValidCourseNum(bufferedReader, courseNumList, courseNum);

        System.out.println("수강신청을 하시겠습니까 ? Y/N");
        String choice = bufferedReader.readLine().trim();
        switch (choice){
            case "Y" :

                Signup.getAllCourseResponse studentList_Course =
                        stub.getAllCourses(Signup.getAllCourseRequest.newBuilder().setIdentifier(5).setCourseNum(courseNum).build());

                Signup.getAllStudResponse classList_Student =
                        stub.getAllStudents(Signup.getAllStudRequest.newBuilder().setIdentifier(5).setStudentNum(studentNum).build());

                String studentListString = studentList_Course.getStudentList();
                String classListString = classList_Student.getClassList();

                StringTokenizer stringTokenizerCourse = new StringTokenizer(studentListString);
                StringTokenizer stringTokenizerStudent = new StringTokenizer(classListString);

                ArrayList<String> studentList = new ArrayList<>();
                ArrayList<String> classList = new ArrayList<>();

                while(stringTokenizerStudent.hasMoreTokens()){
                    classList.add(stringTokenizerStudent.nextToken());
                }
                while (stringTokenizerCourse.hasMoreTokens()){
                    studentList.add(stringTokenizerCourse.nextToken());
                }

                if(!DuplicatedValidationNum(classList , courseNum) &&
                        !DuplicatedValidationNum(studentList , studentNum)) {

                    classListString =classListString+" "+courseNum;
                    studentListString=studentListString+" "+studentNum;

                    Signup.getAllCourseResponse UpdateCourse =
                            stub.getAllCourses(Signup.getAllCourseRequest.newBuilder().setIdentifier(6).setCourseNum(courseNum).
                                    setStudentList(studentListString).build());

                    Signup.getAllStudResponse UpdateStudent =
                            stub.getAllStudents(Signup.getAllStudRequest.newBuilder().setIdentifier(6).setStudentNum(studentNum).
                                    setClassList(classListString).build());

                    if(UpdateCourse.getCheck() ==1  && UpdateStudent.getCheck() ==1) System.out.println("수강신청 되었습니다.");
                    else {
                        System.out.println("오류입니다. 다시 입력 해주세요.");
                        return;
                    }
                }
                else System.out.println("이미 수강신청되어있는 과목입니다. 다시 입력해주세요!");
                break;
            case "N" :
                return;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    private static String TotalValidCourseNum(BufferedReader bufferedReader, List<String> courseNumList, String courseNum) throws IOException {
        while(!DuplicatedValidationNum(courseNumList ,courseNum) || SyntaxValidationNum(courseNum, 1)) {
            System.out.println("존재하지 않는 강의번호 이거나 자릿수(5자리 , 숫자만 가능)를 확인해주세요");
            System.out.print("강의번호 입력 : ");
            courseNum = bufferedReader.readLine().trim();
        }
        return courseNum;
    }

    private static String TotalVaildStudentNum(BufferedReader bufferedReader, List<String> studentNumList, String studentNum) throws IOException {
        while(!DuplicatedValidationNum(studentNumList ,studentNum) || SyntaxValidationNum(studentNum, 3)) {
            System.out.println("존재하지 않는 학번이거나 자릿수(8자리 , 숫자만 가능)를 확인 해주세요 ");
            System.out.print("학번 입력 : ");
            studentNum = bufferedReader.readLine().trim();
        }
        return studentNum;
    }

    private static void DeleteCourse(SignUpServiceGrpc.SignUpServiceBlockingStub stub, BufferedReader courseReader, List<String> courseNumList) throws IOException {
        System.out.print("강의번호 입력 : ");
        String courseNum = courseReader.readLine().trim();
        while(!DuplicatedValidationNum(courseNumList ,courseNum)) {
            System.out.println("존재하지 않는 강의번호입니다. 다시 입력해주세요");
            System.out.print("강의번호 입력: ");
            courseNum = courseReader.readLine().trim();
        }
        Signup.getAllCourseResponse deleteCourseResponse =
                stub.getAllCourses(Signup.getAllCourseRequest.newBuilder().setIdentifier(3).setCourseNum(courseNum).build());
        if(deleteCourseResponse.getCheck() == 1) System.out.println("삭제 완료."); else System.out.println("오류입니다.");
    }

    private static void DeleteStudent(SignUpServiceGrpc.SignUpServiceBlockingStub stub, BufferedReader studentReader, List<String> studentNumList) throws IOException {
        System.out.println("학번 입력 : ");
        String studentNum = studentReader.readLine().trim();
        while(!DuplicatedValidationNum(studentNumList ,studentNum)) {
            System.out.println("존재하지 않는 학번 입니다. 다시 입력해주세요");
            System.out.print("학번 입력: ");
            studentNum = studentReader.readLine().trim();
        }
        Signup.getAllStudResponse deleteStudentResponse =
                stub.getAllStudents(Signup.getAllStudRequest.newBuilder().setIdentifier(3).setStudentNum(studentNum).build());
        if(deleteStudentResponse.getCheck() == 1) System.out.println("삭제 완료."); else System.out.println("오류입니다.");
    }

    private static void AddCourse(SignUpServiceGrpc.SignUpServiceBlockingStub stub, BufferedReader courseReader, List<String> courseNumList) throws IOException {
        System.out.print("강의번호 입력 : ");
        String courseNum = courseReader.readLine().trim();
        while((DuplicatedValidationNum(courseNumList ,courseNum)) || (SyntaxValidationNum(courseNum, 1))) {
            System.out.println("이미 존재하는 강의번호 이거나 자릿수를 확인해주세요 (5자리 , 숫자만 입력가능)");
            System.out.print("강의번호 입력: ");
            courseNum = courseReader.readLine().trim();
        }
        System.out.print("강의명 입력 : ");
        String courseName = courseReader.readLine().trim();
        System.out.print("담당교수 입력: ");
        String professor = courseReader.readLine().trim();
            System.out.print("등록할 학생수 입력: ");
            String StudentNumberString = courseReader.readLine().trim();
            while(SyntaxValidationNum(StudentNumberString, 2)){
                System.out.println("자릿수(최대99까지가능) 혹은 다른 문자를 입력하셨는지 확인해주세요.");
                System.out.print("등록할 학생수 입력: ");
                StudentNumberString = courseReader.readLine().trim();
            }
                String studentList = "";
                int StudentNumber = Integer.parseInt(StudentNumberString);
                List<String> studNumList = new ArrayList<>();
                for (int i = 0; i < StudentNumber; i++) {
                    System.out.print("학번 입력: ");
                    String studentNum = courseReader.readLine().trim();
                    studNumList.add(studentNum);
                    studentList = studentList+studentNum+" ";
                }
        while (!DuplicatedValidationClassAndStud_Num(studNumList , StudentNumber)) {
            System.out.println("중복된 학번을 입력하셨습니다. 처음부터 다시 입력해주세요!");
            for (int i = 0; i < StudentNumber; i++) {
                System.out.print("학번 입력: ");
                String studentNum = courseReader.readLine().trim();
                studNumList.add(studentNum);
                studentList = studentList+studentNum+" ";
            }
        }

        System.out.println("저장 : 0 , 취소 : 9");
            String Choice = courseReader.readLine().trim();
            switch (Choice) {
                case "0" :
                    Signup.getAllCourseResponse addCourseResponse =
                            stub.getAllCourses(Signup.getAllCourseRequest.newBuilder().setIdentifier(2).setCourseNum(courseNum)
                                    .setCourseName(courseName).setProfessor(professor).setStudentList(studentList).build());
                    if (addCourseResponse.getCheck() == 1) System.out.println("저장완료."); else System.out.println("오류입니다.");
                case "9" :
                    return;
            }
    }

    private static void AddStudent(SignUpServiceGrpc.SignUpServiceBlockingStub stub, BufferedReader studentReader, List<String> studentNumList) throws IOException {
        System.out.print("학번 : ");
        String studentNum = studentReader.readLine().trim();
        while(DuplicatedValidationNum(studentNumList ,studentNum) || SyntaxValidationNum(studentNum, 3)) {
            System.out.println("이미 존재하는 학번이거나 자릿수(8자리)를 확인해주세요");
            System.out.print("학번 : ");
            studentNum = studentReader.readLine().trim();
        }
        System.out.print("이름: ");
        String studentName = studentReader.readLine().trim();
        System.out.print("전공 : ");
        String major = studentReader.readLine().trim();
        System.out.print("수강 목록 갯수 : ");
        String classNumberString = studentReader.readLine().trim();
        while(SyntaxValidationNum(classNumberString, 2)){
            System.out.print("수강 목록 갯수 : ");
            classNumberString = studentReader.readLine().trim();
        }
        try{
            int classNumber =  Integer.parseInt(classNumberString);
            String classList = "";
            List<String> classNumList = new ArrayList<>();
            for (int i = 0; i < classNumber; i++) {
                System.out.print("강의 번호 입력 : ");
                String courseNum= studentReader.readLine().trim();
                classNumList.add(courseNum);
                classList = classList+courseNum+" ";
            }
            while (!DuplicatedValidationClassAndStud_Num(classNumList, classNumber)) {
                System.out.println("중복된 강의번호를 입력하셨습니다. 처음부터 다시 입력해주세요!");
                for (int i = 0; i < classNumber; i++) {
                    System.out.print("강의 번호  입력: ");
                    String courseNum = studentReader.readLine().trim();
                    classNumList.add(courseNum);
                    classList = classList+courseNum+" ";
                }
            }
            System.out.println("저장 : 0 , 취소 : 9");
            String Choice = studentReader.readLine().trim();
            switch (Choice) {
                case "0" :
                    Signup.getAllStudResponse addStudentResponse =
                            stub.getAllStudents(Signup.getAllStudRequest.newBuilder().setIdentifier(2).
                                    setStudentNum(studentNum).setStudentName(studentName).setMajor(major)
                                    .setClassList(classList).build());
                    if (addStudentResponse.getCheck() == 1) System.out.println("저장완료."); else System.out.println("오류입니다.");
                    break;
                case "9":
                    return;
            }
        }catch (Exception e) {

        }
    }

    private static void printMenu() {

        System.out.println("------- 수강 시스템 -------");
        System.out.println("1. 강의 목록");
        System.out.println("2. 학생 목록");
        System.out.println("3. 강의 추가");
        System.out.println("4. 강의 삭제");
        System.out.println("5. 학생 추가");
        System.out.println("6. 학생 삭제");
        System.out.println("7. 수강 신청");
        System.out.println("99. 나가기");

    }

    public static void showList(List<?> dataList) {
        String list = "";

        for (int i = 0; i< dataList.size(); i++) {
            list += dataList.get(i) +"\n";
        }
        System.out.println(list);
    }

    private static boolean DuplicatedValidationClassAndStud_Num(List<String> validationList, int inputNum) {
        if(validationList.stream().distinct().count() == inputNum) return true;
        else return false;
    }

    private static boolean DuplicatedValidationNum(List<String> courseNumList , String courseNum) {
        for (String courseNumCheck : courseNumList) {
            if (courseNum.equals(courseNumCheck)) return true;
        }
        return false;
    }

    private static boolean SyntaxValidationNum(String courseNum, int num) {
        boolean output = false;
        if(num ==1 && courseNum.length() ==5 || num==2 && courseNum.length()==1 || num ==3 && courseNum.length() == 8) {
            for (int i = 0; i < courseNum.length(); i++) {
                char temp = courseNum.charAt(i);
                if (Character.isDigit(temp) == false) {
                    output = true;
                }
            }
            return output;
        }else return true;
    }
}
