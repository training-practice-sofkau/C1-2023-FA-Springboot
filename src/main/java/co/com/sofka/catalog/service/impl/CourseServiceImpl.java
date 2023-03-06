package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CustomMapper::courseDTO)
                .toList();
    }

    public CourseDTO getCoursesById(String id) {
        return CustomMapper.courseDTO(courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found")));
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name).stream()
                .map(CustomMapper::courseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {
        return courseRepository.findByCoachContainingIgnoreCase(coach).stream()
                .map(CustomMapper::courseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return courseRepository.findByLevel(Integer.parseInt(level)).stream()
                .map(CustomMapper::courseDTO)
                .toList();
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        return CustomMapper.courseDTO(courseRepository.save(CustomMapper.course(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        course.setCoach(courseDTO.getCoach());
        course.setLevel(courseDTO.getLevel());
        course.setName(courseDTO.getName());
        course.setLastUpdated(Instant.now());
        return CustomMapper.courseDTO(courseRepository.save(course));
    }

    @Override
    public String deleteCourse(CourseDTO courseDTO) {
        String name = courseDTO.getName();
        courseRepository.delete(CustomMapper.course(courseDTO));
        return name;
    }
}
