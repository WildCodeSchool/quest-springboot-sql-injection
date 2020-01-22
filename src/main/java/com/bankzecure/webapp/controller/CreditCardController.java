package com.bankzecure.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.bankzecure.webapp.repository.*;

@Controller
public class CreditCardController {
  private CreditCardRepository repository = new CreditCardRepository();
  
  @GetMapping("/customers/{identifier}/credit-cards")
  String getAll(Model model, @PathVariable String identifier) {
    model.addAttribute("creditCards", repository.findByCustomer(identifier));
    return "customer_cards";
  }

}