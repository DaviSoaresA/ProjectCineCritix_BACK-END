package br.com.backend.ccx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbResponseDTO {

    @JsonProperty("Search")
    private List<MoviesDTO> movies;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private String response;



    public List<MoviesDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<MoviesDTO> movies) {
		this.movies = movies;
	}

	public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
