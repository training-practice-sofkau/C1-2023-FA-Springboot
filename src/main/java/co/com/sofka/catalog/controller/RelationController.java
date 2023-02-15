package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.service.impl.CourseStudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sofkau/relation/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.DELETE}, maxAge = 3600)
public class RelationController {

    @Autowired
    CourseStudentServiceImpl service;

    @PostMapping
    private ResponseEntity<Boolean> relate(@RequestBody CourseStudent courseStudent) {
        return ResponseEntity.status(200).body(service.createRelation(courseStudent));
    }
    @DeleteMapping
    private ResponseEntity<Boolean> unRelate(@RequestBody CourseStudent courseStudent) {
        return ResponseEntity.status(200).body(service.removeRelation(courseStudent));
    }
}
