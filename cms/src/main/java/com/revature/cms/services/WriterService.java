package com.revature.cms.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.cms.models.Writer;
import com.revature.cms.repositories.WriterRepository;

@Service
public class WriterService {
  
  @Autowired
  WriterRepository writerRepository;
  
  public List<Writer> getAll() {
    return writerRepository.findAll();
  }
}
