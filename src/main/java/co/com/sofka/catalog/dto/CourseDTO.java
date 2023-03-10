package co.com.sofka.catalog.dto;

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
    private String id;

    private String name;

    private String coach;

    private List<StudentDTO> studentListDTO;

    private Integer level;

    private LocalDate lastUpdated;
}
