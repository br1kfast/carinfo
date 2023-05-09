package com.wjf.user.repository;

import com.wjf.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


/**
 * @author JunFei
 * @data
 * @purpose
 */


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u from User u where u.userUid = :userUid")
    Optional<User> findByUserUid(@Param("userUid") int userUid);

    @Query(value = "select u from User u where u.name = :name")
    Optional<User> findByUserName(@Param("name") String name);

}
