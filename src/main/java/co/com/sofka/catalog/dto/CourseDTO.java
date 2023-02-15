package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String idDTO;

    private String nameDTO;

    private String coachDTO;

    private Integer levelDTO;

    private LocalDate lastUpdatedDTO;

    private List<StudentDTO> studentListDTO=new ArrayList<>();
}
