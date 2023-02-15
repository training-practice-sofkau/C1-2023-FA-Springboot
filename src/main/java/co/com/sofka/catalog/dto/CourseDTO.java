package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String courseId;

    private String name;

    private String coach;


    private Integer level;

    private LocalDate lastUpdated;

    private List<StudentDTO> studentListDTO;
}
