package com.example.colaboraCityApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.colaboraCityApi.entities.Publication;
import com.example.colaboraCityApi.repositories.PublicationRepository;
import com.example.colaboraCityApi.services.PublicationService;

@SpringBootTest
class PublicationServiceTeste {

    @Mock
    PublicationRepository publicacaoRep;

    @MockBean
    PublicationService servicoPublicacao;

    private Publication publicacao;
    
    // Teste de postagem
@Test
public void testePostagemSemTextoImagem() {
  
  Publication postagem = new Publication();
  
  try {
    servicoPublicacao.create(postagem)
    Assert.fail("Deveria falhar ao criar postagem inválida");

  } catch (IllegalArgumentException e) {
     // Previne exception esperada
  }

}

// Teste de edição de postagem
@Test
public void testeEditarPostagemOutroUsuario() {

  Publication postagem = publicacaoRep.findBy(123);
  
  try {  
    postagem.setTexto("Texto editado");
    servicoPublicacao.atualizaPostagem(postagem);
    
    Assert.fail("Não deveria ter permitido editar post de outro");
} catch (IllegalAccessError e) {
    // Previne erro esperado de permissão
  }

}





}
