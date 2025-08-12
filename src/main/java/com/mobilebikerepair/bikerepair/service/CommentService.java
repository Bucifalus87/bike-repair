package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.CommentDTO;
import com.mobilebikerepair.bikerepair.dto.CommentRequestDTO;

import java.util.List;

public interface CommentService {
    CommentDTO addComment(CommentRequestDTO dto);
    CommentDTO updateComment(Long id, String newText, Long authorId);
    void deleteComment(Long id, Long authorId);
    List<CommentDTO> getCommentsByRepairRequest(Long repairRequestId);
}
