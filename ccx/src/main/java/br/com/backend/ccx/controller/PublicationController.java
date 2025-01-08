package br.com.backend.ccx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.ccx.dto.PublicationDTO;
import br.com.backend.ccx.dto.PublicationInsertDTO;
import br.com.backend.ccx.service.PublicationService;

@RestController
@RequestMapping("/publication")
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	@GetMapping
	public ResponseEntity<List<PublicationDTO>> listAll() {
		return ResponseEntity.ok(publicationService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicationDTO> searchById(@PathVariable Long id) {
		return ResponseEntity.ok(publicationService.findById(id));
	}

	@PostMapping
	public ResponseEntity<PublicationDTO> create(PublicationInsertDTO publicationInsert,
			@RequestHeader("Authorization") String bearerToken) {
		return ResponseEntity.ok(publicationService.save(publicationInsert, bearerToken));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PublicationDTO> update(@PathVariable Long id, PublicationInsertDTO publicationInsert,
            @RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(publicationService.update(id, publicationInsert, bearerToken));
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>delete(@PathVariable Long id){
		return ResponseEntity.ok(publicationService.delete(id));
	}

}
