package vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class CourseVO {

    private  final String courseNum;
    private  final String courseName;
    private  final String professor;
    private  final String studentList;

}
