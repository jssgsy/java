package com.univ.thirdutils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 有两个常见的可用的依赖。com.auth0:java-jwt和io.jsonwebtoken:jjwt-api。这里使用的是java-jwt
 *
 * @author univ
 * date 2023/7/20
 */
public class JwtTest {

    private static final String SECRET_KEY = "abcd";

    /**
     * 生成jwt
     */
    @Test
    public void sign() {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("h1", "aaa");
        headerMap.put("h2", "bbb");
        headerMap.put("h3", "ccc");

        // 比较重要的参数应该就是withNotBefore、withExpiresAt
        String token = JWT.create()
                .withHeader(headerMap)
                .withIssuer("univ")
                .withNotBefore(new Date(System.currentTimeMillis() + 2 * 60 * 1000))    // 设置两分钟之后token才生效
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))  // 设置过期时间
                .withAudience("audience1", "audience2") //设置接受方信息，一般时登录用户
                .withSubject("subject")
                .withClaim("user_id", 1234)
                .withClaim("role", "admin")
                .sign(Algorithm.HMAC256(SECRET_KEY));
        // eyJoMSI6ImFhYSIsImgyIjoiYmJiIiwidHlwIjoiSldUIiwiaDMiOiJjY2MiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiYXVkaWVuY2UxIiwiYXVkaWVuY2UyIl0sInN1YiI6InN1YmplY3QiLCJyb2xlIjoiYWRtaW4iLCJ1c2VyX2lkIjoxMjM0LCJpc3MiOiJ1bml2IiwiZXhwIjoxNjg5ODIzNzQyfQ.xXD2b1rPxWCrhXzia1U_i1t0cl851XhHqR6d9r5UfPc
        System.out.println(token);

    }

    /**
     * 1. 如果token已过期或者尚未开始，则校验都会抛出异常；
     * 2. 看下DecodedJWT类的签名，便会知道如何获取数据，一目了然，很优雅；
     */
    @Test
    public void decode() {
        String token = "eyJoMSI6ImFhYSIsImgyIjoiYmJiIiwidHlwIjoiSldUIiwiaDMiOiJjY2MiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiYXVkaWVuY2UxIiwiYXVkaWVuY2UyIl0sInN1YiI6InN1YmplY3QiLCJuYmYiOjE2ODk4MzQzNTksInJvbGUiOiJhZG1pbiIsInVzZXJfaWQiOjEyMzQsImlzcyI6InVuaXYiLCJleHAiOjE2ODk4MzQ4Mzl9.8N0QPgOEgyzTnpynew7qY3nbZvwQdCBTkTT-LsV38Uc";
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        // 如果token已过期，则会抛出异常：com.auth0.jwt.exceptions.TokenExpiredException
        // 如果token尚未生效，则会抛出异常：com.auth0.jwt.exceptions.InvalidClaimException: The Token can't be used before xxx
        DecodedJWT jwt = verifier.verify(token);

        // 此时的header、payload、signature就是token中的对应部分，既并不是原始值；
        System.out.println("原始token值: " + jwt.getToken());
        System.out.println("token中的header部分: " + jwt.getHeader());
        System.out.println("token中的payload部分: " + jwt.getPayload());
        System.out.println("token中的signature部分: " + jwt.getSignature());

        // 取header中的原始值:getHeaderClaim
        System.out.println("header中的原始值， h1: " + jwt.getHeaderClaim("h1").asString());

        // 取payload中的自定义的原始值:getClaim
        Integer userId = jwt.getClaim("user_id").asInt();
        String role = jwt.getClaim("role").asString();
        System.out.println("payload中的原始值， userId: " + userId);
        System.out.println("payload中的原始值， role: " + role);

        // 取payload中通用字段值:均有对应的独立的方法
        System.out.println("issuer: " + jwt.getIssuer());
        System.out.println("expiresAt: " + jwt.getExpiresAt());
        System.out.println("subject: " + jwt.getSubject());
    }
}
