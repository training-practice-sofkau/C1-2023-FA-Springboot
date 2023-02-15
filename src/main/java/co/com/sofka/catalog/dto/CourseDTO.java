package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String courseId;

    private String name;

    private String coach;

    private Integer level;

    private LocalDateTime lastUpdated;
    private List<Student> studentList= new ArrayList<>();
}
