package com.project2.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.cms.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Post findByStatus(int status);
	

}
