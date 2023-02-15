package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    private ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO song = studentService.createStudent(studentDTO);
        return song == null ? ResponseEntity.status(400).body(studentDTO) : ResponseEntity.status(201).body(song);
    }

    @GetMapping
    private ResponseEntity<List<StudentDTO>> findAll(){
        return this.studentService.getAllStudents().isEmpty() ?
                ResponseEntity.status(204).body(Collections.emptyList()) :
                ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/byIdNum/{idNum}")
    private ResponseEntity<StudentDTO> findByIdNum(@PathVariable("idNum") String idNum){
        return this.studentService.getByIdentificationNumber(idNum) == null ? ResponseEntity.status(404).body(new StudentDTO())
                : ResponseEntity.ok(studentService.getByIdentificationNumber(idNum));
    }

    @GetMapping("/byName/{name}")
    private ResponseEntity<List<StudentDTO>> findByName(@PathVariable("name") String name){
        return this.studentService.getByName(name).isEmpty() ?
                ResponseEntity.status(204).body(Collections.emptyList()) :
                ResponseEntity.ok(studentService.getByName(name));
    }
}
