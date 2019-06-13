package com.nekolr.fish.controller;

import com.nekolr.fish.entity.Permission;
import com.nekolr.fish.exception.BadRequestException;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.PermissionService;
import com.nekolr.fish.service.dto.PermissionDTO;
import com.nekolr.fish.support.I18nUtils;
import com.nekolr.fish.util.TreeUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private I18nUtils i18nUtils;

    @Log("获取权限列表")
    @GetMapping("/permissions")
    @PreAuthorize("hasAnyAuthority('PERMISSION_ALL', 'PERMISSION_SELECT')")
    public ResponseEntity<List<PermissionDTO>> getPermissionList() {
        return new ResponseEntity(TreeUtils.toTree(permissionService.findAll()), HttpStatus.OK);
    }

    @Log("获取权限信息")
    @GetMapping("/permissions/{id}")
    @PreAuthorize("hasAnyAuthority('PERMISSION_ALL', 'PERMISSION_SELECT')")
    public ResponseEntity<PermissionDTO> getPermission(@PathVariable String id) {
        if (NumberUtils.isDigits(id)) {
            return new ResponseEntity(permissionService.findById(Long.valueOf(id)), HttpStatus.OK);
        } else {
            throw new BadRequestException(i18nUtils.getMessage("exceptions.bad_request"));
        }
    }

    @Log("创建权限")
    @PostMapping("/permissions")
    @PreAuthorize("hasAnyAuthority('PERMISSION_ALL', 'PERMISSION_POST')")
    public ResponseEntity<PermissionDTO> createPermission(@Validated @RequestBody Permission permission) {
        PermissionDTO entity = permissionService.savePermission(permission);
        return ResponseEntity.ok(entity);
    }

    @Log("更新权限信息")
    @PutMapping("/permissions")
    @PreAuthorize("hasAnyAuthority('PERMISSION_ALL', 'PERMISSION_PUT')")
    public ResponseEntity<PermissionDTO> updatePermission(@Validated @RequestBody Permission permission) {
        PermissionDTO result = permissionService.updatePermission(permission);
        return ResponseEntity.ok(result);
    }

    @Log("删除权限")
    @DeleteMapping("/permissions/{id}")
    @PreAuthorize("hasAnyAuthority('PERMISSION_ALL', 'PERMISSION_DELETE')")
    public ResponseEntity deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
