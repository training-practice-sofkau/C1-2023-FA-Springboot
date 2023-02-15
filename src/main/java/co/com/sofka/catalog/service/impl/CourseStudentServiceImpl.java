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

@Service
public class CourseStudentServiceImpl implements ICourseStudentService {
    @Autowired
    CourseStudentRepository courseStudentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Boolean createRelation(CourseStudent courseStudent) {
        if(courseRepository.existsById(courseStudent.getCourse().getCourseId())&&studentRepository.existsById(courseStudent.getStudent().getStudentId())){
            if (courseStudentRepository.findByCourseAndStudent(courseStudent.getCourse(), courseStudent.getStudent()).isEmpty()) {
                courseStudentRepository.save(courseStudent);
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public Boolean removeRelation(CourseStudent courseStudent) {
        if(courseRepository.existsById(courseStudent.getCourse().getCourseId())&&studentRepository.existsById(courseStudent.getStudent().getStudentId())){
            if (courseStudentRepository.findByCourseAndStudent(courseStudent.getCourse(), courseStudent.getStudent()).isPresent()) {
                courseStudentRepository.delete(courseStudent);
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }
}
