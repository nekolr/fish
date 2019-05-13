package com.nekolr.fish.controller;

import com.nekolr.fish.entity.User;
import com.nekolr.fish.service.UserService;
import com.nekolr.fish.service.dto.UserDTO;
import com.nekolr.fish.service.query.UserQueryService;
import com.nekolr.fish.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users")
    public ResponseEntity<PageVO> getUsers(UserDTO userDTO, Pageable pageable) {
        return new ResponseEntity(userQueryService.queryAll(userDTO, pageable), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@Validated @RequestBody User user) {
        UserDTO entity = userService.saveUser(user);
        return ResponseEntity.ok(entity);
    }

}
