package com.project2.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project2.cms.model.Posts;
import com.project2.cms.repository.PostsRepository;
import com.project2.cms.services.PostsService;
import com.project2.cms.exception.ResourceNotFoundException;

@RestController
@Controller
@RequestMapping("/")
public class PostsController {
	
	@Autowired
	  PostsService postService;
    @Autowired
    private PostsRepository postRepository;

    @GetMapping("/posts")
    public List<Posts> getAllPosts(HttpSession session) {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    		if((Integer)session.getAttribute("postpermission") != 1) {
    			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    			}
        return postRepository.findAll();
    	}else{
    	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Posts> getPostById(@PathVariable(value = "id") Integer postId,HttpSession session)
        throws ResourceNotFoundException {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    		Posts post = postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    	        return ResponseEntity.ok().body(post);
    			

    	}else{
  	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
      }
    }
    
    
    @PostMapping("/posts")
    public Posts createPost(@Validated @RequestBody Posts post, HttpSession session) {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    		
        return postRepository.save(post);
    	}else{
    	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
    
    //update posts (admin: && the posts for his own)

    @PutMapping("/posts/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable(value = "id") Integer postId,
         @Validated @RequestBody Posts postDetails,  HttpSession session) throws ResourceNotFoundException {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    		
Posts post = postRepository.findById(postId)
    			        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    			       if(postDetails.getAuthor().toString().trim() != "" && !(postDetails.getAuthor().toString().trim().isEmpty())) { 
    			    	   post.setAuthor(postDetails.getAuthor());}
    			       if(postDetails.getPostTitle().toString().trim() != "" && !(postDetails.getPostTitle().toString().trim().isEmpty())) { 
    			    	   post.setPostTitle(postDetails.getPostTitle());}
    			       if(postDetails.getPostDescription().toString().trim() != "" && !(postDetails.getPostDescription().toString().trim().isEmpty())) { 
    			    	   post.setPostDescription(postDetails.getPostDescription());}
    			       if(postDetails.getPostText().toString().trim() != "" && !(postDetails.getPostText().toString().trim().isEmpty())) { 
    			    	   post.setPostText(postDetails.getPostText());}
    			       if(postDetails.getDateSubmitted().toString().trim() != "" && !(postDetails.getDateSubmitted().toString().trim().isEmpty())) { 
    			    	   post.setDateSubmitted(postDetails.getDateSubmitted());}
    			       if(postDetails.getDatePublished().toString().trim() != "" && !(postDetails.getDatePublished().toString().trim().isEmpty())) { 
    			    	   post.setDatePublished(postDetails.getDatePublished());}
    			       if(postDetails.getPostType().toString().trim() != "" && !(postDetails.getPostType().toString().trim().isEmpty())) { 
    			    	   post.setPostType(postDetails.getPostType());}
    			       if(postDetails.getPostField().toString().trim() != "" && !(postDetails.getPostField().toString().trim().isEmpty())) { 
    			    	   post.setPostField(postDetails.getPostField());}
    			       if(postDetails.getKeyWords().toString().trim() != "" && !(postDetails.getKeyWords().toString().trim().isEmpty())) { 
    			    	   post.setKeyWords(postDetails.getKeyWords());}
    			       String string1 = postDetails.getResolver().toString();
    			       try {
    			           @SuppressWarnings("unused")
						Double x = Double.parseDouble(string1);
    			           post.setResolver(postDetails.getResolver());
    			       } catch (NumberFormatException e) {
    			           e.getMessage();
    			       }
    			       if(postDetails.getResolver().toString().trim() != "" && !(postDetails.getResolver().toString().trim().isEmpty())) { 
    			    	   post.setResolver(postDetails.getResolver());}
    			       
    			       if(postDetails.getStatus().toString().trim() != "" && !(postDetails.getStatus().toString().trim().isEmpty())) { 
    			    	   post.setStatus(postDetails.getStatus());}
    			       
    			       String string2 = postDetails.getPublished().toString();
    			       try {
    			           @SuppressWarnings("unused")
						Double x = Double.parseDouble(string2);
    			           post.setPublished(postDetails.getPublished());
    			       } catch (NumberFormatException e) {
    			           e.getMessage();
    			       }
    			       if(postDetails.getResolver().toString().trim() != "" && !(postDetails.getPublished().toString().trim().isEmpty())) { 
    			    	   post.setPublished(postDetails.getPublished());}
    			       
    			       
    			       final Posts updatedPost = postRepository.save(post);
    			        return ResponseEntity.ok(updatedPost);
    			

    	
    	}else{
  	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
      }
    }

    @DeleteMapping("/posts/{id}")
    public Map<String, Boolean> deletePost(@PathVariable(value = "id") Integer postId, HttpSession session)
         throws ResourceNotFoundException {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    	Posts post = postRepository.findById(postId)
       .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));

    	postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    	}else{
    	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    
    
    
}