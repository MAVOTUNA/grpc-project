import dao.SignUpDao;
import io.grpc.stub.StreamObserver;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import session.SessionFactory;
import vo.CourseVO;
import vo.StudentVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignUpServerImpl extends SignUpServiceGrpc.SignUpServiceImplBase {

    @Override
    public void getAllStudents(Signup.getAllStudRequest request, StreamObserver<Signup.getAllStudResponse> responseObserver) {
        try {
            int identifier = request.getIdentifier();
            switch (identifier) {
                case 1:
                    ArrayList<Signup.Student> studentResponseList = new ArrayList<>();
                    List<StudentVO> studentList = getSignUpDao().StudentList();

                    for (StudentVO studentVO : studentList) {
                        Signup.Student getAllStudResponse = Signup.Student.newBuilder()
                                .setStudentNum(studentVO.getStudentNum()).setStudentName(studentVO.getStudentName())
                                .setMajor(studentVO.getMajor()).setClassList(studentVO.getClassList()).build();
                        studentResponseList.add(getAllStudResponse);
                    }

                    Signup.getAllStudResponse studentListResponse =
                            Signup.getAllStudResponse.newBuilder().addAllStudent(studentResponseList).build();

                    responseObserver.onNext(studentListResponse);
                    responseObserver.onCompleted();
                    break;
                case 2:
                    Signup.getAllStudResponse studentAddResponse =
                            Signup.getAllStudResponse.newBuilder().setCheck(getSignUpDao().
                                    AddStudent(new StudentVO(request.getStudentNum() , request.getStudentName() ,
                                            request.getMajor() , request.getClassList()))).build();
                    responseObserver.onNext(studentAddResponse);
                    responseObserver.onCompleted();
                    break;
                case 3:
                    Signup.getAllStudResponse studentDeleteResponse =
                            Signup.getAllStudResponse.newBuilder().setCheck(getSignUpDao().DeleteStudent(request.getStudentNum())).build();
                    responseObserver.onNext(studentDeleteResponse);
                    responseObserver.onCompleted();
                    break;
                case 4 :
                    List<String> studentNumListResponse = new ArrayList<>();
                    List<String> studentNumList = getSignUpDao().StudentNumList();
                    for (String studentNum : studentNumList) {
                        studentNumListResponse.add(studentNum);
                    }
                    Signup.getAllStudResponse courseListNumResponse =
                            Signup.getAllStudResponse.newBuilder().addAllStudentNum(studentNumListResponse).build();

                    responseObserver.onNext(courseListNumResponse);
                    responseObserver.onCompleted();
                    break;
                case 5 :
                    String classList_student = getSignUpDao().classList_Student(request.getStudentNum());
                    Signup.getAllStudResponse classList_student_Response =
                            Signup.getAllStudResponse.newBuilder().setClassList(classList_student).build();

                    responseObserver.onNext(classList_student_Response);
                    responseObserver.onCompleted();

                    break;
                case 6 :
                    int updateCheck = getSignUpDao().UpdateStudent(request.getStudentNum(), request.getClassList());
                    Signup.getAllStudResponse UpdateStudentResponse =
                            Signup.getAllStudResponse.newBuilder().setCheck(updateCheck).build();

                    responseObserver.onNext(UpdateStudentResponse);
                    responseObserver.onCompleted();

                    break;
                default:
                    System.out.println("잘못된 접근입니다.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getAllCourses(Signup.getAllCourseRequest request, StreamObserver<Signup.getAllCourseResponse> responseObserver){
           try {
               int identifier = request.getIdentifier();
               switch (identifier){
                   case 1 :
                       ArrayList<Signup.Course> courseResponseList = new ArrayList<>();
                       List<CourseVO> courseList = getSignUpDao().CourseList();
                       for (CourseVO courseVO : courseList) {
                           Signup.Course getAllCourseResponse = Signup.Course.newBuilder()
                                   .setCourseNum(courseVO.getCourseNum()).setCourseName(courseVO.getCourseName())
                                   .setProfessor(courseVO.getProfessor()).setStudentList(courseVO.getStudentList()).build();
                           courseResponseList.add(getAllCourseResponse);
                       }

                       Signup.getAllCourseResponse courseListResponse =
                               Signup.getAllCourseResponse.newBuilder().addAllCourse(courseResponseList).build();

                       responseObserver.onNext(courseListResponse);
                       responseObserver.onCompleted();
                       break;

                   case 2 :
                       Signup.getAllCourseResponse courseAddResponse =
                               Signup.getAllCourseResponse.newBuilder().setCheck(getSignUpDao().
                                       AddCourse(new CourseVO(request.getCourseNum() , request.getCourseName()
                                       ,request.getProfessor() , request.getStudentList()))).build();
                       responseObserver.onNext(courseAddResponse);
                       responseObserver.onCompleted();
                       break;
                   case 3 :
                       Signup.getAllCourseResponse courseDeleteResponse =
                               Signup.getAllCourseResponse.newBuilder().setCheck(getSignUpDao().DeleteCourse(request.getCourseNum())).build();
                       responseObserver.onNext(courseDeleteResponse);
                       responseObserver.onCompleted();
                       break;
                   case 4 :
                       List<String> courseNumListResponse = new ArrayList<>();
                       List<String> CourseNumList = getSignUpDao().CourseNumList();
                       for (String courseNum : CourseNumList) {
                           courseNumListResponse.add(courseNum);
                       }
                       Signup.getAllCourseResponse courseListNumResponse =
                               Signup.getAllCourseResponse.newBuilder().addAllCourseNum(courseNumListResponse).build();
                       responseObserver.onNext(courseListNumResponse);
                       responseObserver.onCompleted();
                       break;
                   case 5 :
                       String studentList_Course = getSignUpDao().studentList_Course(request.getCourseNum());
                       Signup.getAllCourseResponse studentList_Course_Response =
                               Signup.getAllCourseResponse.newBuilder().setStudentList(studentList_Course).build();

                       responseObserver.onNext(studentList_Course_Response);
                       responseObserver.onCompleted();
                       break;
                   case 6 :
                       int updateCheck = getSignUpDao().UpdateCourse(request.getCourseNum(), request.getStudentList());
                       Signup.getAllCourseResponse UpdateCourseResponse =
                               Signup.getAllCourseResponse.newBuilder().setCheck(updateCheck).build();
                       responseObserver.onNext(UpdateCourseResponse);
                       responseObserver.onCompleted();
                       break;
                   default:
                       System.out.println("잘못된 접근입니다.");
               }
           }catch (Exception e){
           }
    }

    private SignUpDao getSignUpDao() {
        SessionFactory fac = new SessionFactory();
        SqlSession session = fac.openSession(true);
        SignUpDao mapper = session.getMapper(SignUpDao.class);
        return mapper;
    }
}





