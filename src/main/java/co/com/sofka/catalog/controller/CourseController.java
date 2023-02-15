package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity obtenerCursos(){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<CourseDTO> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        else {
            return new ResponseEntity <List> (courses, httpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity courseById(@PathVariable("id") String courseID){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        CourseDTO course = courseService.getById(courseID);

        if (course.getId() == null) {
            return ResponseEntity.status(404).build();
        }
        else {
            return new ResponseEntity (course, httpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("/name/{name}")
    private ResponseEntity getByName(@PathVariable("name") String courseName) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<CourseDTO> courses = courseService.getByName(courseName);

        if (courses.isEmpty()) {
            return ResponseEntity.status(204).body(Collections.emptyList());
        }
        else {
            return new ResponseEntity (courses, httpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("/coach/{coach}")
    private ResponseEntity getByCoach(@PathVariable("coach") String coachName) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<CourseDTO> courses = courseService.getByCoach(coachName);

        if (courses.isEmpty()) {
            return ResponseEntity.status(204).body(Collections.emptyList());
        }
        else {
            return new ResponseEntity (courses, httpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("/level/{level}")
    private ResponseEntity getByLevel(@PathVariable("level") String levelName) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<CourseDTO> courses = courseService.getByLevel(levelName);

        if (courses.isEmpty()) {
            return ResponseEntity.status(204).body(Collections.emptyList());
        }
        else {
            return new ResponseEntity (courses, httpHeaders, HttpStatus.OK);
        }
    }

    @PostMapping
    private ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO course = courseService.saveCourse(courseDTO);
        return  course == null ?
                ResponseEntity.status(400).body(courseDTO)
                : ResponseEntity.status(201).body(course);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") String courseID,
                                                        @RequestBody CourseDTO courseDTO) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        CourseDTO courseUpdated = courseService.editCourse(courseDTO, courseID);

        if (courseUpdated.getId() == null) {
            return ResponseEntity.status(404).build();
        }
        else{
            return new ResponseEntity (courseUpdated, httpHeaders, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@PathVariable("id") String courseID){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        CourseDTO course = courseService.getById(courseID);

        if (course.getId() == null) {
            return ResponseEntity.status(404).body("No course found with the ID provided");
        }
        else {
            String response = courseService.deleteCourse(course.getId());
            return new ResponseEntity (response, httpHeaders, HttpStatus.OK);
        }
    }

}
