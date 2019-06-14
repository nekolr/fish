package com.nekolr.fish.service.impl;


import com.nekolr.fish.dao.MenuRepository;
import com.nekolr.fish.entity.Menu;
import com.nekolr.fish.entity.Permission;
import com.nekolr.fish.exception.EntityExistException;
import com.nekolr.fish.service.MenuService;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.dto.MenuDTO;
import com.nekolr.fish.service.mapper.MenuMapper;
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
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Resource
    private MenuMapper menuMapper;
    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public List<MenuDTO> findAllByRoleId(Long id) {
        return menuMapper.toDto(menuRepository.findAllByRoles_Id(id));
    }

    @Override
    public List<CommonDTO> findAllByRoleIds(List<Long> ids) {
        return menuRepository.findAllByRoleIds(ids).stream().map(menu -> menuMapper.toDto(menu)).collect(Collectors.toList());
    }

    @Override
    public List<CommonDTO> findAll() {
        return menuRepository.findAll().stream().map(menu -> menuMapper.toDto(menu)).collect(Collectors.toList());
    }

    @Override
    public MenuDTO findById(Long id) {
        return menuMapper.toDto(menuRepository.findById(id).get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MenuDTO saveMenu(Menu source) {
        if (ObjectUtils.isNotEmpty(menuRepository.findByName(source.getName()))) {
            throw new EntityExistException(messageGenerator.generateEntityExistMessage(Permission.class, "name", source.getName()));
        }

        Menu menu = menuRepository.save(source);
        return menuMapper.toDto(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MenuDTO updateMenu(Menu source) {
        Menu entity = menuRepository.findById(source.getId()).get();
        entity.setIcon(source.getIcon());
        entity.setName(source.getName());
        entity.setOutside(source.getOutside());
        entity.setPath(source.getPath());
        entity.setPid(source.getPid());
        entity.setSort(source.getSort());

        Menu menu = menuRepository.save(entity);
        return menuMapper.toDto(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
