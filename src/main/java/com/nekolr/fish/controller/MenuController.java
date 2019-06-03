package com.nekolr.fish.controller;

import com.nekolr.fish.entity.Role;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.MenuService;
import com.nekolr.fish.service.RoleService;
import com.nekolr.fish.service.dto.MenuDTO;
import com.nekolr.fish.support.FishSecurityContextHolder;
import com.nekolr.fish.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单控制器
 *
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FishSecurityContextHolder securityContextHolder;

    @Log("获取菜单列表")
    @GetMapping("/currentUserMenus")
    public ResponseEntity<List<MenuDTO>> getCurrentUserMenuList() {
        List<Role> roleSet = roleService.findByUsername(securityContextHolder.getUserDetails().getUsername());
        if (roleSet.size() > 0) {
            List<Long> roleIds = roleSet.stream().map(role -> role.getId()).collect(Collectors.toList());
            return new ResponseEntity(TreeUtils.toTree(menuService.findAllByRoleIds(roleIds)), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ArrayList(), HttpStatus.OK);
        }
    }
}
