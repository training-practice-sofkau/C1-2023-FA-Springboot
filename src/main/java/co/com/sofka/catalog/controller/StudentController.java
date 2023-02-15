package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/students")
    private ResponseEntity<List<StudentDTO>> obtenerEstudiantes(){
        return studentService.getAllStudents().isEmpty()
                ? ResponseEntity.status(204).body(Collections.emptyList())
                : ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    private ResponseEntity<StudentDTO> obtenerEstudiantePorId(@PathVariable("id") String id){
        return studentService.getByIdentificationNumber(id) == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.ok(studentService.getByIdentificationNumber(id));
    }

    @GetMapping("/students/name/{name}")
    private ResponseEntity<List<StudentDTO>> obtenerEstudiantePorNombre(@PathVariable("name") String name){
        return studentService.getByName(name) == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.ok(studentService.getByName(name));
    }

    @PostMapping("/students")
    private ResponseEntity<StudentDTO> guardarArtista(@RequestBody StudentDTO studentDTO){
        StudentDTO studentSaved = studentService.saveStudent(studentDTO);
        return  studentSaved == null
                ? ResponseEntity.status(400).body(studentDTO)
                : ResponseEntity.status(201).body(studentSaved);
    }

    @PutMapping("/students/{id}")
    private ResponseEntity<StudentDTO> actualizarArtista(@PathVariable("id") String id, @RequestBody StudentDTO studentDTO){
        StudentDTO updatedArtist = studentService.editStudent(id, studentDTO);
        return studentDTO == null
                ? ResponseEntity.status(404).body(updatedArtist)
                : ResponseEntity.status(200).body(updatedArtist);
    }

    @DeleteMapping("/students/{id}")
    private ResponseEntity<StudentDTO> borrarArtista(@PathVariable("id") String id){
        StudentDTO studentDeleted = studentService.deleteStudent(id);
        return studentDeleted == null
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(studentDeleted);
    }

}
