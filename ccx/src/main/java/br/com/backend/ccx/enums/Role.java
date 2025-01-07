package br.com.backend.ccx.enums;

public enum Role {

	USER, ADM;
	
	 public String getRoleName() {
        return this.name();
    }
}
