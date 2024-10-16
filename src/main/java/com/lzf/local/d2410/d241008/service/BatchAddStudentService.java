package com.lzf.local.d2410.d241008.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzf.d240819.entity.Student;

/**
 * @author leizefeng
 */
public interface BatchAddStudentService extends IService<Student> {

  void batchAdd();
}
