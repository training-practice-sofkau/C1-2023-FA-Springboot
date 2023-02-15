package co.com.sofka.catalog.dto;

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

    private Integer age;

    private String mail;

    private CourseDTO courseDTO;
}
