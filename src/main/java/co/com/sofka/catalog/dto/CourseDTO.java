package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String id;

    private String name;

    private String coach;

    private List<StudentDTO> studentListDTO = new ArrayList<>();

    private int level;

    private LocalDate lastUpdated;
}
