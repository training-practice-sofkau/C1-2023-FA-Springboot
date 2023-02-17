package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static co.com.sofka.catalog.utils.CustomMapper.courseDTO;

@RestController
@RequestMapping
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    private ResponseEntity<List<CourseDTO>> obtenerCursos(){
        return courseService.getAllCourses().isEmpty() ? ResponseEntity.status(204).body(Collections.emptyList()) :
                ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/courses/name/{name}")
    private ResponseEntity<List<CourseDTO>> obtenerCursosPorNombre(@PathVariable("name") String courseName){
        var r = courseService.getByName(courseName);
        return r == null? ResponseEntity.status(404).build():
                ResponseEntity.ok(r);
    }

    @GetMapping("/courses/coach/{coach}")
    private ResponseEntity<List<CourseDTO>> obtenerCursoPorCoach(@PathVariable("coach") String coach){
        var r = courseService.getByCoach(coach);
        return r == null? ResponseEntity.status(404).build():
                ResponseEntity.ok(r);
    }

    @GetMapping("/courses/level/{level}")
    private ResponseEntity<List<CourseDTO>> obtenerCursoPorNivel(@PathVariable("level") String level){
        var r = courseService.getByLevel(level);
        return r == null? ResponseEntity.status(404).build():
                ResponseEntity.ok(r);
    }

    @PostMapping("/courses")
    private ResponseEntity<CourseDTO> crearCurso(@RequestBody Course course){
        CourseDTO courseSaved = courseService.saveCourse(courseDTO(course));
        return courseSaved == null? ResponseEntity.status(404).body(courseDTO(course)):
                ResponseEntity.status(200).body(courseSaved);
    }

    @PutMapping("/courses")
    private ResponseEntity<CourseDTO> actualizarCurso(@RequestBody Course course){
        CourseDTO courseUpdated = courseService.editCourse(courseDTO(course));
        return courseUpdated == null? ResponseEntity.status(404).body(courseDTO(course)):
                ResponseEntity.status(200).body(courseUpdated);
    }

    @DeleteMapping("/courses/{id}")
    private ResponseEntity<String> eliminarCurso(@PathVariable("id") String idCourse){
        String msg = courseService.deleteCourse(idCourse);
        return msg == null? ResponseEntity.status(404).body("Course non-existent"):
                ResponseEntity.status(201).body(msg);
    }


}
