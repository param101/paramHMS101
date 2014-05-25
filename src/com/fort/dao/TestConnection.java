package com.fort.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class TestConnection {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url =
				 "jdbc:mysql://localhost:3306/HMS";
			Connection con = DriverManager.getConnection( url,"hmsuser", "hmspass");
			PreparedStatement st = con.prepareStatement( "select * from dashboard" );
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Integer id = rs.getInt(1);
				Time time = rs.getTime(2);
				System.out.println( "id " + id + "\ttime " + time );
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
