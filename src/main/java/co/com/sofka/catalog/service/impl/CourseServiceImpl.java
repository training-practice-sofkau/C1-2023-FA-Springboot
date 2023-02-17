package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;

    public Course dtoToEntity(CourseDTO courseDTO) { return CustomMapper.course(courseDTO); }

    public CourseDTO entityToDto(Course course) { return CustomMapper.courseDTO(course);}
    @Override
    public Course createCourse(CourseDTO courseDTO) {
        return this.courseRepository.save(dtoToEntity(courseDTO));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDTO getByName(String name) {
        return entityToDto(this.courseRepository.findByName(name));
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        return this.courseRepository.findByCoach(c)
                .stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByLevel(Integer level) {
        return this.courseRepository.findByLevel(level)
                .stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Course editCourse(CourseDTO courseDTO) {
        return courseRepository.save(dtoToEntity(courseDTO));
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public CourseDTO findCourseById(Long id){
        return entityToDto(courseRepository.findById(id).orElse(new Course()));
    }

    public Course enrolledStudent(Long idCourse, Long idStudent){
        Course course = courseRepository.findById(idCourse).get();
        Student student = studentRepository.findById(idStudent).get();
        course.enrolledStudent(student);
        return courseRepository.save(course);
    }

    public Course removeStudent(Long idCourse, Long idStudent){
        Course course = courseRepository.findById(idCourse).get();
        Student student = studentRepository.findById(idStudent).get();
        course.removeStudent(student);
        return courseRepository.save(course);
    }
}
