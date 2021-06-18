package com.cry.flutter.admin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtil {
    public static void main(String[] args) {
//        generalKey();
        String token = createJWT("testToken");
        Claims claims = parseJWT(token);
        claims.getSubject();

        System.out.println(claims.getSubject());

    }

    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static String createJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId("1")
                .setSubject(subject)   // 主题
                .setIssuer("user")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        return builder.compact();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode("crykkkkkkkkkkkkk");
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
//     * 验证JWT
//     *
//     * @param jwtStr
//     * @return
//     */
//    public static CheckResult validateJWT(String jwtStr) {
//        CheckResult checkResult = new CheckResult();
//        Claims claims = null;
//        try {
//            claims = parseJWT(jwtStr);
//            checkResult.setSuccess(true);
//            checkResult.setClaims(claims);
//        } catch (ExpiredJwtException e) {
//            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_EXPIRE);
//            checkResult.setSuccess(false);
//        } catch (SignatureException e) {
//            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
//            checkResult.setSuccess(false);
//        } catch (Exception e) {
//            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
//            checkResult.setSuccess(false);
//        }
//        return checkResult;
//    }
//


}
