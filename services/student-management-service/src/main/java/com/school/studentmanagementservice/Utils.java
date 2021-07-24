package com.school.studentmanagementservice;

import com.school.studentmanagementservice.exception.NotValidInputException;

public class Utils {
    public static void basicInputValidation(String field) throws NotValidInputException {
        if(field==null||field.isEmpty()){
            throw new NotValidInputException(String.format("Invalid request %s",field));
        }
    }
    public static boolean getBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

}
