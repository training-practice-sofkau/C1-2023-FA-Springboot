package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.entity.StudentCourseE;
import co.com.sofka.catalog.entity.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourseE, StudentCourseId> {
}
