package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @GenericGenerator(name="UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String studentID;
    @Column
    private String name;
    @Column
    private String idNum;
    @Column
    private Integer age;
    @Column
    private String mail;
    @Column
    private Integer numCourses;

    //ManyToOne
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinColumn(name="course_id", foreignKey = @ForeignKey(name = "FK_course_id"))
    private Course course;
}
