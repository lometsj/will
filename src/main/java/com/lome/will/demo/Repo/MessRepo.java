package com.lome.will.demo.Repo;

import com.lome.will.demo.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessRepo extends JpaRepository<Message, Long> {
    List<Message> findAllByUserId(Long userId);
}
