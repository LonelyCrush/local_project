package com.lzf.d240819.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzf.d240819.entity.Student;
import com.lzf.d240819.service.StudentService;
import com.lzf.d240819.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> listAll() {
        return studentMapper.selectList(null);
    }
}




