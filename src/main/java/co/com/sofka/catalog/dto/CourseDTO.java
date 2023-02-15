package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Student;
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
    private String courseId;

    private String name;

    private String coach;

    private List<StudentDTO> studentListDTO;

    private Integer level;

    private LocalDate lastUpdated;
    private List<Student> studentList= new ArrayList<>();
}
