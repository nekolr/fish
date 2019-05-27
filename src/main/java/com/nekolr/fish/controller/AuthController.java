package com.nekolr.fish.controller;

import com.nekolr.fish.constant.Fish;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.security.AuthenticationInfo;
import com.nekolr.fish.security.CustomUserDetails;
import com.nekolr.fish.util.EncryptUtils;
import com.nekolr.fish.util.IdGenerator;
import com.nekolr.fish.util.JwtUtils;
import com.nekolr.fish.security.AuthenticationUser;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 身份凭证控制器
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Log("用户登录")
    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody AuthenticationUser authUser) {

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(authUser.getUsername());

        if (!user.getPassword().equals(EncryptUtils.md5(authUser.getPassword() + user.getSalt()))) {
            throw new AccountExpiredException("Incorrect username or password");
        }

        if (!user.getEnabled()) {
            throw new AccountExpiredException("Account has been disabled");
        }

        // 校验完成后签发 Token
        String token = JwtUtils.issueJwt(IdGenerator.randomUUID(), authUser.getUsername(), Fish.JWT_ISSUER, Fish.JWT_PERIOD >> 2, "", SignatureAlgorithm.HS512);

        return ResponseEntity.ok(new AuthenticationInfo(token, user));
    }
}
