package com.dan.ticketing.models;

public class Greeting {

	private long id;
	private String firstname;
	private String lastname;
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) { this.firstname = firstname; }

	public String getLastname() { return lastname; }

	public void setLastname(String lastname) { this.lastname = lastname; }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
