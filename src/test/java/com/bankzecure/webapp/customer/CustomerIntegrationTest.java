package com.bankzecure.webapp.customer;

import com.bankzecure.webapp.entity.Customer;
import com.bankzecure.webapp.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
import com.bankzecure.webapp.SqlInitializer;;

@SpringBootTest
class CustomerIntegrationTest {

  @Autowired
  private WebApplicationContext applicationContext;

  private CustomerRepository customerRepository;

  private MockMvc mockMvc;

  private SqlInitializer sqlInitializer;

  @BeforeEach
  void setup() throws Exception {
    this.sqlInitializer = new SqlInitializer();
    this.sqlInitializer.resetDatabase();
    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(applicationContext)
      .build();
  }

  /*------------------------------------*
   | Sign-in tests                      |
   *------------------------------------*/
  @Test
  void userRegularLoginOk() throws Exception {
    MvcResult requestResult = this.mockMvc.perform(
      post("/customers/authenticate")
              .param("identifier", "602134")
              .param("password", "63qae4nr6"))
      //.andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andReturn();

    String htmlBody = requestResult.getResponse().getContentAsString();
    assertThat(htmlBody, containsString("<li>Identifier: 602134</li>"));
  }

  @Test
  void userRegularLoginNotOk() throws Exception {
    MvcResult requestResult = this.mockMvc.perform(
      post("/customers/authenticate")
              .param("identifier", "404404")
              .param("password", "user.not.found"))
      //.andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andReturn();

    String htmlBody = requestResult.getResponse().getContentAsString();
    assertThat(htmlBody, containsString("Error: account not found or incorrect password"));
  }

  @Test
  void userMaliciousLoginNotOk() throws Exception {
    MvcResult requestResult = this.mockMvc.perform(
      post("/customers/authenticate")
              .param("identifier", "' OR 1=1 -- ;")
              .param("password", ""))
      //.andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andReturn();

    String htmlBody = requestResult.getResponse().getContentAsString();
    assertThat(htmlBody, containsString("Error: account not found or incorrect password"));
  }

  /*------------------------------------*
   |Profile update tests                |
   *------------------------------------*/
  @Test
  void userRegularUpdateEmailAndPasswordOk() throws Exception {
    MvcResult requestResult = this.mockMvc.perform(
      post("/customers/update")
              .param("identifier", "602134")
              .param("email", "foo.bar.0@spring.io")
              .param("password", "updated.0"))
      //.andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andReturn();

    String htmlBody = requestResult.getResponse().getContentAsString();
    assertThat(htmlBody, containsString("Profile updated!"));
  }
  @Test
  void userRegularUpdateEmailOnlyOk() throws Exception {
    MvcResult requestResult = this.mockMvc.perform(
      post("/customers/update")
              .param("identifier", "602134")
              .param("email", "foo.bar.1@spring.io")
              .param("password", ""))
      //.andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andReturn();

    String htmlBody = requestResult.getResponse().getContentAsString();
    assertThat(htmlBody, containsString("Profile updated!"));
  }

  // @Test
  // void userRegularUpdateNotOk() throws Exception {
  //   MvcResult requestResult = this.mockMvc.perform(
  //     post("/customers/authenticate")
  //             .param("identifier", "404404")
  //             .param("email", "user.not.found@nowhere.org"))
  //     //.andDo(MockMvcResultHandlers.print())
  //     .andExpect(status().isOk())
  //     .andReturn();

  //   String htmlBody = requestResult.getResponse().getContentAsString();
  //   assertThat(htmlBody, containsString("Error: account not found or incorrect password"));
  // }

}