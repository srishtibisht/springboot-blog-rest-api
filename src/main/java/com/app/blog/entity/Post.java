package com.app.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.Setter;



@Data
@Entity
@Table(name="posts", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name = "title",nullable = false)
	public String title;
	
	@Column(name="description",nullable = false)
	private String description;
	
	@Column(name="content",nullable = false)
	private String content;



	
	
}


