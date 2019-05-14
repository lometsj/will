package com.lome.will.demo.Repo;

import com.lome.will.demo.Entity.Will;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WillRepo extends JpaRepository<Will, Long> {
    Will findByUserName(String UserName);
    Will findByUserNameAndPwd(String UserName, String Pwd);
}
