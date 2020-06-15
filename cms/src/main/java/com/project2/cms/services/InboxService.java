package com.project2.cms.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project2.cms.model.Inbox;
import com.project2.cms.repository.InboxRepository;

@Service
public class InboxService {

  @Autowired
  InboxRepository inboxRepository;
  
  public Inbox getInboxById(Integer id) {
    Optional<Inbox> inbox = inboxRepository.findById(id);
    return inbox.get();
  }
}
