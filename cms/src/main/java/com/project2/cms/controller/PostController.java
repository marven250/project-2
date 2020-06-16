package com.project2.cms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project2.cms.model.Post;
import com.project2.cms.services.PostService;

@RestController
public class PostController {

	
	private PostService postService;
	
	@PostMapping("/addPost")
	public Post addPost(@Validated @RequestBody Post post, HttpSession session) {
		return postService.createPost(post);
	}
	
	@PostMapping("/addPosts")
	public List<Post> addPosts(@RequestBody List<Post> posts){
		return postService.createPosts(posts);
	}
	
	@GetMapping("/post/{id}")
	public Post findPostById(@PathVariable(value= "id") Integer id){
		return postService.getPostById(id);
	}
	
	@GetMapping("/posts/{status}")
	public Post findPostByStatus(@PathVariable int status) {
		return postService.getPostsByStatus(status);
	}
	
	@PutMapping("/update")
	public Post updatePost(@RequestBody Post post) {
		return postService.updatePost(post);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deletePost(@PathVariable int id) {
		return postService.deletePost(id);
	}
	
}
