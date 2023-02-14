package co.com.sofka.catalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @GenericGenerator(name = "UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String studentId;

    @Column
    private String name;

    @Column
    private String idNum;

    @Column
    private Integer age;

    @Column
    private String mail;

    @OneToMany(mappedBy = "student",
            targetEntity = CourseStudent.class)
    private List<CourseStudent> courseList;

}
