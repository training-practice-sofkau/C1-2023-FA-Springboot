package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
@CrossOrigin
public class StudentController {

    private final StudentServiceImpl studentService;
}
