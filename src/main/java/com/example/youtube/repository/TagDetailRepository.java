package com.example.youtube.repository;

import com.example.youtube.model.TagDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDetailRepository extends JpaRepository<TagDetail,Integer> {
}
