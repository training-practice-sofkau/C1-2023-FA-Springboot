package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sofkau/students/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.DELETE}, maxAge = 3600)
public class StudentController {
    @Autowired
    StudentServiceImpl service;
    @GetMapping
    private ResponseEntity<List<StudentDTO>> getArtists() {
        return ResponseEntity.status(200).body(service.getAll());
    }


    @GetMapping("/name/{name}")
    private ResponseEntity<List<StudentDTO>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.status(200).body(service.getByName(name));
    }

    @GetMapping("/idnum/{idnum}")
    private ResponseEntity<List<StudentDTO>> getByIdNum(@PathVariable("idnum") String idNum) {
        return ResponseEntity.status(200).body(service.getByIdNum(idNum));
    }

    @PostMapping
    private ResponseEntity<StudentDTO> save(@RequestBody StudentDTO student) {
        return ResponseEntity.status(200).body(service.save(student));
    }

    @PatchMapping
    private ResponseEntity<StudentDTO> update(@RequestBody StudentDTO student) {
        return ResponseEntity.status(200).body(service.update(student));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.status(200).body(service.delete(id));
    }

}
