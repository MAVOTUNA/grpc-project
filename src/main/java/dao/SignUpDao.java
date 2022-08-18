package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vo.CourseVO;
import vo.StudentVO;

import java.util.List;
public interface SignUpDao {

    List<CourseVO> CourseList();
    List<StudentVO> StudentList();

    int AddCourse(CourseVO courseVO);
    int AddStudent(StudentVO studentVO);

    int DeleteCourse(@Param("courseNum") String courseNum);
    int DeleteStudent(@Param("studentNum") String studentNum);

    List<String> CourseNumList();
    List<String> StudentNumList();

    String studentList_Course(@Param("courseNum") String courseNum);
    String classList_Student(@Param("studentNum") String studentNum);

    int UpdateCourse(@Param("courseNum") String courseNum , @Param("studentList") String studentList);
    int UpdateStudent(@Param("studentNum") String studentNum , @Param("classList") String classList);

}
