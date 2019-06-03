package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.RoleRepository;
import com.nekolr.fish.entity.Role;
import com.nekolr.fish.service.RoleService;
import com.nekolr.fish.service.dto.RoleDTO;
import com.nekolr.fish.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByUsername(String username) {
        return new ArrayList<>(roleRepository.findByUsers_Username(username));
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().map(role -> roleMapper.toDto(role)).collect(Collectors.toList());
    }
}
