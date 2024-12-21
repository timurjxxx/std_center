package com.backend.studycenter.configs;

import com.backend.studycenter.common.dto.security.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtGenerator {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.message}")
    private String message;
    @Value("${jwt.expiration}")
    private long expMillis;
    public Map<String, String> generateToken(UserDTO userDTO) {
        String jwtToken="";
        jwtToken = Jwts.builder()
                .setSubject(userDTO.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expMillis))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);



        return jwtTokenGen;
    }
}
