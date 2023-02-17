package co.com.sofka.catalog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @GenericGenerator(name="UUID",
            strategy = "co.com.sofka.catalog.utils.UUIDGeneratorTruncated")
    @GeneratedValue(generator = "UUID")
    @Id
    private String id;

    private String name;

    private String idNum;

    private Integer age;

    private String mail;

    //private Boolean subscribed = false;

    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "FK_course_id"))
    @JsonBackReference
    private Course course;

}
