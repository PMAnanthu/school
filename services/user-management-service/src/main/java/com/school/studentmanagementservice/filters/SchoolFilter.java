package com.school.studentmanagementservice.filters;

import com.school.studentmanagementservice.dto.UserMapping;
import com.school.studentmanagementservice.dto.UserType;
import com.school.studentmanagementservice.service.UserMappingService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Component
public class SchoolFilter implements Filter {
    private final UserMappingService mappingService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
            ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String userId=request.getHeader("userId");
        if(userId!=null) {
            UserMapping userMapping = mappingService.findUserLoginId(userId);
            if(userMapping!=null) {
                if(userMapping.getUserType()== UserType.ACADEMIC_MANGER
                        || userMapping.getUserType()==UserType.SYS_ADMIN
                        || userMapping.getUserType()==UserType.SCHOOL
                        || userMapping.getUserType()==UserType.FINANCE_MANGER){
                    request.setAttribute("school", userMapping.getSchool());
                    chain.doFilter(request,response);
                }else{
                    throw  new ServletException("User is not allowed to do this operation");
                }
            }else{
                throw  new ServletException("User is not a authorised User");
            }
        }else{
            throw  new ServletException("User is not authorised to do this operation");
        }
    }
}
