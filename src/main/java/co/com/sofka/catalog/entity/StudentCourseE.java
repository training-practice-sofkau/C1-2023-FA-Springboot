package co.com.sofka.catalog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentCourseE {

    @EmbeddedId
    StudentCourseId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId")
    Course course;
}
