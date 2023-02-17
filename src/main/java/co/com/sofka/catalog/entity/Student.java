package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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
            targetEntity = CourseStudent.class,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties("courseList")
    @ToString.Exclude
    private List<CourseStudent> courseList = new ArrayList<>();


}
