package com.nekolr.fish.controller;

import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.PermissionService;
import com.nekolr.fish.service.dto.PermissionDTO;
import com.nekolr.fish.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制器
 *
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Log("获取权限列表")
    @GetMapping("/permissions")
    @PreAuthorize("hasAnyAuthority('PERMISSION_ALL', 'PERMISSION_SELECT')")
    public ResponseEntity<List<PermissionDTO>> getPermissionList() {
        return new ResponseEntity(TreeUtils.toTree(permissionService.findAll()), HttpStatus.OK);
    }
}
