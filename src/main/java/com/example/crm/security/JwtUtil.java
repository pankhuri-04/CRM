package com.example.crm.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key (use at least 256-bit)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity (e.g., 24 hours)
    private final long expirationMs = 24 * 60 * 60 * 1000;

    // 🔹 Generate token with username + role
    public String generateToken(String username, String role) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)          // username
                .claim("role", role)           // role claim
                .setIssuedAt(now)              // issued at
                .setExpiration(new Date(now.getTime() + expirationMs)) // expiry
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Extract username
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // 🔹 Extract role
    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // 🔹 Validate token
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 🔹 Parse token
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
