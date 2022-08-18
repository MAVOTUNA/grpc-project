package vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @RequiredArgsConstructor
public class StudentVO {

    private final String studentNum;
    private final String studentName;
    private final String major;
    private final String classList;

}
