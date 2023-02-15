package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String id;

    private String name;

    private String idNum;

    private int age;

    private String mail;

    private CourseDTO course;

    //private int numCourses;
}
