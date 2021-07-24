package com.skyhawks.authentication.repos;

import com.skyhawks.authentication.model.LoginUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepo extends CrudRepository<LoginUser, UUID> {

    @Query("FROM LoginUser WHERE userName = :userName")
    LoginUser findByUserName(String userName);
}
