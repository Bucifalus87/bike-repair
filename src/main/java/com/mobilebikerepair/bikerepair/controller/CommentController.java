package com.mobilebikerepair.bikerepair.controller;

import com.mobilebikerepair.bikerepair.dto.CommentDTO;
import com.mobilebikerepair.bikerepair.dto.CommentRequestDTO;
import com.mobilebikerepair.bikerepair.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentRequestDTO dto) {
        return ResponseEntity.ok(commentService.addComment(dto));
    }

    // simple demo-friendly update using request params
    @PatchMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable Long id,
            @RequestParam String newText,
            @RequestParam Long authorId
    ) {
        return ResponseEntity.ok(commentService.updateComment(id, newText, authorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id,
            @RequestParam Long authorId
    ) {
        commentService.deleteComment(id, authorId);
        return ResponseEntity.noContent().build();
    }

    // list comments for a given repair request
    @GetMapping("/by-repair-request/{repairRequestId}")
    public ResponseEntity<List<CommentDTO>> getByRepairRequest(@PathVariable Long repairRequestId) {
        return ResponseEntity.ok(commentService.getCommentsByRepairRequest(repairRequestId));
    }
}
