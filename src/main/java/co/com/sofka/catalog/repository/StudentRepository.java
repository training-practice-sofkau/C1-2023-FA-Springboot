package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByNameContainingIgnoreCase (String name);

    Optional<Student> findByIdNum (String idNum);
}
