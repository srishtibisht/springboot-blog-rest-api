package com.app.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.blog.entity.Post;
import com.app.blog.payload.PostDto;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.PostService;

public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post nPost = postRepository.save(post);

		PostDto postResponse = new PostDto();
		postResponse.setId(nPost.getId());
		postResponse.setTitle(nPost.getTitle());
		postResponse.setDescription(nPost.getDescription());
		postResponse.setContent(nPost.getContent());

		return postResponse;
	}

}
