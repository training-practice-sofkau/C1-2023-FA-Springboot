package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping()
    public ResponseEntity<List<CourseDTO>> getAll(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/bycoach/{coach}")
    public ResponseEntity<List<CourseDTO>> getByCoach(@PathVariable("coach") String coach){
        return ResponseEntity.ok(courseService.getByCoach(coach));
    }

    @GetMapping("/bylevel/{level}")
    public ResponseEntity<List<CourseDTO>> getByLevel(@PathVariable("level") Integer level){
        return ResponseEntity.ok(courseService.getByLevel(level));
    }

    @PostMapping()
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO result = courseService.createCourse(courseDTO);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<CourseDTO> editCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO result = courseService.getById(courseDTO.getId());
        if(result.getName() != null){
            return ResponseEntity.ok(courseService.editCourse(courseDTO));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable("id") String id){
        courseService.deleteCourse(id);
    }
}
