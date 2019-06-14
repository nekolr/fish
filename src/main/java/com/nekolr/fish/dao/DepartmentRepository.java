package com.nekolr.fish.dao;

import com.nekolr.fish.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor {

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    Department findByName(String name);
}
