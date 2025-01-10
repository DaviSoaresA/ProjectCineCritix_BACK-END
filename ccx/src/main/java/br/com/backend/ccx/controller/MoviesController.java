package br.com.backend.ccx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import br.com.backend.ccx.dto.MovieInsertDTO;
import br.com.backend.ccx.dto.MoviesDTO;
import br.com.backend.ccx.service.MoviesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private MoviesService moviesService;

	@PostMapping("/getMoviesOmdb")
	public ResponseEntity<List<MoviesDTO>> insertMoviesOmdb(
			@PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		List<MoviesDTO> moviesDTO = moviesService.insertOmdbMovies(pageable);
		return ResponseEntity.ok(moviesDTO);
	}
	@GetMapping
	public ResponseEntity<List<MoviesDTO>> list (){
		List<MoviesDTO> moviesDTO = moviesService.listAll();
        return ResponseEntity.ok(moviesDTO);
	}
	@GetMapping("/{id}")
    public ResponseEntity<MoviesDTO> searchById(@PathVariable Long id) {
        MoviesDTO moviesDTO = moviesService.findById(id);
        return ResponseEntity.ok(moviesDTO);
    }
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MoviesDTO> insert(@Valid @RequestBody MovieInsertDTO insert, @RequestHeader("Authorization") String bearerToken) {
        MoviesDTO moviesDTO = moviesService.create(insert, bearerToken);
        return ResponseEntity.ok(moviesDTO);
    }
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MoviesDTO> update(@PathVariable Long id, @Valid @RequestBody MovieInsertDTO update) {
        MoviesDTO moviesDTO = moviesService.update(id, update);
        return ResponseEntity.ok(moviesDTO);
    }
	@DeleteMapping("/{id}")	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MoviesDTO> delete(@PathVariable Long id){
		moviesService.delete(id);
        return ResponseEntity.ok().build();
	}}
	
	