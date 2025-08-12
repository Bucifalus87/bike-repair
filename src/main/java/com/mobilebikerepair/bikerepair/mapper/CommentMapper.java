package com.mobilebikerepair.bikerepair.mapper;

import com.mobilebikerepair.bikerepair.dto.CommentDTO;
import com.mobilebikerepair.bikerepair.dto.CommentRequestDTO;
import com.mobilebikerepair.bikerepair.model.Comment;
import com.mobilebikerepair.bikerepair.model.RepairRequest;
import com.mobilebikerepair.bikerepair.model.User;

public class CommentMapper {

    public static CommentDTO toDTO(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorId(comment.getAuthor() != null ? comment.getAuthor().getId() : null)
                .authorName(comment.getAuthor() != null ? comment.getAuthor().getUsername() : null)
                .repairRequestId(comment.getRepairRequest() != null ? comment.getRepairRequest().getId() : null)
                .build();
    }

    public static Comment toEntity(CommentRequestDTO dto, User author, RepairRequest repairRequest) {
        if (dto == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setAuthor(author);
        comment.setRepairRequest(repairRequest);
        return comment;
    }
}
