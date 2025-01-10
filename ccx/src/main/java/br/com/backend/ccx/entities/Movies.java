package br.com.backend.ccx.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String year;
	private String type;
	private String Poster;

//	private Publication publication;

	public Movies(Long id, String title, String year, String type, String poster) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.type = type;
		Poster = poster;
//		this.publication = publication;
	}

	public Movies() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
	}

}
