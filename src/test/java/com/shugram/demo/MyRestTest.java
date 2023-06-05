package com.shugram.demo;

import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.mapper.StudentMapper;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class MyRestTest {
    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void testSelect(){
        System.out.println(("----- selectAll method test -----"));
        List<StudentEntity> stuList=studentMapper.selectList(null);
        stuList.forEach(System.out::println);
    }
}
