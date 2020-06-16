package com.project2.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project2.cms.dtos.NewMessageDto;
import com.project2.cms.model.Inbox;
import com.project2.cms.model.Message;
import com.project2.cms.repository.WriterRepository;
import com.project2.cms.services.InboxService;
import com.project2.cms.services.MessageService;
import com.project2.cms.services.WriterService;

@RestController
public class MessageController {
  
  @Autowired
  MessageService messageService;
  @Autowired
  WriterRepository writerRepository;
  @Autowired
  InboxService inboxService;
  
  @PostMapping("/messages")
  public Message addNewMessage(@RequestBody NewMessageDto dto) {

    dto = new NewMessageDto();
    try {
      Message ourMessage = new Message(1, writerRepository.findById(dto.getSenderId()).get(), dto.getMessageText(), dto.getMessageStatus(), inboxService.getInboxById(dto.getInboxId()));
      return messageService.createMessage(ourMessage);
    } catch (Throwable e) {
        System.out.println("HERE IS THE ERROR!!!!!!!!! ======>>> : " + e);
        throw e;
    }
  }

}
