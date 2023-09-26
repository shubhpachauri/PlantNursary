package com.amdocs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class getConnection {
	
	public Connection getCon()
	{
		Connection conn=null;

	       String query1;
	       String query2;
	       ResultSet rs;
	       
	       try {
	        // Load the MySQL JDBC driver
	Class.forName("oracle.jdbc.OracleDriver"); //registration
	System.out.println("Inside try after class.forname");
	conn=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger"); //connection

	        // Check if the connection is successful
	        if (conn != null) {
	            System.out.println("Connected to the MySQL database!");
	            // You can perform database operations here
	        } else {
	            System.out.println("Failed to connect to the database.");
	        }

	       }
	       catch (Exception e) {
	           e.printStackTrace();
	       }
	       
	       return conn;
	}
}



		