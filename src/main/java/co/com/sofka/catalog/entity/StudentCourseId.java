package co.com.sofka.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class StudentCourseId implements Serializable {

    @Column(name = "studentId")
    String studentId;

    @Column(name = "courseId")
    String courseId;

}
