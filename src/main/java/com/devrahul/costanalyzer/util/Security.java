package com.devrahul.costanalyzer.util;

import io.jsonwebtoken.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Security {
    static String JWT_SECRET ="Hello guys this is going to be secret key" ;

    public static  String passwordEncoding(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return  passwordEncoder.encode(password);
    }

    public  static boolean passwordMatcher(String plainText, String encryptedPassword){
        PasswordEncoder passwordMatcher = new BCryptPasswordEncoder();
        return passwordMatcher.matches(plainText,encryptedPassword);
    }

    public  static String jwtTokenGenerator(String userData){
        Map<String, Object> claims = new HashMap<>();
        String jwtToken = Jwts.builder().setClaims(claims).setSubject(userData).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5*60*60 * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
        return jwtToken;
    }

    public static String getUserIdFromJwtToken(String token) {
        try{
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
        }
        catch (Exception e){
            return  null;
        }
    }
    public static boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}"+ e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}"+ e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}"+ e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}"+ e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}"+ e.getMessage());
        }
        return false;
    }
}
