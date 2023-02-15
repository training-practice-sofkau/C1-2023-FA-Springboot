package co.com.sofka.catalog.dto;

import co.com.sofka.catalog.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String id;
    private String name;
    private Integer age;
    private String idNum;
    private String mail;

    private Set<Course> courses = new HashSet<>();
}
