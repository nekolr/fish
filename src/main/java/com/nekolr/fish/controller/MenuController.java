package com.nekolr.fish.controller;

import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.MenuService;
import com.nekolr.fish.service.dto.MenuDTO;
import com.nekolr.fish.util.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Log("获取菜单列表")
    @GetMapping("/menus")
    @PreAuthorize("hasAnyAuthority('MENU_ALL', 'MENU_SELECT')")
    public ResponseEntity<List<MenuDTO>> getUsers(@RequestParam Long roleId) {
        return new ResponseEntity(MenuUtils.menus2Tree(menuService.findAllByRoleId(roleId)), HttpStatus.OK);
    }
}
