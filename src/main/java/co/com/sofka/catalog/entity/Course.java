package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @Column @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;

    @Column @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String coach;

    @Column @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer level;

    @Column @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdated;

    //ManyToMany
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinTable(name = "STUDENT_COURSE_TABLE",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Student> students;

    public void addStudentToCourse(Student student){
        students.add(student);
    }

    public void removeStudentToCourse(Student student){
        students.remove(student);
    }

}
