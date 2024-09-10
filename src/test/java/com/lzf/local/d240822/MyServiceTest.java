package com.lzf.local.d240822;

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
 * @author leizf
 * @since 2024-08-22 18:42
 */
@SpringBootTest
public class MyServiceTest {

    private MockedStatic<MyStringUtil> myStringUtilMockedStatic;

    @InjectMocks
    private MyService myService;

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
        myStringUtilMockedStatic = Mockito.mockStatic(MyStringUtil.class);
    }

    @After
    public void after() {
        myStringUtilMockedStatic.close();
    }

    @Test
    public void testMyService() {
        // mock
        Mockito.when(MyStringUtil.toLength3(Mockito.any())).thenReturn("OK");
        // assert
        String result = myService.getLanguage("EN");
        Assertions.assertEquals("OK", result);
    }
}
