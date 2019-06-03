package com.nekolr.fish.controller;

import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.RoleService;
import com.nekolr.fish.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 *
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Log("获取角色列表")
    @GetMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_ALL', 'ROLE_SELECT')")
    public ResponseEntity<List<RoleDTO>> getDepartments() {
        return new ResponseEntity(roleService.findAll(), HttpStatus.OK);
    }
}
