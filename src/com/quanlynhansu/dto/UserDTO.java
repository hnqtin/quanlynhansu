package com.quanlynhansu.dto;

public class UserDTO {
	private int id;
	private String email;
	private String fullname;
	private String roleName;
	public UserDTO(int id, String email, String fullname, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.roleName = roleName;
	}
	public UserDTO() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
