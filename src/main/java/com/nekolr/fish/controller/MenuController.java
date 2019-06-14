package com.nekolr.fish.controller;

import com.nekolr.fish.entity.Menu;
import com.nekolr.fish.entity.Role;
import com.nekolr.fish.exception.BadRequestException;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.MenuService;
import com.nekolr.fish.service.RoleService;
import com.nekolr.fish.service.dto.MenuDTO;
import com.nekolr.fish.support.FishSecurityContextHolder;
import com.nekolr.fish.support.I18nUtils;
import com.nekolr.fish.util.TreeUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private I18nUtils i18nUtils;

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

    @Log("获取菜单列表")
    @GetMapping("/menus")
    @PreAuthorize("hasAnyAuthority('MENU_ALL', 'MENU_SELECT')")
    public ResponseEntity<List<MenuDTO>> getMenuList() {
        return new ResponseEntity(TreeUtils.toTree(menuService.findAll()), HttpStatus.OK);
    }

    @Log("获取菜单信息")
    @GetMapping("/menus/{id}")
    @PreAuthorize("hasAnyAuthority('MENU_ALL', 'MENU_SELECT')")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable String id) {
        if (NumberUtils.isDigits(id)) {
            return new ResponseEntity(menuService.findById(Long.valueOf(id)), HttpStatus.OK);
        } else {
            throw new BadRequestException(i18nUtils.getMessage("exceptions.bad_request"));
        }
    }

    @Log("创建菜单")
    @PostMapping("/menus")
    @PreAuthorize("hasAnyAuthority('MENU_ALL', 'MENU_POST')")
    public ResponseEntity<MenuDTO> createMenu(@Validated @RequestBody Menu menu) {
        MenuDTO entity = menuService.saveMenu(menu);
        return ResponseEntity.ok(entity);
    }

    @Log("更新菜单信息")
    @PutMapping("/menus")
    @PreAuthorize("hasAnyAuthority('MENU_ALL', 'MENU_PUT')")
    public ResponseEntity<MenuDTO> updateMenu(@Validated @RequestBody Menu menu) {
        MenuDTO result = menuService.updateMenu(menu);
        return ResponseEntity.ok(result);
    }

    @Log("删除菜单")
    @DeleteMapping("/menus/{id}")
    @PreAuthorize("hasAnyAuthority('MENU_ALL', 'MENU_DELETE')")
    public ResponseEntity deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
