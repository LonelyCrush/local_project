package com.lzf.local.d2503.d250303.service;

import com.lzf.local.d2408.d240822.MyStringUtil;
import com.lzf.local.d2503.d250303.service.impl.JenServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author leizefeng
 */
@SpringBootTest
public class JenServiceImplTest {

  @InjectMocks
  private JenServiceImpl jenService;

  @Before
  public void before() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testMyService() {
    // assert
    String result = jenService.hello();
    Assertions.assertEquals("Hello, Jen", result);
  }
}
