package it.polito.tdp.ExampleSqlPreparedStatement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe non public perchè solo le classi all'interno del package 'db' 
// devono poter chiamare questa classe
class DBConnect {
	
	public static Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost/Babs?user=root&password=asdru2001";
		return DriverManager.getConnection(jdbcURL);
	}

}
