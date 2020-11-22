package com.birkan.rig.controller;

import com.birkan.rig.common.CustomerDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @PostMapping("login")
    public CustomerDto login(@RequestParam("email") String email) {
        String token = getJWTToken(email);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(email);
        customerDto.setToken(token);
        return customerDto;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
            .builder()
            .setId("softtekJWT")
            .setSubject(username)
            .claim("authorities",
                grantedAuthorities.stream()
                                  .map(GrantedAuthority::getAuthority)
                                  .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512,
                secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
