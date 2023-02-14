package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Course findByName(String name);

    List<Course> findByCoach(String coach);

    List<Course> findByLevel(Integer level);
}
