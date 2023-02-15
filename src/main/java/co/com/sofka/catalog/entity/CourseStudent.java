package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CourseStudent {
    @GenericGenerator(name = "UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "FK_student_id"))
    @JsonIgnoreProperties("courseList")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "FK_course_id"))
    @JsonIgnoreProperties("studentList")
    private Course course;

}
