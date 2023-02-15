package co.com.sofka.catalog.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdated;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,
            targetEntity = Student.class)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

}
