package com.bankzecure.webapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.jdbc.ScriptRunner;

public class SqlInitializer {
  private final static String DB_URL = "jdbc:mysql://localhost:3306/springboot_bankzecure?serverTimezone=GMT";
	private final static String DB_USERNAME = "bankzecure";
  private final static String DB_PASSWORD = "Ultr4B4nk@L0nd0n";

	public void resetDatabase() throws Exception {
		
		String sqlScriptPath = "./src/main/resources/db/reset.sql";
		
		// Create MySql Connection
    // Class.forName("com.mysql.jdbc.Driver");
		try {
      Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      Statement stmt = null;
  
        // Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con);

			// Give the input file to Reader
			Reader reader = new BufferedReader(
                               new FileReader(sqlScriptPath));

			// Exctute script
			sr.runScript(reader);

		} catch (Exception e) {
			System.err.println("Failed to Execute" + sqlScriptPath
          + " The error is " + e.getMessage());
      throw new Exception("Cannot read file " + sqlScriptPath);
		}
	}
}