package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.controller.ItemNotFoundException;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

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
    public CourseDTO getById(String idNum) {
        return entityToDto(courseRepository.findById(idNum).orElseThrow(() -> new ItemNotFoundException(idNum)));
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        return courseRepository.findByNameContaining(name)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        return courseRepository.findByCoachContaining(c)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByLevel(Integer level) {
        return courseRepository.findByLevel(level)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        courseDTO.setStudentListDTO(new ArrayList<StudentDTO>());
        courseDTO.setLastUpdated(LocalDate.now());
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
                    return courseRepository.save(course);
                }).orElseThrow(() -> new ItemNotFoundException(id));
        return entityToDto(courseBody);
    }

    public CourseDTO enrollStudentToCourse(String courseId, String studentsID){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ItemNotFoundException(courseId));
        Student student = studentRepository.findById(studentsID).orElseThrow(() -> new ItemNotFoundException(studentsID));
        course.addStudentToCourse(student);
        student.addNumCourses();
        studentRepository.save(student);
        return entityToDto(courseRepository.save(course));
    }

    @Override
    public CourseDTO deleteCourse(String id) {
        var course = courseRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        var studentList = course.getStudents();
        for(Student student : studentList){
            student.removeNumCourses();
            studentRepository.save(student);
        }
        courseRepository.delete(course);
        return entityToDto(course);
    }

    public CourseDTO deleteStudentToCourse(String courseId, String studentsId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ItemNotFoundException(courseId));
        Student student = studentRepository.findById(studentsId).orElseThrow(() -> new ItemNotFoundException(studentsId));
        course.removeStudentToCourse(student);
        student.removeNumCourses();
        studentRepository.save(student);
        return entityToDto(courseRepository.save(course));
    }
}
