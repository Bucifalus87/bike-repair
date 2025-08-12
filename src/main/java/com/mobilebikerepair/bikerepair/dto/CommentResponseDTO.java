package com.mobilebikerepair.bikerepair.dto;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentResponseDTO {
    private Long id;
    private String text;
    private String authorUsername;
    private LocalDateTime createdAt;
}


