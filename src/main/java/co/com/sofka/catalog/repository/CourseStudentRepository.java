package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, String> {
    Optional<CourseStudent> findByCourseAndStudent(Course courseId, Student studentId);
    Optional<List<CourseStudent>> findByCourse(Course courseId);
    Optional<List<CourseStudent>> findByStudent(Student studentId);
    Optional<List<CourseStudent>> findByCourseNot(Course courseId);
    Optional<List<CourseStudent>> findByStudentNot(Student studentId);
}
