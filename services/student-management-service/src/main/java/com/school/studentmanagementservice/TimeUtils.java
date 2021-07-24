/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 30/06/21
*/
package com.school.studentmanagementservice;

import java.time.LocalDate;

public class TimeUtils {


    public static LocalDate getDate(String date) {
        return LocalDate.parse(date);
    }
}
