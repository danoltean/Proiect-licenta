package com.dan.ticketing.repo;

import com.dan.ticketing.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByFirstname(String firstname);

    @Query("SELECT u FROM User u WHERE u.username = :param")
    User findByUsername(@Param("param") String username);
}

