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
}
