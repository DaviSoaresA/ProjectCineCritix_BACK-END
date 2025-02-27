package br.com.backend.ccx.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.backend.ccx.dto.OmdbResponseDTO;
import br.com.backend.ccx.dto.SearchResultDTO;
import br.com.backend.ccx.dto.SeriesDTO;
import br.com.backend.ccx.dto.SeriesInsertDTO;
import br.com.backend.ccx.entities.Series;
import br.com.backend.ccx.entities.User;
import br.com.backend.ccx.exception.NotFoundException;
import br.com.backend.ccx.repository.SeriesRepository;
import br.com.backend.ccx.repository.UserRepository;
import br.com.backend.ccx.security.JwtUtil;

@Service
public class SeriesService {

	@Autowired
	private SeriesRepository serieRepository;
	
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("2fcfe92f")
	private String apiKey;
	
	@Autowired
	private JwtUtil jwt;

	private static final String OMDB_API_URL = "http://www.omdbapi.com/";

	public List<SeriesDTO> insertOmdbSeries() {
	    int maxPages = 10;
	    int currentPage = 1;

	    try {
	        for (int i = 0; i < maxPages; i++) {
	            String url = UriComponentsBuilder.fromHttpUrl(OMDB_API_URL)
	                    .queryParam("s", "series")
	                    .queryParam("type", "series")
	                    .queryParam("page", currentPage + i)
	                    .queryParam("apikey", apiKey)
	                    .toUriString();

	            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	            String responseBody = response.getBody();

	            // Converte o JSON para o DTO
	            ObjectMapper objectMapper = new ObjectMapper();
	            OmdbResponseDTO omdbResponse = objectMapper.readValue(responseBody, OmdbResponseDTO.class);

	            // Verifica se há resultados antes de iterar
	            if (omdbResponse.getResults() != null && !omdbResponse.getResults().isEmpty()) {
	                for (SearchResultDTO serieDto : omdbResponse.getResults()) {
	                    Series serie = new Series();
	                    serie.setTitle(serieDto.getTitle());
	                    serie.setYear(serieDto.getYear());
	                    serie.setType(serieDto.getType());
	                    serie.setPoster(serieDto.getPoster());
						serie.setDirector(serieDto.getDirector());
						serie.setGenre(serieDto.getGenre());
						serie.setIdImdb(serieDto.getImdbID());
						serie.setImdbRating(serieDto.getImdbRating());
						serie.setPlot(serieDto.getPlot());
						serie.setReleased(serieDto.getReleased());
						serie.setRuntime(serieDto.getRuntime());
						serie.setWriter(serieDto.getWriter());
	                    serieRepository.save(serie);
	                }
	            }
	        }

	        return serieRepository.findAll().stream()
	                .map(SeriesDTO::new)
	                .collect(Collectors.toList());

	    } catch (Exception e) {
	        throw new RuntimeException("Erro ao buscar dados da API OMDB: " + e.getMessage());
	    }
	}


	public List<SeriesDTO> listAll() {
		List<Series> series = serieRepository.findAll();
		return series.stream().map(SeriesDTO::new).toList();
	}
	public Page<SeriesDTO> listPag(Pageable pageable ) {
		Page<Series> series = serieRepository.findAll(pageable);
		Page<SeriesDTO> seriesDTO = series.map(serie -> new SeriesDTO(serie));
		return seriesDTO;
	}

	public SeriesDTO findById(Long id) {
		Optional<Series> serie = serieRepository.findById(id);
		if (serie.isEmpty()) {
			throw new NotFoundException("Filme não encontrado");
		}
		return new SeriesDTO(serie.get());
	}
	
	public SeriesDTO findByTitle(String title) {
		Optional<Series> serieOpt = serieRepository.findByTitle(title);
		if (serieOpt.isEmpty()) {
			throw new NotFoundException("Filme não encontrado");
		}
		return new SeriesDTO(serieOpt.get());
	}

	public SeriesDTO create(SeriesInsertDTO insert, String token) {
		
		Long idUser = jwt.getId(token);
		
		Optional<User> userOpt = userRepository.findById(idUser);
		if (userOpt.isEmpty()) {
			throw new NotFoundException("Usuario não autenticado");
		}
		
		Series serie = new Series();
		serie.setTitle(insert.getTitle());
		serie.setYear(insert.getYear());
		serie.setType(insert.getType());
		serie.setPoster(insert.getPoster());
		serieRepository.save(serie);
		return new SeriesDTO(serie);
	}

	public SeriesDTO update(Long id, SeriesInsertDTO insertDto) {
		Optional<Series>serieOpt = serieRepository.findById(id);
		if (serieOpt.isEmpty()) {
            throw new NotFoundException("Filme não encontrado");
        }
		Series serie = new Series();
		serie.setId(id);
		serie.setTitle(insertDto.getTitle() != null ? insertDto.getTitle(): serieOpt.get().getTitle());
		serie.setYear(insertDto.getYear() != null ? insertDto.getYear(): serieOpt.get().getYear());
		serie.setType(insertDto.getType() != null ? insertDto.getType(): serieOpt.get().getType());
		serie.setPoster(insertDto.getPoster() != null ? insertDto.getPoster(): serieOpt.get().getPoster());
		return new SeriesDTO(serie);
	}
	
    public void delete(Long id) {
    	Optional<Series> serieOpt = serieRepository.findById(id);
        if (serieOpt.isEmpty()) {
            throw new NotFoundException("Filme não encontrado");
        }
        serieRepository.deleteById(id);
    }
}
