package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.controller.ItemNotFoundException;
import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course dtoToEntity(CourseDTO courseDTO) {
        return CustomMapper.course(courseDTO);
    }

    @Override
    public CourseDTO entityToDto(Course course) {
        return CustomMapper.courseDTO(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream().map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> Objects.equals(course.getName(), name))
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> Objects.equals(course.getCoach(), c))
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> Objects.equals(course.getCoach(), level))
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        return entityToDto(courseRepository.save(dtoToEntity(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(String id, CourseDTO courseDTO) {
        var courseBody = courseRepository.findById(id)
                .map(course -> {
                    course.setName(courseDTO.getName());
                    course.setCoach(courseDTO.getCoach());
                    course.setLevel(courseDTO.getLevel());
                    course.setLastUpdated(LocalDate.now());
                    course.setStudentList(CustomMapper.listStudent(courseDTO.getStudentListDTO()));
                    return courseRepository.save(course);
                }).orElseThrow(() -> new ItemNotFoundException(id));
        return entityToDto(courseBody);
    }

    @Override
    public CourseDTO deleteCourse(String id) {
        var course = courseRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        courseRepository.delete(course);
        return entityToDto(course);
    }
}
