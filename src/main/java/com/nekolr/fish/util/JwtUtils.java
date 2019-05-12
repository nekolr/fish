package com.nekolr.fish.util;

import com.nekolr.fish.constant.Fish;
import com.nekolr.fish.security.JwtUser;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * JWT 工具类
 *
 * @author nekolr
 */
public class JwtUtils {

    /**
     * 签发令牌
     *
     * @param jwtId     令牌 ID
     * @param subject   被签发者
     * @param issuer    签发者
     * @param period    有效时间，单位秒
     * @param algorithm 加密算法
     * @return
     */
    public static String issueJwt(String jwtId, String subject, String issuer,
                                  Long period, String audience, SignatureAlgorithm algorithm) {
        // 当前时间戳
        Long currentTimeMillis = System.currentTimeMillis();
        // 私钥
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(Fish.JWT_SECRET);
        // 创建 JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder();

        /* Reserved claims */

        // 设置令牌 ID
        jwtBuilder.setId(jwtId);
        // 设置被签发者
        jwtBuilder.setSubject(subject);
        // 设置签发者
        jwtBuilder.setIssuer(issuer);
        // 设置接收者
        jwtBuilder.setAudience(audience);
        // 设置签发时间
        jwtBuilder.setIssuedAt(new Date(currentTimeMillis));
        // 设置到期时间
        jwtBuilder.setExpiration(new Date(currentTimeMillis + period * 1000));

        /* Private claims */

        // 目前为空，可以根据需要添加


        // 压缩方式，默认采用 deflate
        jwtBuilder.compressWith(CompressionCodecs.DEFLATE);
        // 采用加密算法加密私钥
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, algorithm.getJcaName());
        // 签发令牌
        jwtBuilder.signWith(secretKey, algorithm);
        // 生成令牌
        return jwtBuilder.compact();
    }

    /**
     * 解析 JWT
     *
     * @param jwt       令牌
     * @param algorithm 加密算法
     * @return
     */
    public static JwtUser parseJwt(String jwt, SignatureAlgorithm algorithm) {
        // 私钥
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(Fish.JWT_SECRET);
        // 通过加密算法加密私钥
        SecretKey key = new SecretKeySpec(secretKeyBytes, algorithm.getJcaName());
        // 获取有效荷载
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();

        JwtUser jwtUser = new JwtUser();
        jwtUser.setJwtId(claims.getId());
        jwtUser.setUsername(claims.getSubject());
        jwtUser.setIssuer(claims.getIssuer());
        jwtUser.setIssuedAt(claims.getIssuedAt());
        jwtUser.setAudience(claims.getAudience());
        return jwtUser;
    }

}
