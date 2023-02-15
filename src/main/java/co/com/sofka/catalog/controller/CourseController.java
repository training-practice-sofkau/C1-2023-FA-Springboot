package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private ResponseEntity<List<Course>> findAll(){
        return this.courseService.getAllCourses().isEmpty() ?
                ResponseEntity.status(204).body(Collections.emptyList()) :
                ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/byName/{name}")
    private ResponseEntity<CourseDTO> findByName(@PathVariable("name") String name){
        return this.courseService.getByName(name) == null ? ResponseEntity.status(404).body(new CourseDTO())
                : ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping("/byCoach/{coach}")
    private ResponseEntity<List<CourseDTO>> findByCoach(@PathVariable("coach") String coach){
        return this.courseService.getByCoach(coach).isEmpty() ?
                ResponseEntity.status(204).body(Collections.emptyList())
                : ResponseEntity.ok(courseService.getByCoach(coach));
    }

    @GetMapping("/byLevel/{level}")
    private ResponseEntity<List<CourseDTO>> findByCoach(@PathVariable("level") Integer level){
        return this.courseService.getByLevel(level).isEmpty() ?
                ResponseEntity.status(204).body(Collections.emptyList())
                : ResponseEntity.ok(courseService.getByLevel(level));
    }

    @DeleteMapping("{id}")
    private ResponseEntity<?> deleteCourse(@PathVariable("id") String idCourse){
        try{
            courseService.deleteCourse(idCourse);
            return ResponseEntity.ok("Student deleted");
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    private ResponseEntity<Course> updateCourse(@PathVariable("id") String idCourse, @RequestBody CourseDTO courseDetails ){
        CourseDTO courseUp = courseService.findCourseById(idCourse);

        courseUp.setName(courseDetails.getName());
        courseUp.setCoach(courseDetails.getCoach());
        courseUp.setLevel(courseDetails.getLevel());

        courseService.createCourse(courseUp);

        return ResponseEntity.ok(this.courseService.editCourse(courseUp));

    }

    @PutMapping("/{courseId}/students/{studentId}")
    Course enrolleStudentToCourse(
            @PathVariable("courseId") String idCourse,
            @PathVariable("studentId") String idStudent
    ){
       return courseService.enrolledStudent(idCourse, idStudent);
    }
}
