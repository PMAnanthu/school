package com.school.user.filters;

import com.school.user.dto.UserMapping;
import com.school.user.dto.UserType;
import com.school.user.service.UserMappingService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Component
@Slf4j
public class SchoolFilter implements Filter {
    private static final Logger LOGGER= LoggerFactory.getLogger(SchoolFilter.class);
    private final UserMappingService mappingService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
            ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        request.getHeaderNames().asIterator().forEachRemaining(name->System.out.println(name));
        if(request.getHeader("school")!=null){
            LOGGER.info("by pass url for has school code");
            chain.doFilter(request,response);
        }
        if(request.getRequestURI().contains("admin")){
            LOGGER.info("by pass url for admin");
            chain.doFilter(request,response);
        }
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
        }
    }
}
