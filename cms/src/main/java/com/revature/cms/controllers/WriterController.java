package com.revature.cms.controllers;

import java.util.List;

//import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cms.exceptions.ResourceNotFoundException;
import com.revature.cms.models.Writer;
import com.revature.cms.repositories.WriterRepository;
//import com.revature.cms.services.WriterService;

@RestController
@RequestMapping( path="writers")
public class WriterController {
  
	
//	@Autowired
//	  private WriterService writerService;
	
  @Autowired
  private WriterRepository writerRepository;
//	@GetMapping("/simple")
//	public String grret() {
//		return "hi there";
//	}
	//get all writers
  @GetMapping("/")
  public List<Writer> getAllWriters() {
    return this.writerRepository.findAll();
  }
  
  // get writer by id
  @GetMapping("/{id}")
  public Writer getWriterById(@PathVariable (value = "id") Integer writerid){
	  return this.writerRepository.findById(writerid).
			  orElseThrow(() -> new ResourceNotFoundException("writer Not found with id "+ writerid));
	   }
  
  //create writer
  @PostMapping
  public Writer createWriter(@RequestBody Writer writer) {
	  return this.writerRepository.save(writer);
  }
  
  // update writer
  @PutMapping("/{id}")
  public Writer updateWriter(@RequestBody Writer writer, @PathVariable ("id") Integer writerid) {
	  
	  	Writer existingwriter = this.writerRepository.findById(writerid)
				  .orElseThrow(() -> new ResourceNotFoundException("writer Not found with id "+ writerid));
	  	existingwriter.setFirstname(writer.getFirstname());
	  	existingwriter.setLastname(writer.getLastname());
	  	existingwriter.setEmail(writer.getEmail());
	  	existingwriter.setUsername(writer.getUsername());
	  	existingwriter.setPassword(writer.getPassword());
	  	return this.writerRepository.save(existingwriter);
	  
  }
  
  
  //delete writer by id
  @DeleteMapping("/{id}")
  public ResponseEntity<Writer> deleteWriter(@PathVariable ("id") Integer writerid){
	  
	  Writer existingWriter = this.writerRepository.findById(writerid)
			  .orElseThrow(() -> new ResourceNotFoundException("writer Not found with id "+ writerid));
	  this.writerRepository.delete(existingWriter);
	  return ResponseEntity.ok().build();
  }

//@Override
//public Object convertToDatabaseColumn(Object attribute) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public Object convertToEntityAttribute(Object dbData) {
//	// TODO Auto-generated method stub
//	return null;
//}
  

}

