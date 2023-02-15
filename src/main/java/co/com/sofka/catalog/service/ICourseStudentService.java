package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.CourseStudent;

public interface ICourseStudentService {
    Boolean createRelation(CourseStudent courseStudent);
    Boolean removeRelation(CourseStudent courseStudent);
}
