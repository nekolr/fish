package com.nekolr.fish.controller;

import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.DepartmentService;
import com.nekolr.fish.service.dto.DepartmentDTO;
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
 * 部门控制器
 *
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Log("获取部门列表")
    @GetMapping("/departments")
    @PreAuthorize("hasAnyAuthority('DEPARTMENT_ALL', 'DEPARTMENT_SELECT')")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        return new ResponseEntity(TreeUtils.toTree(departmentService.findAll()), HttpStatus.OK);
    }

}
