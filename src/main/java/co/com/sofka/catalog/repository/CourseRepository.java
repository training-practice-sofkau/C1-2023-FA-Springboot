package co.com.sofka.catalog.repository;

import co.com.sofka.catalog.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findByNameContainingIgnoreCase (String name);

    List <Course> findAllByCoachContainingIgnoreCase (String coach);

    List <Course> findAllByLevel (int level);

}
