package com.school.studentmanagementservice.service;

import com.school.studentmanagementservice.dto.Staff;
import com.school.studentmanagementservice.dto.UserMapping;
import com.school.studentmanagementservice.dto.UserType;
import com.school.studentmanagementservice.exception.UserNotFountException;
import com.school.studentmanagementservice.http.dto.CreateLoginRequest;
import com.school.studentmanagementservice.http.dto.CreateManger;
import com.school.studentmanagementservice.proxy.AuthProxy;
import com.school.studentmanagementservice.repo.IStaffRepo;
import com.school.studentmanagementservice.repo.IUserMappingRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
public class MangersService {

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
            throw new UserNotFountException("Staff Not Found with for ID " + uuid.toString());
        return staff;
    }

    public String insertStaff(CreateManger createStaff) {
        UserMapping userMapping = new UserMapping();
        try {
            userMapping.setUserType(createStaff.getUserType());
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
                    userMapping.setSchool(UUID.fromString(createStaff.getSchool()));
                    iUserMappingRepo.save(userMapping);
                    return "Staff Created " + staff.getUuid();
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
