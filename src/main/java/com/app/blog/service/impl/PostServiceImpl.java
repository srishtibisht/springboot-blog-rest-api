package com.app.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.blog.entity.Post;
import com.app.blog.payload.PostDto;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.PostService;

public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = new Post();
		
		
		
		PostDto response = new PostDto();
		
		
		return response;
	}
	

}
