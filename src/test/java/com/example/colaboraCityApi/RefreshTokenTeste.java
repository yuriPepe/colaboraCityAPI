package com.example.colaboraCityApi;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.colaboraCityApi.repositories.RefreshTokenRepository;
import com.example.colaboraCityApi.services.RefreshTokenService;

@SpringBootTest
class RefreshTokenTeste {
    
@Mock
RefreshTokenRepository refreshToken;

@MockBean
RefreshTokenService serviceRefreshToken;
}
