package com.dankook.authchatting.auth.application;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dankook.authchatting.auth.domain.Auth;
import com.dankook.authchatting.auth.domain.Token;
import com.dankook.authchatting.auth.exception.NotFoundTokenRoleException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProviderImpl implements TokenProvider {
    private final Key key;
    private final String secretKey;
    private final long accessExpiration;
    private final long refreshExpiration;

    public TokenProviderImpl(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expired.access}") long accessExpiration,
            @Value("${jwt.expired.refresh}") long refreshExpiration
    ) {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(bytes);
        this.secretKey = secretKey;
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
    }

    @Override
    public Token create(Auth auth) {
        Long currentTime = new Date().getTime();

        String accessToken = Jwts.builder()
                                 .setClaims(setClaims(auth))
                                 .setSubject(auth.getMember().getId().toString())
                                 .setExpiration(new Date(currentTime + accessExpiration))
                                 .signWith(key, SignatureAlgorithm.HS512)
                                 .compact();

        String refreshToken = Jwts.builder()
                                  .setClaims(setClaims(auth))
                                  .setSubject(auth.getMember().getId().toString())
                                  .setExpiration(new Date(currentTime + refreshExpiration))
                                  .signWith(key, SignatureAlgorithm.HS512)
                                  .compact();

        return Token.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
    }

    private Claims setClaims(Auth auth) {
        Claims claims = Jwts.claims();
        claims.put("memberId", auth.getMember().getId());
        claims.put("type", auth.getType());

        return claims;
    }

    @Override
    public boolean validation(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    @Override
    public Long parseMemberId(String token) {
        System.out.println("token is " + token);

        return getClaims(token).get("memberId", Long.class);
    }

    @Override
    public Authentication getAuthentication(String accessToken) {
        Claims claims = getClaims(accessToken);
        if (claims.get("type") == null) {
            throw new NotFoundTokenRoleException();
        }

        Collection<? extends GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(claims.get("type").toString()));

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                       .setSigningKey(key)
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}