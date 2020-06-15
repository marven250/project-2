package com.project2.cms.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

import com.project2.cms.model.Writer;
import com.project2.cms.repository.WriterRepository;
import com.project2.cms.services.WriterService;
import com.project2.cms.exception.ResourceNotFoundException;

@RestController
@Controller
@RequestMapping("/")
public class WriterController {
	
	@Autowired
	  WriterService writerService;
    @Autowired
    private WriterRepository writerRepository;

    @GetMapping("/writers")
    public List<Writer> getAllEmployees(HttpSession session) {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
        return writerRepository.findAll();
    	}else{
    	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/writers/{id}")
    public ResponseEntity<Writer> getEmployeeById(@PathVariable(value = "id") Integer writerid,HttpSession session)
        throws ResourceNotFoundException {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    	 Writer writer = writerRepository.findById(writerid).orElseThrow(() -> new ResourceNotFoundException("writer not found for this id :: " + writerid));
        return ResponseEntity.ok().body(writer);
    	}else{
  	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
      }
    }
    
    
    @PostMapping("/writers")
    public Writer createEmployee(@Validated @RequestBody Writer writer, HttpSession session) {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
        return writerRepository.save(writer);
    	}else{
    	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/writers/{id}")
    public ResponseEntity<Writer> updateWriter(@PathVariable(value = "id") Integer writerid,
         @Validated @RequestBody Writer writerDetails,  HttpSession session) throws ResourceNotFoundException {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    	Writer writer = writerRepository.findById(writerid)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + writerid));

       if(writerDetails.getUsername().toString().trim() != "" && !(writerDetails.getUsername().toString().trim().isEmpty())) { 
    	   writer.setUsername(writerDetails.getUsername());}
       if(writerDetails.getPassword().toString().trim() != "" && !(writerDetails.getPassword().toString().trim().isEmpty())) { 
    	   writer.setPassword(writerDetails.getPassword());}
       if(writerDetails.getFirstname().toString().trim() != "" && !(writerDetails.getFirstname().toString().trim().isEmpty())) { 
    	   writer.setFirstname(writerDetails.getFirstname());}
       if(writerDetails.getLastname().toString().trim() != "" && !(writerDetails.getLastname().toString().trim().isEmpty())) { 
    	   writer.setLastname(writerDetails.getLastname());}
       if(writerDetails.getEmail().toString().trim() != "" && !(writerDetails.getEmail().toString().trim().isEmpty())) { 
    	   writer.setEmail(writerDetails.getEmail());}
       if(writerDetails.getPhone().toString().trim() != "" && !(writerDetails.getPhone().toString().trim().isEmpty())) { 
    	   writer.setPhone(writerDetails.getPhone());}
       String string = writerDetails.getPermission().toString();
       try {
           Double x = Double.parseDouble(string);
           writer.setPermission(writerDetails.getPermission());
       } catch (NumberFormatException e) {
           e.getMessage();
       }
       if(writerDetails.getPermission().toString().trim() != "" && !(writerDetails.getPermission().toString().trim().isEmpty())) { 
    	   writer.setPermission(writerDetails.getPermission());}
       
       
       final Writer updatedWriter = writerRepository.save(writer);
        return ResponseEntity.ok(updatedWriter);
    	}else{
  	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
      }
    }

    @DeleteMapping("/writers/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer writerid, HttpSession session)
         throws ResourceNotFoundException {
    	if (session.getAttribute("isLoggedIn") != null
    	        && (Boolean) session.getAttribute("isLoggedIn")) {
    	Writer writer = writerRepository.findById(writerid)
       .orElseThrow(() -> new ResourceNotFoundException("Writer not found for this id :: " + writerid));

    	writerRepository.delete(writer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    	}else{
    	      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
    
    // To store values on a session similar to express session, just add HttpSession as a parameter to
    // your Controller method
    // Then use methods on the session to set/get attributes
    @PostMapping("/login")
    public Writer attemptLogin(@RequestBody Credentials creds, HttpSession session) {
      Boolean isLoggedIn =writerService.checkCredentials(creds.getUsername(), creds.getPassword());

      session.setAttribute("isLoggedIn", isLoggedIn);
      
      List<Writer> store = writerRepository.checkUsernamePassword(creds.getUsername(), creds.getPassword());
      if(isLoggedIn == false) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      //cookies
      
      Cookie permission = new Cookie("permission", store.get(0).getPermission().toString());
      permission.setMaxAge(7 * 24 * 60 * 60);
      permission.setSecure(true);
      permission.setHttpOnly(true);
      permission.setPath("/"); 
      Cookie id = new Cookie("id", store.get(0).getWriterid().toString());
      id.setMaxAge(7 * 24 * 60 * 60);
      id.setSecure(true);
      id.setHttpOnly(true);
      id.setPath("/"); 

//      response.addCookie(permission);
//      response.addCookie(id);

      return store.get(0);
    }
    @PostMapping("/logout")
    public String info(HttpSession session) {
    	String xxx = session.getAttribute("isLoggedIn").toString();
//         session.setAttribute("isLoggedIn", false);
         return xxx;

    }
    
    

@GetMapping("/checkCookies")
public String readAllCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        return Arrays.stream(cookies)
                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
    }
    return "No cookies";
}
    
}