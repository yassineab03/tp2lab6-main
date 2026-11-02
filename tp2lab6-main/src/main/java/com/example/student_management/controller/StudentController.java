package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Enregistrer un étudiant")
    @PostMapping("/save")
    public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @Operation(summary = "Supprimer un étudiant par id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        return studentService.delete(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Récupérer tous les étudiants")
    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") int id) {
        return ResponseEntity.of(studentService.findById(id));
    }

    @Operation(summary = "Compter les étudiants")
    @GetMapping("/count")
    public ResponseEntity<Long> countStudent() {
        return new ResponseEntity<>(studentService.countStudents(), HttpStatus.OK);
    }

    @Operation(summary = "Nombre d'étudiants par année de naissance")
    @GetMapping("/byYear")
    public ResponseEntity<Collection<?>> findByYear() {
        return new ResponseEntity<>(studentService.findNbrStudentByYear(), HttpStatus.OK);
    }
}
