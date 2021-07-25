package com.school.user.service;

import com.school.user.dto.Parent;
import com.school.user.dto.UserMapping;
import com.school.user.dto.UserType;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateLoginRequest;
import com.school.user.http.dto.CreateParent;
import com.school.user.proxy.AuthProxy;
import com.school.user.repo.IParentRepo;
import com.school.user.repo.IUserMappingRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class ParentService {

    private final AuthProxy authProxy;
    private final IUserMappingRepo iUserMappingRepo;
    private final ModelMapper modelMapper;
    private final IParentRepo parentRepo;
    private final Utils utils;

    public List<Parent> findAll() {
        List<Parent> parents = new ArrayList<>();
        parentRepo.findAll().iterator().forEachRemaining(parent -> parents.add(parent));
        return parents;
    }


    public Parent findParent(UUID uuid) throws UserNotFountException {
        Parent parent = parentRepo.findByUserId(uuid);
        if (parent == null)
            throw new UserNotFountException("Parent Not Found with for ID " + uuid.toString());
        return parent;
    }

    public String insertParent(CreateParent createParent) {
        UserMapping userMapping = new UserMapping();
        try {
            userMapping.setUserType(UserType.PARENT);
            Parent parent = parentRepo.save(modelMapper.map(createParent, Parent.class));
            if (parent != null) {
                CreateLoginRequest createLoginRequestRequest = new CreateLoginRequest();
                createLoginRequestRequest.setEmail(parent.getEmail());
                createLoginRequestRequest.setName(parent.getFirstName() + " " + parent.getMiddleName() != null ?
                        parent.getMiddleName() : "" + " " +
                        parent.getLastName() != null ? parent.getLastName() : "");
                createLoginRequestRequest.setUserName(createParent.getUserName());
                createLoginRequestRequest.setPassword(utils.randomChar(8));
                String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
                if (createLoginResponse != null) {
                    userMapping.setLogin(UUID.fromString(createLoginResponse));
                    userMapping.setUserId(parent.getUuid());
                    iUserMappingRepo.save(userMapping);
                    return parent.getUuid().toString();
                } else {
                    parentRepo.delete(parent);
                }
            }
            return "Unable to create login";
        } catch (Exception e) {
            throw e;
        }
    }
}
