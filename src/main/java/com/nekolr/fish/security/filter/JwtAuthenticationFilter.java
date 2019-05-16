package com.nekolr.fish.security.filter;

import com.nekolr.fish.constant.Fish;
import com.nekolr.fish.util.JwtUtils;
import com.nekolr.fish.security.JwtUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义访问权限校验过滤器
 *
 * @author nekolr
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(Fish.TOKEN_HEADER_KEY);

        if (StringUtils.isBlank(header) || !header.startsWith(Fish.TOKEN_HEADER_VALUE_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = StringUtils.replace(header, Fish.TOKEN_HEADER_VALUE_PREFIX, "");
        try {
            // 只判断 token 合法有效，真正的用户信息通过查询数据库得到
            JwtUser jwtUser = JwtUtils.parseJwt(jwt, SignatureAlgorithm.HS512);
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUser.getUsername());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            log.info("Authorized user '{}', setting security context", jwtUser.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (ExpiredJwtException e) {
            // token 过期
            log.error("Token has expired: {}" + e);
        } catch (UnsupportedJwtException e) {
            // token 格式错误
            log.error("Token format error: {}" + e);
        } catch (MalformedJwtException e) {
            // token 构造错误
            log.error("Token construct error: {}" + e);
        } catch (SignatureException e) {
            // 签名失败
            log.error("Signature failed: {}" + e);
        } catch (IllegalArgumentException e) {
            // 非法参数
            log.error("Illegal argument: {}" + e);
        }
        chain.doFilter(request, response);
    }
}
