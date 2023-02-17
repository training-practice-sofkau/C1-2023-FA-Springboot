package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    //@Query("SELECT s FROM students WHERE s.idNum =: idNum")
    Student findByIdNum(String idNum);
    List<Student> findByNameContaining(String name);
}
