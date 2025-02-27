package br.com.backend.ccx.dto;

import br.com.backend.ccx.entities.User;
import br.com.backend.ccx.enums.Role;

public class UserDTO {

	private String email;
	private String fullName;
	private Role profile;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.email = user.getEmail();
		this.fullName = user.getFullName();
		this.profile = user.getProfile();
	}

	public Role getProfile() {
		return profile;
	}

	public void setProfile(Role profile) {
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
