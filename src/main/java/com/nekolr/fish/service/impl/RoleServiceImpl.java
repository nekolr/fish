package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.RoleRepository;
import com.nekolr.fish.entity.Role;
import com.nekolr.fish.exception.EntityExistException;
import com.nekolr.fish.service.RoleService;
import com.nekolr.fish.service.dto.RoleDTO;
import com.nekolr.fish.service.mapper.RoleMapper;
import com.nekolr.fish.support.MessageGenerator;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Resource
    private RoleMapper roleMapper;
    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public List<Role> findByUsername(String username) {
        return new ArrayList<>(roleRepository.findByUsers_Username(username));
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleMapper.toDto(roleRepository.findAll());
    }

    @Override
    public RoleDTO findById(Long id) {
        return roleMapper.toDto(roleRepository.findById(id).get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleDTO saveRole(Role source) {
        if (ObjectUtils.isNotEmpty(roleRepository.findByName(source.getName()))) {
            throw new EntityExistException(messageGenerator.generateEntityExistMessage(Role.class, "name", source.getName()));
        }

        Role role = roleRepository.save(source);
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleDTO updateRole(Role source) {
        Role entity = roleRepository.findById(source.getId()).get();
        entity.setDescription(source.getDescription());
        entity.setName(source.getName());
        entity.setSort(source.getSort());
        entity.setMenus(source.getMenus());
        entity.setPermissions(source.getPermissions());

        Role role = roleRepository.save(entity);
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> ids) {
        List<Role> roles = ids.stream().map(id -> {
            Role role = new Role();
            role.setId(id);
            return role;
        }).collect(Collectors.toList());
        roleRepository.deleteInBatch(roles);
    }
}
