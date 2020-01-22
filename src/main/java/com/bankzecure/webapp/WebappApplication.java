package com.bankzecure.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bankzecure.webapp.repository.CustomerRepository;

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

}
