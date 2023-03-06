package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @GenericGenerator(name="UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String coach;

    @Column
    private Integer level;

    @Column
    private Instant lastUpdated = Instant.now();

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.MERGE,
            targetEntity = Student.class
    )
    @JsonManagedReference
    private List<Student> studentList = new ArrayList<>();
}
