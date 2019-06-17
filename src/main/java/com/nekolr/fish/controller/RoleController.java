package com.nekolr.fish.controller;

import com.nekolr.fish.entity.Role;
import com.nekolr.fish.exception.BadRequestException;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.RoleService;
import com.nekolr.fish.service.dto.RoleDTO;
import com.nekolr.fish.service.query.RoleQueryService;
import com.nekolr.fish.support.I18nUtils;
import com.nekolr.fish.support.PageRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private RoleQueryService roleQueryService;
    @Autowired
    private I18nUtils i18nUtils;

    @Log("获取角色列表")
    @GetMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_ALL', 'ROLE_SELECT')")
    public ResponseEntity getRoles(RoleDTO role, PageRequest pageRequest, @RequestParam(required = false) String paging) {
        if (StringUtils.isNotBlank(paging) && Boolean.TRUE.equals(Boolean.valueOf(paging))) {
            return new ResponseEntity(roleQueryService.queryAll(role, pageRequest.toPageable()), HttpStatus.OK);
        } else {
            return new ResponseEntity(roleService.findAll(), HttpStatus.OK);
        }
    }

    @Log("获取角色信息")
    @GetMapping("/roles/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ALL', 'ROLE_SELECT')")
    public ResponseEntity<RoleDTO> getRole(@PathVariable String id) {
        if (NumberUtils.isDigits(id)) {
            return new ResponseEntity(roleService.findById(Long.valueOf(id)), HttpStatus.OK);
        } else {
            throw new BadRequestException(i18nUtils.getMessage("exceptions.bad_request"));
        }
    }

    @Log("创建角色")
    @PostMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_ALL', 'ROLE_POST')")
    public ResponseEntity<RoleDTO> createRole(@Validated @RequestBody Role role) {
        RoleDTO entity = roleService.saveRole(role);
        return ResponseEntity.ok(entity);
    }

    @Log("更新角色信息")
    @PutMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_ALL', 'ROLE_PUT')")
    public ResponseEntity<RoleDTO> updateRole(@Validated @RequestBody Role role) {
        RoleDTO result = roleService.updateRole(role);
        return ResponseEntity.ok(result);
    }

    @Log("删除角色")
    @DeleteMapping("/roles/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ALL', 'ROLE_DELETE')")
    public ResponseEntity deleteRole(@PathVariable Long id) {
        if (id.equals(1L)) {
            throw new BadRequestException(i18nUtils.getMessage("exceptions.bad_request"));
        }
        roleService.deleteRole(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("批量删除角色")
    @DeleteMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_ALL', 'ROLE_DELETE')")
    public ResponseEntity batchDeleteRole(@RequestParam String ids) {
        String[] keys = StringUtils.split(ids, ",");
        List<Long> newKeys = new ArrayList<>(keys.length);
        Arrays.stream(keys).forEach(key -> {
            if (!NumberUtils.isDigits(key)) {
                throw new BadRequestException(i18nUtils.getMessage("exceptions.bad_request"));
            }
            if (!Long.valueOf(key).equals(1L)) {
                newKeys.add(Long.valueOf(key));
            }
        });
        roleService.deleteBatch(newKeys);
        return new ResponseEntity(HttpStatus.OK);
    }
}
