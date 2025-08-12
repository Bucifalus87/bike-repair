package com.mobilebikerepair.bikerepair.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private Long authorId;
    private String authorName;
    private Long repairRequestId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
