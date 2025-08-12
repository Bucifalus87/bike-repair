package com.mobilebikerepair.bikerepair.repository;

import com.mobilebikerepair.bikerepair.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRepairRequestId(Long repairRequestId);
}
