package com.example.colaboraCityApi.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.colaboraCityApi.dto.CitizenInputDto;
import com.example.colaboraCityApi.dto.CitizenOutputDto;
import com.example.colaboraCityApi.dto.PublicationInputDto;
import com.example.colaboraCityApi.dto.PublicationOutputDto;
import com.example.colaboraCityApi.entities.Citizen;
import com.example.colaboraCityApi.entities.Publication;
import com.example.colaboraCityApi.repositories.PublicationRepository;
import jakarta.transaction.Transactional;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository repository;

    public Publication converteDtoParaEntidade(PublicationInputDto dto){
        Publication publicacao = new Publication();
        publicacao.setDataPublicacao(dto.getDataPublicacao());
        publicacao.setTextoPublicacao(dto.getTextoPublicacao());
        publicacao.setId(dto.getId());

        return publicacao;
    }

    public PublicationOutputDto converteEntidadeParaDto(Publication publicacao){
        PublicationOutputDto publicacaoSaida = new PublicationOutputDto();
        publicacaoSaida.setDataPublicacao(publicacao.getDataPublicacao());
        publicacaoSaida.setTextoPublicacao(publicacao.getTextoPublicacao());
        publicacaoSaida.setId(publicacao.getId());
        publicacaoSaida.setCitizen(publicacao.getCidadao().getName());

        return publicacaoSaida;
    }



    //CRIAR NOVA PUBLICAÇÃO
    @Transactional
    public PublicationOutputDto create(PublicationInputDto dto, Citizen cidadao){

        var publication = converteDtoParaEntidade(dto);
        publication.setCidadao(cidadao);
        Publication publicacaoCriada = repository.save(publication);
        

        return converteEntidadeParaDto(publicacaoCriada);
        
    
    }

    //READ
    public PublicationOutputDto read(Long id){

        Publication publicacao = repository.findById(id).get();
        return converteEntidadeParaDto(publicacao);
       
    }

    //LISTAGEM
    public List<PublicationOutputDto> list(Pageable page){
        
        return  repository.findAll(page).stream().map(p->converteEntidadeParaDto(p)).toList();
    }

    //DELETE PUBLICAÇÃO
    @Transactional
    public void delete(Long id){
     
        repository.deleteById(id);
    }

    //ATUALIZAR USER/ ALTERAR DADO
    @Transactional
    public PublicationOutputDto update(PublicationInputDto publicacao){

        if(publicacao.getId() == null){
            publicacao.setId(99l);
        }
        if(repository.existsById(publicacao.getId())){
            Publication publicacaoAtualizada = repository.save(converteDtoParaEntidade(publicacao));
            return converteEntidadeParaDto(publicacaoAtualizada);
        }else{
            return null;
        }

    }


}
