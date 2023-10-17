package com.turkcell.spring.starter.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private String SECRET_KEY = "w3fvo4E169FTCxo9IDlXLv6ao3B1LF9kxVPIqrKwuVFmUna5SkYQbxshwb9I8b6CAmcfGNRHPuFJCGw0bfrAjvcnMQ4t8DL8VfBrvfaPKKV6JM8WRGm8uhzxfACHSM9A5CctppkfTkHItXWFzy40Rw9OOCQGP+Voi3bMelVF5GKHn3ac6LIxHF83C5Fiy4AO";

    private long EXPIRATION = 86400000;
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails user){
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails user){
        final String usernameFromToken = extractUsername(token);
        return (user.getUsername().equals(usernameFromToken)) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //claim: datalara verilen isim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token)
    {
        //parserBuilder: var olan objeyi kendi kullanabileceğin hale getirmek
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody(); // Jwt içerisindeki datayı parse eder.
    }

    public Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}