package com.nekolr.fish.service;

import com.nekolr.fish.entity.Job;
import com.nekolr.fish.service.dto.JobDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "job")
public interface JobService {

    /**
     * 获取所有部门列表
     *
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<JobDTO> findAll();

    /**
     * 根据 ID 查询
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'id:' + #p0")
    JobDTO findById(Long id);

    /**
     * 保存职务
     *
     * @param job
     * @return
     */
    @CacheEvict(allEntries = true)
    JobDTO saveJob(Job job);

    /**
     * 更新职务
     *
     * @param job
     * @return
     */
    @CacheEvict(allEntries = true)
    JobDTO updateJob(Job job);

    /**
     * 删除职务
     *
     * @param id
     * @return
     */
    @CacheEvict(allEntries = true)
    void deleteJob(Long id);
}
