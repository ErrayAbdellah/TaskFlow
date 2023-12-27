package com.taskflow.taskflow.repository;

import com.taskflow.taskflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IUserRepo extends JpaRepository<User, UUID> {

}
