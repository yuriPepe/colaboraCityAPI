package com.example.colaboraCityApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.colaboraCityApi.dto.LoginInputDto;
import com.example.colaboraCityApi.dto.RefreshTokenDto;
import com.example.colaboraCityApi.dto.TokenDto;
import com.example.colaboraCityApi.entities.User;
import com.example.colaboraCityApi.entities.Citizen;
import com.example.colaboraCityApi.entities.RefreshToken;
import com.example.colaboraCityApi.services.RefreshTokenService;
import com.example.colaboraCityApi.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginInputDto dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                dados.getLogin(),
                dados.getPassword());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((UserDetails) authentication.getPrincipal());
        var user = (UserDetails) authentication.getPrincipal();
        var refreshToken = refreshTokenService.createRefreshToken(user, token);
        return new ResponseEntity<TokenDto>(
                new TokenDto(token, refreshToken.getRefreshToken(), user.getUsername()),
                HttpStatus.CREATED);
    }

////////////////////////////


// @PostMapping("/email")
//     public ResponseEntity<TokenDto> email(@RequestBody @Valid LoginInputDto dados) {
//         var authenticationToken = new UsernamePasswordAuthenticationToken(
//                 dados.getEmail();
//         var authentication = manager.authenticate(authenticationToken);
//         var token = tokenService.gerarToken((UserDetails) authentication.getPrincipal());
//         var user = (UserDetails) authentication.getPrincipal();
//         var refreshToken = refreshTokenService.createRefreshToken(user, token);
//         return new ResponseEntity<TokenDto>(
//                 new TokenDto(token, refreshToken.getRefreshToken(), user.getUsername()),
//                 HttpStatus.CREATED);
//     }


/////////////////////////////
    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refreshtoken(@Valid @RequestBody RefreshTokenDto request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> getToken(requestRefreshToken, user))
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }

    private ResponseEntity<TokenDto> getToken(String requestRefreshToken, User usuario) {
        String token = tokenService.gerarToken(usuario);
        refreshTokenService.deleteByUser(usuario);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuario, token);
        return ResponseEntity.ok(new TokenDto(token, refreshToken.getRefreshToken(), usuario.getEmail()));
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<?> revokeToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Citizen) auth.getPrincipal();
        refreshTokenService.deleteByUser(usuario);
        return ResponseEntity.noContent().build();
    }

}