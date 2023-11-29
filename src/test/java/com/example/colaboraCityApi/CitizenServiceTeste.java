package com.example.colaboraCityApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.colaboraCityApi.entities.Citizen;
import com.example.colaboraCityApi.repositories.CitizenRepository;
import com.example.colaboraCityApi.services.CitizenService;

@SpringBootTest
class CitizenServiceTeste {

    @Mock
    CitizenService servicoCidadao;

    @Mock
    Citizen cidadao;

    @Mock
    CitizenRepository repository;

    // Teste de exclusão de conta 
@Test 
public void testeExcluirContaComPostagens() {

  Citizen cidadao = repository.findOne(123);
  
  servicoCidadao.delete(cidadao);
  
  Assert.assertFalse(repository.exists(cidadao.getId()));
  
}

  
}
