package com.app.blog.payload;

public class CommentDto {

	private long id;
	private String name;
	private String email;
	private String body;

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(long id, String name, String email, String body) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", name=" + name + ", email=" + email + ", body=" + body + "]";
	}

}
