package com.sina.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test01 {
    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);
    }
}
