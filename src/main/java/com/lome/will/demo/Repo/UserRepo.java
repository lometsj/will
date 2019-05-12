package com.lome.will.demo.Repo;

import com.lome.will.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);

}
