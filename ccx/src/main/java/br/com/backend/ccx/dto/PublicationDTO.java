package br.com.backend.ccx.dto;

import br.com.backend.ccx.entities.Publication;
import br.com.backend.ccx.entities.User;

public class PublicationDTO {

	private Double rate;

	private String notes;

	private User user;

	public PublicationDTO(Double rate, String notes, User user) {
		super();
		this.rate = rate;
		this.notes = notes;
		this.user = user;
	}

	public PublicationDTO() {
		super();
	}
	public PublicationDTO(Publication publication) {
		this.rate = publication.getRate();
		this.notes = publication.getNotes();
		this.user = publication.getUser();
	}

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

}
