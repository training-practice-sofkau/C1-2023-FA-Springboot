package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Course;
import lombok.*;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String id;

    private String name;

    private String idNum;

    private Integer age;

    private String mail;

    //private Integer numCourses;

    private CourseDTO course;
}
