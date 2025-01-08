package br.com.backend.ccx.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> listAll() {
		return ResponseEntity.ok(service.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> searchById(@PathVariable Long id) {
		UserDTO userDto = service.searchById(id);
		return ResponseEntity.ok(userDto);
	}

	@GetMapping("/{email}")
	public ResponseEntity<UserDTO> searchByEmail(@PathVariable String email) {
		return ResponseEntity.ok(service.searchByEmail(email));
	}

	@PostMapping
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		User user = service.save(userInsertDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDTO(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserInsertDTO userInsertDTO,
			@RequestHeader("Authorization") String bearerToken) {
		
		UserDTO user = service.update(userInsertDTO, bearerToken);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
        service.deleteById(id, bearerToken);
        return ResponseEntity.noContent().build();
    }
}
