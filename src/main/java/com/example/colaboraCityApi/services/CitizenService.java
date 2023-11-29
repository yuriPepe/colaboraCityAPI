package com.example.colaboraCityApi.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.colaboraCityApi.dto.CitizenInputDto;
import com.example.colaboraCityApi.dto.CitizenOutputDto;
import com.example.colaboraCityApi.entities.Citizen;
import com.example.colaboraCityApi.entities.FileInfo;
import com.example.colaboraCityApi.repositories.CitizenRepository;
import jakarta.transaction.Transactional;

@Service
public class CitizenService implements UserDetailsService {
    @Autowired
    private CitizenRepository repository;

    @Autowired
    FilesStorageService storageService;


    public Citizen converteDtoParaEntidade(CitizenInputDto dto){
        Citizen cidadao = new Citizen();
        cidadao.setAdmin(dto.getAdmin());
        cidadao.setAdress(dto.getAdress());
        cidadao.setEmail(dto.getEmail());
        cidadao.setSenha(dto.getSenha());
        cidadao.setId(dto.getId());
        //
        cidadao.setName(dto.getName());

        return cidadao;

    }

    public CitizenOutputDto converteEntidadeParaDto(Citizen cidadao){
        CitizenOutputDto cidadaoSaida = new CitizenOutputDto();
        cidadaoSaida.setAdmin(cidadao.getAdmin());
        cidadaoSaida.setAdress(cidadao.getAdress());
        cidadaoSaida.setEmail(cidadao.getEmail());
        cidadaoSaida.setName(cidadao.getName());
        cidadaoSaida.setId(cidadao.getId());
        
        return cidadaoSaida;

    }

    //CRIAR NOVO USUÁRIO NO BANCO DE DADOS
    @Transactional
    public CitizenOutputDto create(CitizenInputDto dto){
        var cidadao = converteDtoParaEntidade(dto);
        cidadao.setSenha(new BCryptPasswordEncoder().encode(cidadao.getSenha()));
        Citizen cidadaoCriado = repository.save(cidadao);
        return converteEntidadeParaDto(cidadaoCriado);

    }


    //READ
    public CitizenOutputDto read(Long id){

       Citizen cidadao = repository.findById(id).get();
       return converteEntidadeParaDto(cidadao);
    }

    //LISTAGEM
    public List<CitizenOutputDto> list(Pageable page){
        
        return  repository.findAll(page).stream().map(p->converteEntidadeParaDto(p)).toList();
    }

    //DELETE USER
    @Transactional
    public void delete(Long id){
     
        repository.deleteById(id);
    }

    //ATUALIZAR USER/ ALTERAR DADO
    @Transactional
    public CitizenOutputDto update(CitizenInputDto cidadao){

        if(cidadao.getId() == null){
            cidadao.setId(99l);
        }
        if(repository.existsById(cidadao.getId())){
            Citizen cidadaoAtualizado = repository.save(converteDtoParaEntidade(cidadao));
            return converteEntidadeParaDto(cidadaoAtualizado);
        }else{
            return null;
        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Citizen cidadao = repository.findByEmail(username);
        if(cidadao != null){
            return org.springframework.security.core.userdetails.User.builder()
                .password(cidadao.getPassword())
                .username(cidadao.getUsername())
            .build();
        }else{
            throw new UsernameNotFoundException("");
        }
    }


    public void upload(Long id, MultipartFile file) {
        if (repository.existsById(id)) {
            var filename = storageService.save(file);
            var foto = new FileInfo(filename);
            var evento = repository.findById(id).get();
            evento.setFoto(foto);
            repository.save(evento);
        }
    }

    public Resource getFoto(Long id) {
        if (repository.existsById(id)) {
            var evento = repository.findById(id).get();
            if (evento.getFoto() != null) {
                return storageService.load(evento.getFoto().getFilename());
            }
            return null;
        }
        return null;
    }

}
