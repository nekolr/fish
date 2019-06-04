package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.JobRepository;
import com.nekolr.fish.service.JobService;
import com.nekolr.fish.service.dto.JobDTO;
import com.nekolr.fish.service.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Resource
    private JobMapper jobMapper;

    @Override
    public List<JobDTO> findAll() {
        return jobRepository.findAll().stream().map(job -> jobMapper.toDto(job)).collect(Collectors.toList());
    }
}
