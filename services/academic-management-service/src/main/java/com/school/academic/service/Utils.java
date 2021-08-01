package com.school.academic.service;

import com.school.academic.proxy.UserServiceProxy;
import com.school.academic.proxy.UserType;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
public class Utils {
    private static final String USER_ID = "userId";
    private static final String LOGIN_ID = "loginId";
    private final UserServiceProxy proxy;
    private static  final Logger LOGGER= LoggerFactory.getLogger(Utils.class);

    public boolean checkUserPermission(String userId) {
        UserType userType = proxy.checkUserType(userId, LOGIN_ID);
        LOGGER.info("User Type===>"+userType);
        if (userType == UserType.ACADEMIC_MANGER
                || userType == UserType.TEACHER
                || userType == UserType.SYS_ADMIN
                || userType == UserType.SCHOOL) {
            return true;
        }
        return false;
    }

    public boolean checkValidStudent(UUID obj) {
        UserType userType = proxy.checkUserType(obj.toString(), USER_ID);
        if (userType == UserType.STUDENT){
            return true;
        }
        return false;
    }

    public boolean checkValidTeacher(UUID obj) {
        UserType userType = proxy.checkUserType(obj.toString(), USER_ID);
        if (userType == UserType.TEACHER){
            return true;
        }
        return false;
    }
}
