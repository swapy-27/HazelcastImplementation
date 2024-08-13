package com.swapy.hazelcast_app.controller;


import com.swapy.hazelcast_app.Entity.Student;
import com.swapy.hazelcast_app.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "students", key="#id")
    public Student getStudentById(@PathVariable Integer id) {

        // Adding a delay in case data fetches from DB instead of Cache
        try{
            Thread.sleep(2000);
        }catch ( InterruptedException e ){
            System.out.println("Something went wrong");
        }

        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    @CachePut(value = "students", key="#id")
    public Student updateStudent( @RequestBody Student student,@PathVariable Integer id) {
        return studentService.updateStudent(student , id);
    }


    @DeleteMapping("/{id}")
    @Cacheable(value = "students", key="#id")
    public Student deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }


}
