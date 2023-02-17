package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Student;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private String id;

    private String name;

    private String coach;

    private List<StudentDTO> studentListDTO = new ArrayList<>();

    private String level;

    private LocalDate lastUpdated;
}
