package com.bankzecure.webapp.repository;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.bankzecure.webapp.entity.*;
import com.bankzecure.webapp.JdbcUtils;

public class CreditCardRepository {
  private final static String DB_URL = "jdbc:mysql://localhost:3306/springboot_bankzecure?serverTimezone=GMT&noAccessToProcedureBodies=true";
	private final static String DB_USERNAME = "bankzecure";
	private final static String DB_PASSWORD = "Ultr4B4nk@L0nd0n";

  public List<CreditCard> findByCustomer(final String identifier) {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet resultSet = null;
    try {
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      statement = connection.prepareCall("{call getCustomerCreditCards(?)}");
      statement.setString(1, identifier);
      statement.execute();
      resultSet = statement.getResultSet();

      final List<CreditCard> creditCards = new ArrayList<CreditCard>();

      while (resultSet.next()) {
        final int id = resultSet.getInt("id");
        final int customerId = resultSet.getInt("customer_id");
        final String type = resultSet.getString("type");
        final String number = resultSet.getString("number");
        final String cvv = resultSet.getString("cvv");
        final String expiry = resultSet.getString("expiry");
        creditCards.add(new CreditCard(id, customerId, type, number, cvv, expiry));
      }

      return creditCards;
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