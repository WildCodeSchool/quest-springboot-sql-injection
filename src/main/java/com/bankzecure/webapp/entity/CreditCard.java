package com.bankzecure.webapp.entity;

import java.util.*;

public class CreditCard {

  private long id;
  private long customerId;
  private String type;
  private String number;
  private String cvv;
  private String expiry;

  public CreditCard(long id, long customerId, String type, String number, String cvv, String expiry) {
    this.id = id;
    this.customerId = customerId;
    this.type = type;
    this.number = number;
    this.cvv = cvv;
    this.expiry = expiry;
  }

  public long getId() {
    return id;
  }

  public long getCustomerId() {
    return customerId;
  }

  public String getType() {
    return type;
  }

  public String getNumber() {
    return number;
  }

  public String getFormattedNumber() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(number.substring(0, 4));
    for (int i = 4 ; i < number.length() ; i += 4) {
      stringBuilder.append(" ");
      stringBuilder.append(number.substring(i, i + 4));
    }
    return stringBuilder.toString();
  }

  public String getCvv() {
    return cvv;
  }

  public String getExpiry() {
    return expiry;
  }
}
