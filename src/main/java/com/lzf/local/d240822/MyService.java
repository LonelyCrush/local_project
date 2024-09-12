package com.lzf.local.d240822;

import org.springframework.stereotype.Service;

/**
 * @author leizf
 * @since 2024-08-22 18:40
 */
@Service
public class MyService {

    public String getLanguage(String language) {
        String result = MyStringUtil.toLength3(language);
        System.out.println(result);
        return result;
    }
}
