package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sofkau/courses/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.DELETE}, maxAge = 3600)
public class CourseController {
    @Autowired
    CourseServiceImpl service;

    @GetMapping
    private ResponseEntity<List<CourseDTO>> getCourses() {
        return ResponseEntity.status(200).body(service.getAll());
    }

    @GetMapping("/name/{name}")
    private ResponseEntity<List<CourseDTO>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.status(200).body(service.getByName(name));
    }

    @GetMapping("/coach/{coach}")
    private ResponseEntity<List<CourseDTO>> getByCoach(@PathVariable("coach") String coach) {
        return ResponseEntity.status(200).body(service.getByCoach(coach));
    }

    @GetMapping("/level/{level}")
    private ResponseEntity<List<CourseDTO>> getByLevel(@PathVariable("level") Integer level) {
        return ResponseEntity.status(200).body(service.getByLevel(level));
    }

    @PostMapping
    private ResponseEntity<CourseDTO> save(@RequestBody CourseDTO course) {
        return ResponseEntity.status(200).body(service.save(course));
    }

    @PatchMapping
    private ResponseEntity<CourseDTO> update(@RequestBody CourseDTO course) {
        return ResponseEntity.status(200).body(service.update(course));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.status(200).body(service.delete(id));
    }
}
