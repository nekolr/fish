package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.PermissionRepository;
import com.nekolr.fish.service.PermissionService;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<CommonDTO> findAll() {
        return permissionRepository.findAll().stream().map(permission -> permissionMapper.toDto(permission)).collect(Collectors.toList());
    }
}
