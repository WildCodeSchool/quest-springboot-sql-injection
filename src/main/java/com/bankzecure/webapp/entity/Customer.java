package com.bankzecure.webapp.entity;

import java.util.Date;

public class Customer {

  private long id;
  private String identifier;
  private String firstName;
  private String lastName;
  private String email;

  public Customer(long id, String identifier, String firstName, String lastName, String email) {
    this.id = id;
    this.identifier = identifier;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public String getIdentifier() {
    return identifier;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public String getEmail() {
    return email;
  }
}
