package com.bankzecure.webapp.customer;

import com.bankzecure.webapp.entity.Customer;
import com.bankzecure.webapp.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CustomerIntegrationTest {

  @Autowired
  private WebApplicationContext applicationContext;

  private CustomerRepository customerRepository;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(applicationContext)
      .build();
  }

  @Test
  void userRegularLoginHttpStatusOk() throws Exception {
    MvcResult requestResult = this.mockMvc.perform(
      post("/customers/authenticate")
              .param("identifier", "602134")
              .param("password", "63qae4nr6"))
      .andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andReturn();

    String htmlBody = requestResult.getResponse().getContentAsString();
    assertThat(htmlBody, containsString("<li>Identifier: 602134</li>"));
  }

}