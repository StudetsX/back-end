package com.acheron.campusx.security.jwt;

import com.acheron.campusx.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.Instant.now;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

//    private final SecretKey secretKey = Keys.hmacShaKeyFor(this.secret.getBytes());
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("id",userDetails.getId());
        if(userDetails.getChair()!=null){
            claims.put("chairId",userDetails.getChair().getId());
            claims.put("chairName",userDetails.getChair().getName());
        } else if (userDetails.getGroup()!=null) {
            claims.put("groupId",userDetails.getGroup().getId());
            claims.put("groupName",userDetails.getGroup().getName());

        }
//        claims.put("group_id",userDetails.getGroup());
//        claims.put("chair",userDetails.getChair())
        claims.put("name",userDetails.getFirstName());
        claims.put("role", userDetails.getRole().getAuthority());
        claims.put("roles", rolesList);
        claims.put("image",userDetails.getImage());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now()))
                .setExpiration(Date.from(now().plus(Period.ofWeeks(40))))
//                .signWith(SignatureAlgorithm.HS256, secret)
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
    }
}