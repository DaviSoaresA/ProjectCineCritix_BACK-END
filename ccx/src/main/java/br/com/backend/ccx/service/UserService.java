package br.com.backend.ccx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.ccx.dto.UserDTO;
import br.com.backend.ccx.dto.UserInsertDTO;
import br.com.backend.ccx.entities.User;
import br.com.backend.ccx.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> listar(){
        return repository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO buscar(String email){
        return new UserDTO(repository.findByEmail(email).get());
    }

    public User salvar(UserInsertDTO insertDTO){
        if (!insertDTO.getConfirmPassword().equals(insertDTO.getPassword())) {
            new RuntimeException("As senhas estão diferentes");
        }
        User user = new User();
        user.setEmail(insertDTO.getEmail());
        user.setFullName(insertDTO.getFullName());
        user.setPassword(insertDTO.getPassword());
        
        return repository.save(user);
    }

}
