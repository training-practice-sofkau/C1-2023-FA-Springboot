package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.sofka.catalog.utils.CustomMapper.student;
import static co.com.sofka.catalog.utils.CustomMapper.studentDTO;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/students")
    private ResponseEntity<List<Student>> obtenerEstudiantes(){
        return studentService.getAllStudents().isEmpty() ? ResponseEntity.status(204).body(Collections.emptyList()) :
                ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/IdNum/{id}")
    private ResponseEntity<Student> obtenerEstudiantePorIdNumber(@PathVariable("id") String idStudent){
        return studentService.getByIdentificationNumber(idStudent) == null ? ResponseEntity.status(404).body(student(new StudentDTO())):
                ResponseEntity.ok(student(studentService.getByIdentificationNumber(idStudent)));
    }

    @PostMapping("/students")
    private ResponseEntity<StudentDTO> crearEstudiante(@RequestBody StudentDTO studentDTO){
        StudentDTO studentSaved = studentService.saveStudent(studentDTO);
        return studentSaved == null? ResponseEntity.status(404).body(studentDTO):
                ResponseEntity.status(200).body(studentSaved);
    }

    @PutMapping("/students")
    private ResponseEntity<StudentDTO> actualizarEstudiante(@RequestBody Student student){
        StudentDTO studentUpdated = studentService.editStudent(studentDTO(student));
        return studentUpdated == null? ResponseEntity.status(404).body(studentDTO(student)):
                ResponseEntity.status(200).body(studentUpdated);
    }

    @DeleteMapping("/students/{id}")
    private ResponseEntity<String> eliminarEstudiante(@PathVariable("id") String idStudent){
        String msg = studentService.deleteStudent(idStudent);
        return msg == null? ResponseEntity.status(404).body("Student non-existent") :
                ResponseEntity.status(201).body(msg);
    }

    @GetMapping("/students/name/{name}")
    private ResponseEntity<List<Student>> obtenerEstudiantePorNombre(@PathVariable("name") String studentName){
        var r = studentService.getByName(studentName);
        return r == null? ResponseEntity.status(404).build() : ResponseEntity.ok(r);
    }
}
