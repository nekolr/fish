package com.nekolr.fish.service;

import com.nekolr.fish.entity.Department;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.dto.DepartmentDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "department")
public interface DepartmentService {

    /**
     * 获取所有部门列表
     *
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<CommonDTO> findAll();

    /**
     * 根据 ID 查询
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'id:' + #p0")
    DepartmentDTO findById(Long id);

    /**
     * 保存部门
     *
     * @param department
     * @return
     */
    @CacheEvict(allEntries = true)
    DepartmentDTO saveDepartment(Department department);

    /**
     * 更新部门
     *
     * @param department
     * @return
     */
    @CacheEvict(allEntries = true)
    DepartmentDTO updateDepartment(Department department);

    /**
     * 删除部门
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void deleteDepartment(Long id);
}
