package it.polito.tdp.ExampleSqlPreparedStatement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ExampleSqlPreparedStatement.model.Station;

// Sarà la classe che contiene i metodi che mi servono per accedere al DB.
public class BabsDAO {
	
	// interroga il DB chiedendo di restituire una lista di tutte le stazioni
	public List<Station> listStation() {
		
		List<Station> result = new ArrayList<>();
		
		String sql = "SELECT station_id, name, dockcount, landmark FROM station";
		
//		String jdbcURL = "jdbc:mysql://localhost/Babs?user=root&password=asdru2001";
 
		try {
			// = DBConnect.getConnection() perché ho creato una classe apposita.
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Station s = new Station(res.getInt("station_id"), 
						res.getString("name"),
						res.getInt("dockcount"),
						res.getString("landmark"));
				result.add(s);
			}
			
			//5. chiudere statement
			st.close();
			
			// 6. (Ultimo) Chiudere la connessione!
			conn.close();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<Station> listStationByLandmark(String landmark) {
List<Station> result = new ArrayList<>();
		
		String sql = "SELECT station_id, name, dockcount, landmark FROM station "
				+ "WHERE landmark = ?";
		
//		String jdbcURL = "jdbc:mysql://localhost/Babs?user=root&password=asdru2001";
 
		try {
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, landmark);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Station s = new Station(res.getInt("station_id"), 
						res.getString("name"),
						res.getInt("dockcount"),
						res.getString("landmark"));
				result.add(s);
			}
			
			st.close();
			
			conn.close();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
