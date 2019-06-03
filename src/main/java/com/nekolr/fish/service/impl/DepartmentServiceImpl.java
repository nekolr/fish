package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.DepartmentRepository;
import com.nekolr.fish.service.DepartmentService;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<CommonDTO> findAll() {
        return departmentRepository.findAll().stream().map(dept -> departmentMapper.toDto(dept)).collect(Collectors.toList());
    }
}
