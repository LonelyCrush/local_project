package com.lzf.d240819.controller;

import com.lzf.d240819.entity.Student;
import com.lzf.d240819.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author leizf
 * @since 2024-08-19 22:55
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/listAll")
    public List<Student> listAll() {
        return studentService.listAll();
    }
}
