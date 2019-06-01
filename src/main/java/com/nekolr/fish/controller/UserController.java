package com.nekolr.fish.controller;

import com.nekolr.fish.entity.User;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.UserService;
import com.nekolr.fish.service.dto.UserDTO;
import com.nekolr.fish.service.mapper.UserMapper;
import com.nekolr.fish.service.query.UserQueryService;
import com.nekolr.fish.support.FishSecurityContextHolder;
import com.nekolr.fish.support.PageRequest;
import com.nekolr.fish.vo.PageVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private FishSecurityContextHolder securityContextHolder;

    @Log("获取用户列表")
    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER_ALL', 'USER_SELECT')")
    public ResponseEntity<PageVO> getUsers(UserDTO userDTO, PageRequest pageRequest) {
        return new ResponseEntity(userQueryService.queryAll(userDTO, pageRequest.toPageable()), HttpStatus.OK);
    }

    @Log("获取用户信息")
    @GetMapping("/users/{usernameOrId}")
    @PreAuthorize("hasAnyAuthority('USER_ALL', 'USER_SELECT')")
    public ResponseEntity<UserDTO> getUser(@PathVariable String usernameOrId) {
        if (NumberUtils.isDigits(usernameOrId)) {
            return new ResponseEntity(userMapper.toDto(userService.findById(Long.valueOf(usernameOrId))), HttpStatus.OK);
        } else {
            return new ResponseEntity(userMapper.toDto(userService.findByUsername(usernameOrId)), HttpStatus.OK);
        }
    }

    @Log("获取当前用户信息")
    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return new ResponseEntity(userMapper.toDto(userService.findByUsername(securityContextHolder.getUserDetails().getUsername())), HttpStatus.OK);
    }

    @Log("创建用户")
    @PostMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER_ALL', 'USER_POST')")
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody User user) {
        UserDTO entity = userService.saveUser(user);
        return ResponseEntity.ok(entity);
    }

    @Log("更新用户信息")
    @PutMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER_ALL', 'USER_PUT')")
    public ResponseEntity<UserDTO> updateUser(@Validated @RequestBody User user) {
        UserDTO entity = userService.saveUser(user);
        return ResponseEntity.ok(entity);
    }

    @Log("删除用户")
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAnyAuthority('USER_ALL', 'USER_DELETE')")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
