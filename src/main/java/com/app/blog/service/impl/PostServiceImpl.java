package com.app.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.payload.PostDto;
import com.app.blog.payload.PostResponse;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		Post nPost = postRepository.save(post);
		PostDto postResponse = mapToDTO(nPost);
		return postResponse;
	}

//	@Override
//	public List<PostDto> getAllPosts() {
//
//		List<Post> posts = postRepository.findAll();
//		return posts.stream().map(l -> mapToDTO(l)).collect(Collectors.toList());
//
//	}

	// get all post using pagination
//	public List<PostDto> getAllPosts(int pageNo,int pageSize){
//		
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		
//		Page<Post> posts= postRepository.findAll(pageable);
//		
//		List<Post> listOfPosts = posts.getContent();
//		
//		return listOfPosts.stream().map(l -> mapToDTO(l)).collect(Collectors.toList());
//		
//	}

	// get all post using customize pagination
//	public PostResponse getAllPosts(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		Page<Post> posts = postRepository.findAll(pageable);
//
//		List<Post> listOfPosts = posts.getContent();
//		List<PostDto> content = listOfPosts.stream().map(l -> mapToDTO(l)).collect(Collectors.toList());
//
//		PostResponse postResponse = new PostResponse();
//		postResponse.setContent(content);
//		postResponse.setTotalPages(posts.getTotalPages());
//		postResponse.setTotalElements(posts.getNumberOfElements());
//		postResponse.setLast(posts.isLast());
//		postResponse.setPageSize(posts.getSize());
//		postResponse.setPageNo(posts.getNumber());
//
//		return postResponse;
//	}

	// get all post using customize pagination and sorting
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();	
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> listOfPosts = posts.getContent();

		List<PostDto> content = listOfPosts.stream().map(l -> mapToDTO(l)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());

		return postResponse;

	}

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

	private PostDto mapToDTO(Post post) {
		
//		System.out.println("PPPPPPPPP : "+ post.getComment());
//		
//		PostDto postDto = mapper.map(post, PostDto.class);
//		
//		System.out.println("POST DTO : "+ postDto.getComments());

		
		  PostDto postDto = new PostDto(); postDto.setId(post.getId());
		  postDto.setTitle(post.getTitle());
		  postDto.setDescription(post.getDescription());
		  postDto.setContent(post.getContent());
//		  postDto.setComments(post.getComment());
		 

		return postDto;

	}

	private Post mapToEntity(PostDto postDto) {
		
		Post post = mapper.map(postDto, Post.class);
		System.out.println("POST : "+post);

//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());

		return post;
	}

}
