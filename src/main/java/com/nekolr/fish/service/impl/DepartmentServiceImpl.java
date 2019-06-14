package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.DepartmentRepository;
import com.nekolr.fish.entity.Department;
import com.nekolr.fish.entity.Permission;
import com.nekolr.fish.exception.EntityExistException;
import com.nekolr.fish.service.DepartmentService;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.dto.DepartmentDTO;
import com.nekolr.fish.service.mapper.DepartmentMapper;
import com.nekolr.fish.support.MessageGenerator;
import org.apache.commons.lang3.ObjectUtils;
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
    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public List<CommonDTO> findAll() {
        return departmentRepository.findAll().stream().map(dept -> departmentMapper.toDto(dept)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        return departmentMapper.toDto(departmentRepository.findById(id).get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DepartmentDTO saveDepartment(Department source) {
        if (ObjectUtils.isNotEmpty(departmentRepository.findByName(source.getName()))) {
            throw new EntityExistException(messageGenerator.generateEntityExistMessage(Permission.class, "name", source.getName()));
        }

        Department department = departmentRepository.save(source);
        return departmentMapper.toDto(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DepartmentDTO updateDepartment(Department source) {
        Department entity = departmentRepository.findById(source.getId()).get();
        entity.setDescription(source.getDescription());
        entity.setEnabled(source.getEnabled());
        entity.setName(source.getName());
        entity.setPid(source.getPid());
        entity.setSort(source.getSort());

        Department department = departmentRepository.save(entity);
        return departmentMapper.toDto(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
