package com.lome.will.demo.Repo;

import com.lome.will.demo.Entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficeRepo extends JpaRepository<Office, Long> {
    Office findByName(String name);
}
