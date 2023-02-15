package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, String> {
    Optional<CourseStudent> findByCourseAndStudent(Course courseId, Student studentId);
}
