package com.lzf.d240819.service;

import com.lzf.d240819.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface StudentService extends IService<Student> {

    List<Student> listAll();
}
