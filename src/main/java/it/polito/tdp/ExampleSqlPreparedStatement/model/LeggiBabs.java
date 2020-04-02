package it.polito.tdp.ExampleSqlPreparedStatement.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeggiBabs {
	
	public void run() {
		
		// 1. Identificazione del Database
		String jdbcURL = "jdbc:mysql://localhost/Babs?user=root&password=asdru2001";

		//2. Connessione col Database 
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			//3. Eseguire una Query (fare prova su SequelPro)
			String sql = "SELECT NAME FROM station WHERE landmark = ?";
			
// 			PREPAREDSTATEMENT: qualora usassi il PreparedStatement (DA USARE!!!)
// 			Bisogna cambiare due semplici istruzioni:
// 			Statement st = conn.createStatement();
// 			String sql = "SELECT NAME FROM station";
// 			con
// 			String sql = "SELECT name FROM station";
			
			//4. Aprire lo statement
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, "Palo Alto");
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				String nomeStazione = res.getString("name");
				System.out.println(nomeStazione);
			}
			
			//5. chiudere statement
			st.close();
			
			// 6. (Ultimo) Chiudere la connessione!
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) {
		LeggiBabs babs = new LeggiBabs();
		babs.run();
	}
	
	

}
