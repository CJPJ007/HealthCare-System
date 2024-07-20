package com.health.healthcare.repos;

import com.health.healthcare.domain.User;
import com.health.healthcare.domain.UserData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findFirstByUser(User user);

}
