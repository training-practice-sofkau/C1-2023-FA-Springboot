package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GenericGenerator(name="UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    private String courseId;
    @Column
    private String name;
    @Column
    private String coach;
    @Column
    private Integer level;
    @Column
    private LocalDateTime lastUpdated;

    //OneToMany
    @JsonManagedReference
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, targetEntity = Student.class)
    private List<Student> students = new ArrayList<>();
}
