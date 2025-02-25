package br.com.backend.ccx.dto;

public class MovieInsertDTO {

	private String title;
	private String year;
	private String type;
	private String poster;

	public MovieInsertDTO() {
		super();
	}

	public MovieInsertDTO(String title, String year, String type, String poster) {
		super();
		this.title = title;
		this.year = year;
		this.type = type;
		this.poster = poster;
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
