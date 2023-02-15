package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/courses")
@CrossOrigin
public class CourseController {

    private final CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity getCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
    public ResponseEntity saveCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO courseDTO1 = courseService.saveCourse(courseDTO);
        return courseDTO1 == null ? ResponseEntity.badRequest().body("Wrong params sent") : ResponseEntity.ok().body(courseDTO1);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteCourse(@RequestParam String courseId) throws DataIntegrityViolationException {
        String c = courseService.deleteCourse(courseId);
        return c == null ? ResponseEntity.status(400).body("Course is not in our system") : ResponseEntity.ok().body(c);
    }

    @PutMapping
    public ResponseEntity updateCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO courseDTO1 = courseService.editCourse(courseDTO);
        return courseDTO1 == null ? ResponseEntity.badRequest().body("Wrong params sent") : ResponseEntity.ok().body(courseDTO1);
    }
}
