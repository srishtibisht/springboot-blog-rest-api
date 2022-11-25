package com.app.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payload.CommentDto;
import com.app.blog.payload.PostDto;
import com.app.blog.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
//	@RequestBody PostDto postDto, @PathVariable(name = "id_value") long id
	
	
	@PostMapping("posts/{post_id}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(name = "post_id") int id,@RequestBody CommentDto commentDto) {
//		CommentDto dto = commentService.createComment(id, commentDto);
//
//		System.out.println("Data is : "+ dto);

		return new ResponseEntity<CommentDto>(commentService.createComment(id, commentDto), HttpStatus.CREATED);

	}

	@GetMapping("posts/{id}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable long id){
		return commentService.getCommentsByPostId(id);
		
	
		}
}
