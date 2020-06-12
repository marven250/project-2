package com.revature.cms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "writers")

public class Writer {

  @Id
  @Column(name = "writerid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer writerId;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  public Integer getWriterId() {
    return writerId;
  }
  public void setWriterId(Integer writerId) {
    this.writerId = writerId;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
  
  
//  public Writer(Integer writerId, String username, String password) {
//    super();
//    this.writerId = writerId;
//    this.username = username;
//    this.password = password;
//  }
  @Override
  public String toString() {
    return "Writer [writerId=" + writerId + ", username=" + username + ", password=" + password
        + "]";
  }
  
  
}
