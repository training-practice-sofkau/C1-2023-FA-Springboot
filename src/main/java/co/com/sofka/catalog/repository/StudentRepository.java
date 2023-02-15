package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByNameContaining (String name);
    List<Student> findByIdNum (String idNum);

}
