package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.CourseStudent;

import java.util.List;

public interface ICourseStudentService {
    Boolean createRelation(CourseStudent courseStudent);
    Boolean removeRelation(CourseStudent courseStudent);
    List<List<CourseDTO>> getStudentRelations(StudentDTO studentId);
    List<List<StudentDTO>> getCourseRelations(CourseDTO courseId);
}
