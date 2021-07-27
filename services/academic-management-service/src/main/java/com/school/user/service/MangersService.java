package com.school.user.service;

import com.school.user.dto.Staff;
import com.school.user.dto.UserMapping;
import com.school.user.dto.UserType;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateLoginRequest;
import com.school.user.http.dto.CreateManger;
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
public class MangersService {

    private final AuthProxy authProxy;
    private final IUserMappingRepo iUserMappingRepo;
    private final ModelMapper modelMapper;
    private final IStaffRepo iStaffRepo;
    private final Utils utils;
    private final SchoolService schoolService;

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

    public String insertManger(CreateManger createStaff,String userId) {
        UserMapping userMapping = new UserMapping();
        try {
            userMapping.setUserType(createStaff.getUserType());
            Staff staff = iStaffRepo.save(modelMapper.map(createStaff, Staff.class));
            if (staff != null) {
                CreateLoginRequest createLoginRequestRequest = new CreateLoginRequest();
                createLoginRequestRequest.setEmail(staff.getEmail());
                String name=staff.getFirstName();
                name=name+(staff.getMiddleName()!=null?" "+staff.getMiddleName():"");
                name=name+(staff.getLastName()!=null?" "+staff.getLastName():"");
                createLoginRequestRequest.setName(name);
                createLoginRequestRequest.setUserName(createStaff.getUserName());
                createLoginRequestRequest.setPassword(utils.randomChar(8));
                String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
                if (createLoginResponse != null) {
                    userMapping.setLogin(UUID.fromString(createLoginResponse));
                    userMapping.setUserId(staff.getUuid());
                    userMapping.setSchool(schoolService.findSchoolForUser(userId));
                    iUserMappingRepo.save(userMapping);
                    return createLoginResponse;
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
