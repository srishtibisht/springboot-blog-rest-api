package com.app.blog.service;

import java.util.List;
import com.app.blog.payload.PostDto;
import com.app.blog.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);
	
//	List<PostDto> getAllPosts();  
//	List<PostDto> getAllPosts(int pageNo,int pageSize);
	
	PostResponse getAllPosts(int pageNo,int pageSize);

	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto, long id);
	
	void deletePost(long id);

	
	

}
