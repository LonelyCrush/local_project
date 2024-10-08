package com.lzf.d240819.mapper;

import com.lzf.d240819.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * @Entity com.lzf.d240819.entity.Student
 */
public interface StudentMapper extends BaseMapper<Student> {

  void batchAdd(List<Student> studentList);
}




