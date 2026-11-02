package com.example.student_management;

import com.example.student_management.controller.StudentController;
import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setId(1);
        student.setNom("Mido");
        when(studentService.save(any(Student.class))).thenReturn(student);

        ResponseEntity<Student> response = studentController.save(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Mido", response.getBody().getNom());
    }

    @Test
    void testDeleteStudent() {
        when(studentService.delete(1)).thenReturn(true);
        ResponseEntity<Void> response = studentController.delete(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testFindAllStudents() {
        when(studentService.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));
        ResponseEntity<List<Student>> response = studentController.findAll();
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCountStudents() {
        when(studentService.countStudents()).thenReturn(10L);
        ResponseEntity<Long> response = studentController.countStudent();
        assertEquals(10L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFindByYear() {
        when(studentService.findNbrStudentByYear()).thenReturn(Arrays.asList());
        ResponseEntity<Collection<?>> response = studentController.findByYear();
        assertEquals(0, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void findByIdReturnsStudentWhenPresent() {
        Student student = new Student();
        student.setId(7);
        when(studentService.findById(7)).thenReturn(Optional.of(student));
        ResponseEntity<Student> response = studentController.findById(7);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void findByIdReturnsNotFoundWhenAbsent() {
        when(studentService.findById(99)).thenReturn(Optional.empty());
        assertEquals(HttpStatus.NOT_FOUND, studentController.findById(99).getStatusCode());
    }
}
