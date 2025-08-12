package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.CommentDTO;
import com.mobilebikerepair.bikerepair.dto.CommentRequestDTO;
import com.mobilebikerepair.bikerepair.mapper.CommentMapper;
import com.mobilebikerepair.bikerepair.model.Comment;
import com.mobilebikerepair.bikerepair.model.RepairRequest;
import com.mobilebikerepair.bikerepair.model.User;
import com.mobilebikerepair.bikerepair.repository.CommentRepository;
import com.mobilebikerepair.bikerepair.repository.RepairRequestRepository;
import com.mobilebikerepair.bikerepair.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final RepairRequestRepository repairRequestRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDTO addComment(CommentRequestDTO dto) {
        RepairRequest request = repairRequestRepository.findById(dto.getRepairRequestId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Repair request not found"));

        User author = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        Comment comment = CommentMapper.toEntity(dto, author, request);
        comment = commentRepository.save(comment);

        return CommentMapper.toDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long id, String newText, Long authorId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        if (!comment.getAuthor().getId().equals(authorId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the author can edit this comment");
        }

        comment.setText(newText);
        comment = commentRepository.save(comment);
        return CommentMapper.toDTO(comment);
    }

    @Override
    public void deleteComment(Long id, Long authorId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        if (!comment.getAuthor().getId().equals(authorId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the author can delete this comment");
        }

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByRepairRequest(Long repairRequestId) {
        return commentRepository.findByRepairRequestId(repairRequestId).stream()
                .map(CommentMapper::toDTO)
                .toList();
    }
}
