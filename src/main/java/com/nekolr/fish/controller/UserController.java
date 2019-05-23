package com.nekolr.fish.controller;

import com.nekolr.fish.entity.User;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.UserService;
import com.nekolr.fish.service.dto.UserDTO;
import com.nekolr.fish.service.mapper.UserMapper;
import com.nekolr.fish.service.query.UserQueryService;
import com.nekolr.fish.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
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

    @Log("获取用户列表")
    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER_ALL', 'USER_SELECT')")
    public ResponseEntity<PageVO> getUsers(UserDTO userDTO, Pageable pageable) {
        return new ResponseEntity(userQueryService.queryAll(userDTO, pageable), HttpStatus.OK);
    }

    @Log("获取用户信息")
    @GetMapping("/users/{username}")
    @PreAuthorize("hasAnyAuthority('USER_ALL', 'USER_SELECT')")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        return new ResponseEntity(userMapper.toDto(userService.findByUsername(username)), HttpStatus.OK);
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
