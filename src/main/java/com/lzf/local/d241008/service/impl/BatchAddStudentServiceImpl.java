package com.lzf.local.d241008.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzf.d240819.entity.Student;
import com.lzf.d240819.mapper.StudentMapper;
import com.lzf.local.d241008.service.BatchAddStudentService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.CollectionUtils;

/**
 * @author leizefeng
 */
public class BatchAddStudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements BatchAddStudentService {

  @Resource
  private StudentMapper studentMapper;

  @Resource
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public void batchAdd() {
    System.out.println("===== 开始插入数据 =====");
    long startTime = System.currentTimeMillis();
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      List<Student> studentList = new ArrayList<>();
      for (int i = 0; i < 300000; i++) {
        Student student = new Student();
        student.setName("姓名" + i).setAge(17 + i % 7).setBirth(LocalDateTime.now());
        studentList.add(student);
        if (i % 5000 == 0) {
          sqlSession.insert("batchAdd", studentList);
          sqlSession.commit();
          studentList.clear();
        }
      }
      if (!CollectionUtils.isEmpty(studentList)) {
        sqlSession.insert("batchAdd", studentList);
        sqlSession.commit();
      }
      long spendTime = System.currentTimeMillis()-startTime;
      System.out.println("成功插入 30 万条数据，耗时：" + spendTime / 1000 + "秒");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
