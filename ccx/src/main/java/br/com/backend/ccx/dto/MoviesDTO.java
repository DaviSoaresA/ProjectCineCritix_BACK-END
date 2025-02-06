package br.com.backend.ccx.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.backend.ccx.entities.Movies;
import br.com.backend.ccx.entities.Publication;
import jakarta.persistence.OneToMany;

// This DTO is used to map the response from OMDB API to a Movies entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesDTO {

	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Poster")
	private String poster;

	@OneToMany(mappedBy = "movie")
	private List<Publication> publications;

	public MoviesDTO() {
		super();
	}

	public MoviesDTO(String title, String year, String type, String poster, List<Publication> publications) {
		super();
		this.title = title;
		this.year = year;
		this.type = type;
		this.poster = poster;
		this.publications = publications;
	}

	public MoviesDTO(Movies movies) {
		this.title = movies.getTitle();
		this.year = movies.getYear();
		this.type = movies.getType();
		this.poster = movies.getPoster();
		this.publications = movies.getPublications();

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
}
