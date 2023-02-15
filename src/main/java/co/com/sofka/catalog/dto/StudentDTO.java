package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String idDTO;

    private String nameDTO;

    private String idNumDTO;

    private Integer ageDTO;

    private String mailDTO;

    private CourseDTO courseDTO;
}
