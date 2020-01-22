package com.bankzecure.webapp.repository;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bankzecure.webapp.entity.*;
import com.bankzecure.webapp.JdbcUtils;

public class CustomerRepository {
  private final static String DB_URL = "jdbc:mysql://localhost:3306/spring_ultrabank?serverTimezone=GMT";
	private final static String DB_USERNAME = "ultrabank";
	private final static String DB_PASSWORD = "Ultr4B4nk@L0nd0n";

  public Customer findByIdentifierAndPassword(final String identifierParam, final String password) {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    final String query = "SELECT * FROM customer WHERE identifier = '" + identifierParam + "' AND password = '" + password + "'";
    System.out.println(query);
    try {
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);

      Customer customer = null;

      if (resultSet.next()) {
        final int id = resultSet.getInt("id");
        final String identifier = resultSet.getString("identifier");
        final String firstName = resultSet.getString("first_name");
        final String lastName = resultSet.getString("last_name");
        final String email = resultSet.getString("email");
        customer = new Customer(id, identifier, firstName, lastName, email);
      }
      return customer;
    } catch (final SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtils.closeResultSet(resultSet);
      JdbcUtils.closeStatement(statement);
      JdbcUtils.closeConnection(connection);
    }
    return null;
  }
}