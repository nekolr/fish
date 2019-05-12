package com.nekolr.fish.controller;

import com.nekolr.fish.service.dto.UserDTO;
import com.nekolr.fish.service.query.UserQueryService;
import com.nekolr.fish.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserQueryService userQueryService;

    @GetMapping("/users")
    public ResponseEntity<PageVO> getUsers(UserDTO userDTO, Pageable pageable) {
        return new ResponseEntity(userQueryService.queryAll(userDTO, pageable), HttpStatus.OK);
    }

}
