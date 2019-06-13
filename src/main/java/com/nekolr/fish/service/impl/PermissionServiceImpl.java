package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.PermissionRepository;
import com.nekolr.fish.entity.Permission;
import com.nekolr.fish.exception.EntityExistException;
import com.nekolr.fish.service.PermissionService;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.dto.PermissionDTO;
import com.nekolr.fish.service.mapper.PermissionMapper;
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
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Resource
    private PermissionMapper permissionMapper;
    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public List<CommonDTO> findAll() {
        return permissionRepository.findAll().stream().map(permission -> permissionMapper.toDto(permission)).collect(Collectors.toList());
    }

    @Override
    public PermissionDTO findById(Long id) {
        return permissionMapper.toDto(permissionRepository.findById(id).get());
    }

    @Override
    public PermissionDTO savePermission(Permission source) {

        if (ObjectUtils.isNotEmpty(permissionRepository.findByName(source.getName()))) {
            throw new EntityExistException(messageGenerator.generateEntityExistMessage(Permission.class, "name", source.getName()));
        }

        Permission permission = permissionRepository.save(source);
        return permissionMapper.toDto(permission);
    }

    @Override
    public PermissionDTO updatePermission(Permission source) {

        Permission entity = permissionRepository.findById(source.getId()).get();
        entity.setName(source.getName());
        entity.setDescription(source.getDescription());
        entity.setPid(source.getPid());
        entity.setSort(source.getSort());

        Permission permission = permissionRepository.save(entity);
        return permissionMapper.toDto(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
