package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.entity.Student;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CourseDTO {

    private String courseId;

    private String name;

    private String coach;


    private Integer level;

    private LocalDate lastUpdated;

    private List<StudentDTO> studentListDTO = new ArrayList<>();
}
