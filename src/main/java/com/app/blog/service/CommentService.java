package com.app.blog.service;

import java.util.List;

import com.app.blog.entity.Comment;
import com.app.blog.payload.CommentDto;

public interface CommentService {
	
    CommentDto createComment(long postId, CommentDto commentDto);
    
    List<CommentDto> getCommentsByPostId(long id);
    
    CommentDto getCommentById(long postId,long commentId );
    
    CommentDto updateComment(long postId,long commentId ,CommentDto commentUpdateRequest);
    
    void deleteComment(long postId,long commentId);
    
    

}
