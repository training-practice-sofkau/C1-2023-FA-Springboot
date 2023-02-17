package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.CourseStudentRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.ICourseStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseStudentServiceImpl implements ICourseStudentService {
    @Autowired
    CourseStudentRepository courseStudentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    StudentServiceImpl studentService;

    @Override
    public Boolean createRelation(CourseStudent courseStudent) {
        if (courseRepository.existsById(courseStudent.getCourse().getCourseId()) && studentRepository.existsById(courseStudent.getStudent().getStudentId())) {
            if (courseStudentRepository.findByCourseAndStudent(courseStudent.getCourse(), courseStudent.getStudent()).isEmpty()) {
                courseStudentRepository.save(courseStudent);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Boolean removeRelation(CourseStudent courseStudent) {
        if (courseRepository.existsById(courseStudent.getCourse().getCourseId()) && studentRepository.existsById(courseStudent.getStudent().getStudentId())) {
            Optional<CourseStudent> relation = courseStudentRepository.findByCourseAndStudent(courseStudent.getCourse(), courseStudent.getStudent());
            if (relation.isPresent()) {
                courseStudentRepository.delete(relation.get());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<List<CourseDTO>> getStudentRelations(StudentDTO student) {
        if (studentRepository.existsById(student.getStudentId())) {
            Optional<List<CourseStudent>> related = courseStudentRepository.findByStudent(CustomMapper.student(student));
            Optional<List<CourseDTO>> notRelated = Optional.of(courseService.getAll().stream().filter(course -> course.getStudentListDTO().stream().noneMatch(result -> result.getStudentId().equals(student.getStudentId()))).collect(Collectors.toList()));
            return related.map(courseStudents -> List.of(courseStudents.stream().map(relation -> CustomMapper.courseDTO(relation.getCourse())).collect(Collectors.toList()), notRelated.get())).orElseGet(ArrayList::new);
        } else {
            return new ArrayList<>();
        }
    }

    ;

    @Override
    public List<List<StudentDTO>> getCourseRelations(CourseDTO course) {
        if (courseRepository.existsById(course.getCourseId())) {
            Optional<List<CourseStudent>> related = courseStudentRepository.findByCourse(CustomMapper.course(course));
            Optional<List<StudentDTO>> notRelated = Optional.of(studentService.getAll().stream().filter(student -> student.getCourseListDTO().stream().noneMatch(result -> result.getCourseId().equals(course.getCourseId()))).collect(Collectors.toList()));
            return related.map(courseStudents -> List.of(courseStudents.stream().map(relation -> CustomMapper.studentDTO(relation.getStudent())).collect(Collectors.toList()), notRelated.get())).orElseGet(ArrayList::new);
        } else {
            return new ArrayList<>();
        }
    }

    ;
}
