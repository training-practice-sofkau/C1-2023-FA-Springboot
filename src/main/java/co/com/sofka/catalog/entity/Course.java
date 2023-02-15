package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
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
            targetEntity = CourseStudent.class)
    @JsonIgnoreProperties("studentList")
    private List<CourseStudent> studentList;


}
