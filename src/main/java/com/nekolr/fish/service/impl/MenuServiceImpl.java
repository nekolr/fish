package com.nekolr.fish.service.impl;


import com.nekolr.fish.dao.MenuRepository;
import com.nekolr.fish.service.MenuService;
import com.nekolr.fish.service.dto.MenuDTO;
import com.nekolr.fish.service.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuDTO> findAllByRoleId(Long id) {
        return menuRepository.findAllByRoles_Id(id).stream().map(menu -> menuMapper.toDto(menu)).collect(Collectors.toList());
    }

    @Override
    public List<MenuDTO> findAllByRoleIds(List<Long> ids) {
        return menuRepository.findAllByRoleIds(ids).stream().map(menu -> menuMapper.toDto(menu)).collect(Collectors.toList());
    }
}
