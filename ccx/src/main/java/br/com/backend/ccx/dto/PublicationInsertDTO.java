package br.com.backend.ccx.dto;

import br.com.backend.ccx.entities.Movies;
import br.com.backend.ccx.entities.Publication;
import br.com.backend.ccx.entities.User;

public class PublicationInsertDTO {

	private Double rate;

	private String notes;

	private User user;

	private Movies movies;

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

}
