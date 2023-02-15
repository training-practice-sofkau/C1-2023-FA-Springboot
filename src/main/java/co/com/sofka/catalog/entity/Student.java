package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @GenericGenerator(name="UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String idNum;

    @Column
    private Integer age;

    @Column
    private String mail;

    @Column
    private Integer numCourses;

    //ManyToOne
    @ManyToMany(mappedBy = "students",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Course> courses;

    public void addNumCourses(){
        numCourses++;
    }

    public void removeNumCourses(){
        numCourses--;
    }

}
