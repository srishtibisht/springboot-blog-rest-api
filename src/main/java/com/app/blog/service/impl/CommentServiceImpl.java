package com.app.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Comment;
import com.app.blog.entity.Post;
import com.app.blog.exception.BlogAPIException;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.payload.CommentDto;
import com.app.blog.repository.CommentRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.CommentService;
import com.app.blog.utils.AppConstants;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		Comment comment = mapToEntity(commentDto);
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		comment.setPost(post);
		Comment newComment = commentRepository.save(comment);
		return mapToDto(newComment);
	}
	
	public List<CommentDto> getCommentsByPostId(long id){
		
		List<Comment> list = commentRepository.findByPostId(id);
	
		return list.stream().map(l -> mapToDto(l)).collect(Collectors.toList());
	}
	
	public CommentDto getCommentById(long postId, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (post.getId() != comment.getPost().getId()) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post");
		}

		return mapToDto(comment);
	}

	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentUpdateRequest) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if(comment.getPost().getId() != post.getId()) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, AppConstants.DEFAULT_ERROR_MESSAGE);
		}
		comment.setName(commentUpdateRequest.getName());
		comment.setEmail(commentUpdateRequest.getEmail());
		comment.setBody(commentUpdateRequest.getBody());
		
		Comment finalComment = commentRepository.save(comment);
		return mapToDto(finalComment);
	}

	@Override
	public void deleteComment(long postId, long commentId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (comment.getPost().getId() != post.getId()) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, AppConstants.DEFAULT_ERROR_MESSAGE);
		}

//		commentRepository.deleteById(comment.getId());
		commentRepository.delete(comment);

	}
	
	private Comment mapToEntity(CommentDto commentDto){
		
		/*
		 * Comment comment = new Comment(); comment.setId(commentDto.getId());
		 * comment.setName(commentDto.getName());
		 * comment.setEmail(commentDto.getEmail());
		 * comment.setBody(commentDto.getBody());
		 */		
		Comment comment = mapper.map(commentDto, Comment.class);
		
		return comment;
	}
	
	private CommentDto mapToDto(Comment comment) {
		
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		
		/*
		 * CommentDto commentDto = new CommentDto(); commentDto.setId(comment.getId());
		 * commentDto.setName(comment.getName());
		 * commentDto.setEmail(comment.getEmail());
		 * commentDto.setBody(comment.getBody());
		 */
		
		return commentDto;
	}

	
}
