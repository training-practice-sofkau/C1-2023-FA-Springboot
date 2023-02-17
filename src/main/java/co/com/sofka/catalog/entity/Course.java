package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
//    @GenericGenerator(name="UUID",
//            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private String coach;

    private Integer level;

    private LocalDate lastUpdated;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonManagedReference
    private Set<Student> enrolledStudents = new HashSet<>();

    public void enrolledStudent(Student student) {
        this.enrolledStudents.add(student);
    }

    public void removeStudent(Student student){
        this.enrolledStudents.remove(student);
    }
}
