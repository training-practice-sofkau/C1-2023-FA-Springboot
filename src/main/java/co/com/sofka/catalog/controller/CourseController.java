package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;

    @PostMapping
    private ResponseEntity<Course> saveStudent(@RequestBody CourseDTO courseDTO){
        Course course =  courseService.createCourse(courseDTO);
        return course == null ? ResponseEntity.status(400).body(course) : ResponseEntity.status(201).body(course);
    }

    @GetMapping
    private ResponseEntity<List<CourseDTO>> findAll(){
        return this.courseService.getAllCourses().isEmpty() ?
                ResponseEntity.status(204).body(Collections.emptyList()) :
                ResponseEntity.ok(courseService.getAllCourses());
    }
}
