package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.service.impl.CourseStudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sofkau/relation/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.DELETE}, maxAge = 3600)
public class RelationController {

    @Autowired
    CourseStudentServiceImpl service;

    @GetMapping("/student/{id}")
    private ResponseEntity<List<List<CourseDTO>>> getStudentRelations(@PathVariable("id") String student ) {
        if (student != null) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(student);
            return ResponseEntity.status(200).body(service.getStudentRelations(studentDTO));
        } else {
            return ResponseEntity.status(200).body(new ArrayList<>());
        }
    }
    @GetMapping("/course/{id}")
        private ResponseEntity<List<List<StudentDTO>>> getCourseRelations(@PathVariable("id") String course ) {
        if (course != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourseId(course);
            return ResponseEntity.status(200).body(service.getCourseRelations(courseDTO));
        } else {
            return ResponseEntity.status(200).body(new ArrayList<>());
        }
    }

    @PostMapping
    private ResponseEntity<Boolean> relate(@RequestBody CourseStudent courseStudent) {
        return ResponseEntity.status(200).body(service.createRelation(courseStudent));
    }

    @DeleteMapping("/{courseId}/{studentId}")
    private ResponseEntity<Boolean> unRelate(@PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId) {
        Course course = new Course();
        Student student = new Student();
        course.setCourseId(courseId);
        student.setStudentId(studentId);
        CourseStudent courseStudent = new CourseStudent(null, student,course);
        return ResponseEntity.status(200).body(service.removeRelation(courseStudent));
}
    }
