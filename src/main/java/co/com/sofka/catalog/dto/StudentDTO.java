package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String studentId;

    private String name;

    private String idNum;

    private Integer age;

    private String mail;

    private Course course;
}
