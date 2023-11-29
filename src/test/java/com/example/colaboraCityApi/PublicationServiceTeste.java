package com.example.colaboraCityApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.colaboraCityApi.repositories.PublicationRepository;
import com.example.colaboraCityApi.services.PublicationService;

@SpringBootTest
class PublicationServiceTeste {

    @Mock
    PublicationRepository publicacao;

    @MockBean
    PublicationService servicoPublicacao;
    
    @Test
    public void criarPublicacaoComTexto(){

        
    }

    @Test
    public void criarPublicacaoComTextoMaiorDeQuinhentos(){

        

    }

    @Test
    public void criarPublicacaoComTextoVazio(){


        

    }
}
