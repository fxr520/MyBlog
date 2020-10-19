package com.fxr.myblog;

import com.fxr.myblog.po.Type;
import com.fxr.myblog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    private TypeService typeService;

    @Test
    @Transactional
    void contextloads() {
        Type type = typeService.getType(1L);
        System.out.println(type);
    }

}
