package com.nekolr.fish.controller;

import com.nekolr.fish.entity.Department;
import com.nekolr.fish.exception.BadRequestException;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.DepartmentService;
import com.nekolr.fish.service.dto.DepartmentDTO;
import com.nekolr.fish.service.dto.MenuDTO;
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
 * 部门控制器
 *
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private I18nUtils i18nUtils;

    @Log("获取部门列表")
    @GetMapping("/departments")
    @PreAuthorize("hasAnyAuthority('DEPARTMENT_ALL', 'DEPARTMENT_SELECT')")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        return new ResponseEntity(TreeUtils.toTree(departmentService.findAll()), HttpStatus.OK);
    }

    @Log("获取部门信息")
    @GetMapping("/departments/{id}")
    @PreAuthorize("hasAnyAuthority('DEPARTMENT_ALL', 'DEPARTMENT_SELECT')")
    public ResponseEntity<MenuDTO> getDepartment(@PathVariable String id) {
        if (NumberUtils.isDigits(id)) {
            return new ResponseEntity(departmentService.findById(Long.valueOf(id)), HttpStatus.OK);
        } else {
            throw new BadRequestException(i18nUtils.getMessage("exceptions.bad_request"));
        }
    }

    @Log("创建部门")
    @PostMapping("/departments")
    @PreAuthorize("hasAnyAuthority('DEPARTMENT_ALL', 'DEPARTMENT_POST')")
    public ResponseEntity<DepartmentDTO> createDepartment(@Validated @RequestBody Department department) {
        DepartmentDTO entity = departmentService.saveDepartment(department);
        return ResponseEntity.ok(entity);
    }

    @Log("更新部门信息")
    @PutMapping("/departments")
    @PreAuthorize("hasAnyAuthority('DEPARTMENT_ALL', 'DEPARTMENT_PUT')")
    public ResponseEntity<DepartmentDTO> updateDepartment(@Validated @RequestBody Department department) {
        DepartmentDTO result = departmentService.updateDepartment(department);
        return ResponseEntity.ok(result);
    }

    @Log("删除部门")
    @DeleteMapping("/departments/{id}")
    @PreAuthorize("hasAnyAuthority('DEPARTMENT_ALL', 'DEPARTMENT_DELETE')")
    public ResponseEntity deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
