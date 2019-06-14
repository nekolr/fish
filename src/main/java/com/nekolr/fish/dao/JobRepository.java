package com.nekolr.fish.dao;

import com.nekolr.fish.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor {

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    Job findByName(String name);
}
