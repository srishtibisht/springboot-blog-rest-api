package com.app.blog.service;

import java.util.List;

import com.app.blog.entity.Comment;
import com.app.blog.payload.CommentDto;

public interface CommentService {
	
    CommentDto createComment(long postId, CommentDto commentDto);
    
    List<CommentDto> getCommentsByPostId(long id);
    
    CommentDto getCommentById(long commentId, long postId);
    
    

}
