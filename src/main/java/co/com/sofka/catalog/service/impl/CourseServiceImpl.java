package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getByName(String name) {
        return (courseRepository.findByNameContaining(name));
    }

    @Override
    public List<Course> getByCoach(String c) {
        return courseRepository.findByCoachContaining(c);
    }

    @Override
    public List<Course> getByLevel(String level) {

        return courseRepository.findByLevel(level);
    }
    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {

        return CustomMapper.courseDTO(courseRepository.save(CustomMapper.course(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        return CustomMapper.courseDTO(courseRepository.save(CustomMapper.course(courseDTO)));
    }

    @Override
    public String deleteCourse(String id) {
        Optional<Course> d = courseRepository.findById(id);
        if (d.isEmpty()) return null;

        else{
            courseRepository.deleteById(id);
            return "Course deleted";
        }

    }
}
