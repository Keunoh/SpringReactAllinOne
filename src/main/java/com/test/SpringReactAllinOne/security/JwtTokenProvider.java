package com.test.SpringReactAllinOne.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationTime}")
    private int jwtExpirationTime;

    public String generateToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + jwtExpirationTime);

        return Jwts.builder()
                .setSubject(Long.toString(customUserDetails.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJwt(token);
            return true;
        } catch (SignatureException signatureException) {
            log.error("Invalid JWT signature - {}", signatureException);
        } catch (MalformedJwtException malformedJwtException) {
            log.error("Invalid JWT - {}", malformedJwtException);
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("Expired JWT - {}", expiredJwtException);
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.error("Unsupported JWT - {}", unsupportedJwtException);
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("JWT claims string is empty - {}", illegalArgumentException);
        }
        return false;
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJwt(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
