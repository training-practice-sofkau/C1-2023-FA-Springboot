package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService){
        this.courseService = courseService;
    }

    @GetMapping("")
    private ResponseEntity<List<CourseDTO>> obtenerCursos(){
        var result = courseService.getAllCourses();
        return result.isEmpty() ?
                ResponseEntity.status(204).body(result) :
                ResponseEntity.ok(result);
    }

    @GetMapping("/byName/{name}")
    private ResponseEntity<List<CourseDTO>> obtenerPorNombre(
            @PathVariable("name") String courseName){
        var result = courseService.getByName(courseName);
        return result.isEmpty() ?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(result);
    }

    @GetMapping("/byCoach/{coach}")
    private ResponseEntity<List<CourseDTO>> obtenerPorCoach(
            @PathVariable("coach") String coach){
        var result = courseService.getByCoach(coach);
        return result.isEmpty() ?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(result);
    }



    @GetMapping("/byLevel/{level}")
    private ResponseEntity<List<CourseDTO>> obtenerPorLevel(
            @PathVariable("level") Integer level){
        var result = courseService.getByLevel(level);
        return result.isEmpty() ?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(result);
    }

    @PostMapping("")
    private ResponseEntity<CourseDTO> crearCurso(@RequestBody CourseDTO courseDTO){
        CourseDTO courseDTO1 = courseService.saveCourse(courseDTO);
        return courseDTO1 == null?
                ResponseEntity.status(400).body(null) :
                ResponseEntity.status(201).body(courseDTO1);
    }

    @PutMapping("")
    private ResponseEntity<CourseDTO> actualizarCurso(@RequestBody CourseDTO courseDTO){
        CourseDTO courseDTO1 = courseService.editCourse(courseDTO);
        return courseDTO1 == null?
                ResponseEntity.status(400).body(null) :
                ResponseEntity.status(201).body(courseDTO1);
    }


    @DeleteMapping("/{id}")
    private ResponseEntity<String> borrarCurso (@PathVariable("id") String courseId){
        String s = courseService.deleteCourse(courseId);
        return s == null ?
                new ResponseEntity<>("There is not course with id: " + courseId, HttpStatus.CONFLICT):
                new ResponseEntity<>(s, HttpStatus.ACCEPTED);
    }
}
