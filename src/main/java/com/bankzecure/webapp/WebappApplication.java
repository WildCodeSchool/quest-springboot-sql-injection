package com.bankzecure.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.bankzecure.webapp.repository.CustomerRepository;
import com.bankzecure.webapp.entity.Customer;

@Controller
@SpringBootApplication
public class WebappApplication {
  private CustomerRepository repository = new CustomerRepository();

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
  }

  @GetMapping("/")
  String home() {
    return "login_form";
  }

  @PostMapping("/login")
  String login(Model model, @RequestParam String identifier, @RequestParam String password) {
    Customer customer = repository.findByIdentifierAndPassword(identifier, password);
    model.addAttribute("customer", customer);
    return "profile";
  }

}
