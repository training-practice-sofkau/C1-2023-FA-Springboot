package co.com.sofka.catalog.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private String studentId;

    private String name;

    private String idNum;

    private Integer age;

    private String mail;

    private Integer numCourses;

    private List<CourseDTO> courseListDTO = new ArrayList<>();
}
