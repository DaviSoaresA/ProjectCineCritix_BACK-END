package br.com.backend.ccx.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.backend.ccx.dto.UserDTO;
import br.com.backend.ccx.dto.UserInsertDTO;
import br.com.backend.ccx.entities.User;
import br.com.backend.ccx.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> buscar(@PathVariable String email){
        if (service.buscar(email) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.buscar(email));
    }

    @PostMapping
    public ResponseEntity<UserDTO> salvar(@Valid @RequestBody UserInsertDTO userInsertDTO){
        User user = service.salvar(userInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDTO(user));
    }
}
