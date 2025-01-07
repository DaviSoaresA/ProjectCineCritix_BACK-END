package br.com.backend.ccx.dto;

import br.com.backend.ccx.entities.User;

public class UserDTO {

    private String email;
    private String fullName;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.fullName = user.getFullName();
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
