package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Course {
    @GenericGenerator(name = "UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String courseId;

    @Column
    private String name;

    @Column
    private String coach;

    @Column
    private Integer level;

    @Column
    private LocalDate lastUpdated;

    @OneToMany(mappedBy = "course",
            targetEntity = CourseStudent.class,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties("studentList")
    @ToString.Exclude
    private List<CourseStudent> studentList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return courseId != null && Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
