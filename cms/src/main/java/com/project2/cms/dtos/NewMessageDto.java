package com.project2.cms.dtos;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewMessageDto {
  
//  @NotNull
//  private Integer messageId;
  @NotNull
  private Integer senderId;
  @NotNull
  private String messageText;
  @NotNull
  private String messageStatus;
  @NotNull
  private Integer inboxId;
//  public Integer getMessageId() {
//    return messageId;
//  }
//  public void setMessageId(Integer messageId) {
//    this.messageId = messageId;
//  }
  public Integer getSenderId() {
    return senderId;
  }
  public void setSenderId(Integer senderId) {
    this.senderId = senderId;
  }
  public String getMessageText() {
    return messageText;
  }
  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }
  public String getMessageStatus() {
    return messageStatus;
  }
  public void setMessageStatus(String messageStatus) {
    this.messageStatus = messageStatus;
  }
  public Integer getInboxId() {
    return inboxId;
  }
  public void setInboxId(Integer inboxId) {
    this.inboxId = inboxId;
  }


  
}
