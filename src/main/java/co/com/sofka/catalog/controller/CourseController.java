package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
@CrossOrigin(origins = "http://localhost:4200/")
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    public CourseController(CourseServiceImpl courseServiceImpl) {
        this.courseServiceImpl = courseServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity<>(courseServiceImpl.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CourseDTO>> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(courseServiceImpl.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/coach/{coach}")
    public ResponseEntity<List<CourseDTO>> getByCoach(@PathVariable("coach") String coach){
        return new ResponseEntity<>(courseServiceImpl.getByCoach(coach), HttpStatus.OK);
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<CourseDTO>> getByLevel(@PathVariable("level") String level){
        return new ResponseEntity<>(courseServiceImpl.getByLevel(level), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO) {
        courseServiceImpl.saveCourse(courseDTO);
        return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> editCourse(@RequestBody CourseDTO courseDTO) {
        courseServiceImpl.editCourse(courseDTO);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") String courseId){
        return new ResponseEntity<>(courseServiceImpl.deleteCourse(courseId), HttpStatus.OK);
    }
}
