package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Course {
    @GenericGenerator(name="UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String id;

    private String name;

    private String coach;

    private String level;

    private LocalDate lastUpdated;

    //OneToMany
    @OneToMany(mappedBy = "course", targetEntity = Student.class)
    @JsonManagedReference
    private List<Student> studentList = new ArrayList<>();



}
