package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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


    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = Course.class)
    @JsonBackReference
    @JoinColumn(name="course_id", foreignKey = @ForeignKey(name = "FK_course_id"))
    private Course course;

}
