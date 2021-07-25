package com.school.studentmanagementservice.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Utils {
    public String randomChar(int length) {
        char[] chars = new char[length];
        String string = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789!@#$%^&*?-+=(){}|[];',./<>";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            chars[i] = string.charAt(random.nextInt(string.length()));
        }
        return new String(chars);
    }
}
