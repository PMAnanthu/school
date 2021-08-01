package com.school.academic.service;

import com.school.academic.dto.TimeTable;
import com.school.academic.exception.NotAuthorizedException;
import com.school.academic.http.dto.TimeTableRequest;
import com.school.academic.repo.ITimeTableRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class TimeTableService {
    private final ITimeTableRepo repo;
    private final ModelMapper mapper;
    private final Utils utils;

    public List<TimeTable> findAll() {
        List<TimeTable> timeTables = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(TimeTable -> timeTables.add(TimeTable));
        return timeTables;
    }

    public TimeTable findById(String uuid) {
        return repo.findById(UUID.fromString(uuid)).get();
    }

    public String create(TimeTableRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            TimeTable timeTable = mapper.map(request, TimeTable.class);
            value = repo.save(timeTable).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }


    public String update(TimeTableRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            TimeTable timeTable = mapper.map(request, TimeTable.class);
            value = repo.save(timeTable).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }

}
