package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import co.com.sofka.catalog.utils.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static co.com.sofka.catalog.utils.CustomMapper.student;
import static co.com.sofka.catalog.utils.CustomMapper.studentDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/students")
    private ResponseEntity<List<StudentDTO>> obtenerEstudiantes(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/idnum/{id}")
    private ResponseEntity<StudentDTO> obtenerEstudiantePorIdNumber(@PathVariable("id") String idStudent) {
        return studentService.getByIdentificationNumber(idStudent) == null ? ResponseEntity.status(404).body(new StudentDTO()) :
                ResponseEntity.ok(studentService.getByIdentificationNumber(idStudent));
    }

    @PostMapping("/students")
    private ResponseEntity<StudentDTO> crearEstudiante(@RequestBody StudentDTO studentDTO) {
        StudentDTO studentSaved = studentService.saveStudent(studentDTO);
        return studentSaved == null ? ResponseEntity.status(404).body(studentDTO) :
                ResponseEntity.status(200).body(studentSaved);
    }

    @PutMapping("/students")
    private ResponseEntity<StudentDTO> actualizarEstudiante(@RequestBody StudentDTO studentDTO) {
        StudentDTO studentUpdated = studentService.editStudent(studentDTO);
        return studentUpdated == null ? ResponseEntity.status(404).body(studentDTO) :
                ResponseEntity.status(200).body(studentUpdated);
    }

    @DeleteMapping("/students/{id}")
    private ResponseEntity<String> eliminarEstudiante(@PathVariable("id") String idStudent) {
        String msg = studentService.deleteStudent(idStudent);
        return msg == null ? ResponseEntity.status(404).body("Student non-existent") :
                ResponseEntity.status(201).body(msg);
    }

    @GetMapping("/students/name/{name}")
    private ResponseEntity<List<StudentDTO>> obtenerEstudiantePorNombre(@PathVariable("name") String studentName) {
        var r = studentService.getByName(studentName);
        return r == null ? ResponseEntity.status(404).build() : ResponseEntity.ok(r);
    }
}
