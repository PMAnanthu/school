package com.school.user.service;

import com.school.user.dto.Staff;
import com.school.user.dto.UserMapping;
import com.school.user.dto.UserType;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateLoginRequest;
import com.school.user.http.dto.CreateStaff;
import com.school.user.proxy.AuthProxy;
import com.school.user.repo.IStaffRepo;
import com.school.user.repo.IUserMappingRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
public class TeachersService {

    private final AuthProxy authProxy;
    private final IUserMappingRepo iUserMappingRepo;
    private final ModelMapper modelMapper;
    private final IStaffRepo iStaffRepo;
    private final Utils utils;

    public List<Staff> findAll() {
        List<Staff> staffs = new ArrayList<>();
        iStaffRepo.findAll().iterator().forEachRemaining(staff -> staffs.add(staff));
        return staffs;
    }

    public List<Staff> findAllBySchool(UUID school) {
        List<UserMapping> studentsMap = iUserMappingRepo.findAllStudentBySchoolAndType(school, UserType.TEACHER);
        Iterable<UUID> iterable = studentsMap.stream().map(UserMapping::getUserId).collect(Collectors.toList());
        Iterable<Staff> iStaffs = iStaffRepo.findAllById(iterable);
        List<Staff> staffs = new ArrayList<>();
        iStaffs.iterator().forEachRemaining(staff -> staffs.add(staff));
        return staffs;
    }

    public Staff findStaff(UUID uuid) throws UserNotFountException {
        Staff staff = iStaffRepo.findByUserId(uuid);
        if (staff == null)
            throw new UserNotFountException("Teacher Not Found with for ID " + uuid.toString());
        return staff;
    }

    public String insertStaff(CreateStaff createStaff,String school) {
        UserMapping userMapping = new UserMapping();
        try {
            userMapping.setUserType(UserType.TEACHER);
            Staff staff = iStaffRepo.save(modelMapper.map(createStaff, Staff.class));
            if (staff != null) {
                CreateLoginRequest createLoginRequestRequest = new CreateLoginRequest();
                createLoginRequestRequest.setEmail(staff.getEmail());
                createLoginRequestRequest.setName(staff.getFirstName() + " " + staff.getMiddleName() != null ?
                        staff.getMiddleName() : "" + " " +
                        staff.getLastName() != null ? staff.getLastName() : "");
                createLoginRequestRequest.setUserName(createStaff.getUserName());
                createLoginRequestRequest.setPassword(utils.randomChar(8));
                String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
                if (createLoginResponse != null) {
                    userMapping.setLogin(UUID.fromString(createLoginResponse));
                    userMapping.setUserId(staff.getUuid());
                    userMapping.setSchool(UUID.fromString(school));
                    iUserMappingRepo.save(userMapping);
                    return staff.getUuid().toString();
                } else {
                    iStaffRepo.delete(staff);
                }
            }
            return "Unable to create login";
        } catch (Exception e) {
            throw e;
        }
    }
}