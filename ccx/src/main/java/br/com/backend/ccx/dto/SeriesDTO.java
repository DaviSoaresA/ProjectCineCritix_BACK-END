package br.com.backend.ccx.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.backend.ccx.entities.Series;
import br.com.backend.ccx.entities.Publication;
import jakarta.persistence.OneToMany;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeriesDTO {

	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Poster")
	private String poster;

	@OneToMany(mappedBy = "serie")
	private List<Publication> publications;

	public SeriesDTO() {
		super();
	}

	public SeriesDTO(String title, String year, String type, String poster, List<Publication> publications) {
		super();
		this.title = title;
		this.year = year;
		this.type = type;
		this.poster = poster;
		this.publications = publications;
	}

	public SeriesDTO(Series series) {
		this.title = series.getTitle();
		this.year = series.getYear();
		this.type = series.getType();
		this.poster = series.getPoster();
		this.publications = series.getPublications();

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
