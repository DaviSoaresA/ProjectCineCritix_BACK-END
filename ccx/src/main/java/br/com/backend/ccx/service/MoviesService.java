package br.com.backend.ccx.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.backend.ccx.dto.MovieInsertDTO;
import br.com.backend.ccx.dto.MoviesDTO;
import br.com.backend.ccx.dto.OmdbResponseDTO;
import br.com.backend.ccx.entities.Movies;
import br.com.backend.ccx.entities.User;
import br.com.backend.ccx.exception.NotFoundException;
import br.com.backend.ccx.repository.MoviesRepository;
import br.com.backend.ccx.repository.UserRepository;
import br.com.backend.ccx.security.JwtUtil;

@Service
public class MoviesService {

	@Autowired
	private MoviesRepository moviesRepository;
	
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("2fcfe92f")
	private String apiKey;
	
	@Autowired
	private JwtUtil jwt;

	private static final String OMDB_API_URL = "http://www.omdbapi.com/";

	public List<MoviesDTO> insertOmdbMovies(Pageable pageable) {
		// cada pagina retorna 10 filmes
		int maxPages = 10;
		int currentPage = pageable.getPageNumber() + 1;

		try {
			for (int i = 0; i < maxPages; i++) {
				String url = UriComponentsBuilder.fromHttpUrl(OMDB_API_URL).queryParam("s", "movie")
						.queryParam("page", currentPage + i).queryParam("apikey", apiKey).toUriString();

				ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
				String responseBody = response.getBody();

				// converte o JSON para o dto
				ObjectMapper objectMapper = new ObjectMapper();
				OmdbResponseDTO omdbResponse = objectMapper.readValue(responseBody, OmdbResponseDTO.class);

				for (MoviesDTO movieDto : omdbResponse.getMovies()) {
					Movies movie = new Movies();
					movie.setTitle(movieDto.getTitle());
					movie.setYear(movieDto.getYear());
					movie.setType(movieDto.getType());
					movie.setPoster(movieDto.getPoster());
					moviesRepository.save(movie);
				}
			}

			// Buscar todos os filmes salvos no banco e converter para DTO
			List<Movies> moviesList = moviesRepository.findAll();
			return moviesList.stream().map(MoviesDTO::new).collect(Collectors.toList());

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar dados da API OMDB: " + e.getMessage());
		}
	}

	public List<MoviesDTO> listAll() {
		List<Movies> movies = moviesRepository.findAll();
		return movies.stream().map(MoviesDTO::new).toList();
	}

	public MoviesDTO findById(Long id) {
		Optional<Movies> movie = moviesRepository.findById(id);
		if (movie.isEmpty()) {
			throw new NotFoundException("Filme não encontrado");
		}
		return new MoviesDTO(movie.get());
	}

	public MoviesDTO create(MovieInsertDTO insert, String token) {
		
		// Validar token
		Long idUser = jwt.getId(token);
		
		Optional<User> userOpt = userRepository.findById(idUser);
		if (userOpt.isEmpty()) {
			throw new NotFoundException("Usuario não autenticado");
		}
		
		Movies movie = new Movies();
		movie.setTitle(insert.getTitle());
		movie.setYear(insert.getYear());
		movie.setType(insert.getType());
		movie.setPoster(insert.getPoster());
		moviesRepository.save(movie);
		return new MoviesDTO(movie);
	}

	public MoviesDTO update(Long id, MovieInsertDTO insertDto) {
		Optional<Movies>movieOpt = moviesRepository.findById(id);
		if (movieOpt.isEmpty()) {
            throw new NotFoundException("Filme não encontrado");
        }
		Movies movie = new Movies();
		movie.setId(id);
		movie.setTitle(insertDto.getTitle() != null ? insertDto.getTitle(): movieOpt.get().getTitle());
		movie.setYear(insertDto.getYear() != null ? insertDto.getYear(): movieOpt.get().getYear());
		movie.setType(insertDto.getType() != null ? insertDto.getType(): movieOpt.get().getType());
		movie.setPoster(insertDto.getPoster() != null ? insertDto.getPoster(): movieOpt.get().getPoster());
		return new MoviesDTO(movie);
	}
	
    public void delete(Long id) {
    	Optional<Movies> movieOpt = moviesRepository.findById(id);
        if (movieOpt.isEmpty()) {
            throw new NotFoundException("Filme não encontrado");
        }
        moviesRepository.deleteById(id);
    }
}
