package com.app.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.payload.PostDto;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);

		Post nPost = postRepository.save(post);

		PostDto postResponse = mapToDTO(nPost);

		return postResponse;
	}

	private PostDto mapToDTO(Post post) {

		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());

		return postDto;

	}

	private Post mapToEntity(PostDto postDto) {

		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		return post;
	}

	@Override
	public List<PostDto> getAllPosts() {

		List<Post> posts = postRepository.findAll();
		return posts.stream().map(l -> mapToDTO(l)).collect(Collectors.toList());

	}
	
//	public List<PostDto> getAllPosts(int pageNo, int pageSize){
//		
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		
//		Page<Post> posts = postRepository.findAll(pageable);
//		
//		List<Post> listOfPost = posts.getContent();
//		
//		return  listOfPost.stream().map(i-> mapToDTO(i)).collect(Collectors.toList() );
//		
//	}

	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		return mapToDTO(post);
	}

	public PostDto updatePost(PostDto postDto, long id) {

		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post updatePost = postRepository.save(post);

		return mapToDTO(updatePost);
	}
	
	public void deletePost(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		postRepository.delete(post);
	}
	

}
