package br.com.backend.ccx.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.backend.ccx.entities.Publication;
import br.com.backend.ccx.entities.Series;
import jakarta.persistence.OneToMany;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeriesDTO {

	@JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Runtime")
	private String runtime;

    @JsonProperty("Released")
	private String released;

    @JsonProperty("Plot")
	private String plot;

    @JsonProperty("Director")
	private String director;

    @JsonProperty("Writer")
	private String writer;

    @JsonProperty("imdbRating")
	private Double imdbRating;

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

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	
}
