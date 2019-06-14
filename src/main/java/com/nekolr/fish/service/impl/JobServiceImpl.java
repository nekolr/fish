package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.JobRepository;
import com.nekolr.fish.entity.Job;
import com.nekolr.fish.entity.Permission;
import com.nekolr.fish.exception.EntityExistException;
import com.nekolr.fish.service.JobService;
import com.nekolr.fish.service.dto.JobDTO;
import com.nekolr.fish.service.mapper.JobMapper;
import com.nekolr.fish.support.MessageGenerator;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Resource
    private JobMapper jobMapper;
    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public List<JobDTO> findAll() {
        return jobMapper.toDto(jobRepository.findAll());
    }

    @Override
    public JobDTO findById(Long id) {
        return jobMapper.toDto(jobRepository.findById(id).get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JobDTO saveJob(Job source) {
        if (ObjectUtils.isNotEmpty(jobRepository.findByName(source.getName()))) {
            throw new EntityExistException(messageGenerator.generateEntityExistMessage(Permission.class, "name", source.getName()));
        }

        Job job = jobRepository.save(source);
        return jobMapper.toDto(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JobDTO updateJob(Job source) {
        Job entity = jobRepository.findById(source.getId()).get();
        entity.setEnabled(source.getEnabled());
        entity.setName(source.getName());
        entity.setSort(source.getSort());
        entity.setDescription(source.getDescription());
        entity.setDepartment(source.getDepartment());

        Job job = jobRepository.save(entity);
        return jobMapper.toDto(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
