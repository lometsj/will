package com.lome.will.demo.Repo;

import com.lome.will.demo.Entity.NoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoInfoRepo extends JpaRepository<NoInfo, Long> {

    NoInfo findByUserId(Long userId);
}
