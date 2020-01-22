package com.bankzecure.webapp.repository;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bankzecure.webapp.entity.*;
import com.bankzecure.webapp.JdbcUtils;

public class CustomerRepository {
  private final static String DB_URL = "jdbc:mysql://localhost:3306/springboot_bankzecure?serverTimezone=GMT";
	private final static String DB_USERNAME = "bankzecure";
	private final static String DB_PASSWORD = "Ultr4B4nk@L0nd0n";

  public Customer findByIdentifierAndPassword(final String identifierParam, final String password) {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      statement = connection.createStatement();
      final String query = "SELECT * FROM customer WHERE identifier = '" + identifierParam + "' AND password = '" + password + "'";
      System.out.println(query);
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

  public Customer update(String identifierParam, String emailParam, String password) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    Customer customer = null;
    try {
        // Connection and statement
        connection = DriverManager.getConnection(
          DB_URL, DB_USERNAME, DB_PASSWORD
        );
        statement = connection.createStatement();

        // Build the update query using a QueryBuilder
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE customer SET email = '" + emailParam + "'");
        // Don't set the password in the update, if it's not provided
        if (password != "") {
          queryBuilder.append(",password = '" + password + "'");
        }
        queryBuilder.append(" WHERE identifier = '" + identifierParam + "'");
        String query = queryBuilder.toString();
        System.out.println(query);
        statement.executeUpdate(query);

        JdbcUtils.closeStatement(statement);
        JdbcUtils.closeConnection(connection);

        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        statement = connection.createStatement();
        query = "SELECT * FROM customer WHERE identifier = '" + identifierParam + "'";
        resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
          final int id = resultSet.getInt("id");
          final String identifier = resultSet.getString("identifier");
          final String firstName = resultSet.getString("first_name");
          final String lastName = resultSet.getString("last_name");
          final String email = resultSet.getString("email");
          customer = new Customer(id, identifier, firstName, lastName, email);
        }
        return customer;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
   	    JdbcUtils.closeStatement(statement);
   	    JdbcUtils.closeConnection(connection);
    }
    return null;
}
}