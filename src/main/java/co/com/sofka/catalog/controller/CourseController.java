package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping("/courses")
    private ResponseEntity<List<CourseDTO>> obtenerCursos(){
        return courseService.getAllCourses().isEmpty()
                ? ResponseEntity.status(204).body(Collections.emptyList())
                : ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/courses/{id}")
    private ResponseEntity<CourseDTO> obtenerCursoPorId(@PathVariable("id") String id){
        return courseService.getById(id) == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/courses/name/{name}")
    private ResponseEntity<List<CourseDTO>> obtenerCursosPorNombre(@PathVariable("name") String name){
        return courseService.getByName(name) == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping("/courses/coach/{coach}")
    private ResponseEntity<List<CourseDTO>> obtenerCursosPorCoach(@PathVariable("coach") String coach){
        return courseService.getByCoach(coach) == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.ok(courseService.getByCoach(coach));
    }

    @GetMapping("/courses/level/{level}")
    private ResponseEntity<List<CourseDTO>> obtenerCursosPorNivel(@PathVariable("level") Integer level){
        return courseService.getByLevel(level) == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.ok(courseService.getByLevel(level));
    }

    @PostMapping(value = "/courses", consumes = "application/json;charset=UTF-8")
    private ResponseEntity<CourseDTO> guardarCurso(@RequestBody CourseDTO courseDTO){
        CourseDTO courseSaved = courseService.saveCourse(courseDTO);
        return  courseSaved == null
                ? ResponseEntity.status(400).body(courseDTO)
                : ResponseEntity.status(201).body(courseSaved);
    }

    @PutMapping("/courses/{id}")
    private ResponseEntity<CourseDTO> actualizarCursos(@PathVariable("id") String id, @RequestBody CourseDTO courseDTO){
        CourseDTO updatedCourse = courseService.editCourse(id, courseDTO);
        return updatedCourse == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(updatedCourse);
    }

    @PutMapping("/courses/{courseId}/students/{studentsId}")
    private ResponseEntity<CourseDTO> apuntarEstudianteACurso(@PathVariable("courseId") String courseId, @PathVariable("studentsId") String studentsId){
        CourseDTO updatedCourse = courseService.enrollStudentToCourse(courseId, studentsId);
        return updatedCourse == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(updatedCourse);
    }

    @DeleteMapping("/courses/{id}")
    private ResponseEntity<CourseDTO> borrarCurso(@PathVariable("id") String id){
        CourseDTO courseDeleted = courseService.deleteCourse(id);
        return courseDeleted == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(courseDeleted);
    }

    @DeleteMapping("/courses/{courseId}/students/{studentsId}")
    private ResponseEntity<CourseDTO> eliminarEstudianteDeCurso(@PathVariable("courseId") String courseId, @PathVariable("studentsId") String studentsId){
        CourseDTO updatedCourse = courseService.deleteStudentToCourse(courseId, studentsId);
        return updatedCourse == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(updatedCourse);
    }
}
