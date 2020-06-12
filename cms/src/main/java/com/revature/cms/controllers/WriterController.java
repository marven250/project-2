package com.revature.cms.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.cms.models.Writer;
import com.revature.cms.services.WriterService;

@RequestMapping(path = "/writers")
@RestController
public class WriterController {
  
  @Autowired
  WriterService writerService;

  @GetMapping
  public List<Writer> getAllWriters() {
    return writerService.getAll();
  }

}
