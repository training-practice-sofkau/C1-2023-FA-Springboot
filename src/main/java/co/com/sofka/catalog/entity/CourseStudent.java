package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseStudent {
    @GeneratedValue()
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "FK_student_id"))
    //@JsonIgnoreProperties("albums")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "FK_course_id"))
    //@JsonIgnoreProperties("albums")
    private Course course;

}
