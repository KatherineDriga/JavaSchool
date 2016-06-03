package com.mycompany.app.JavaSchool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {

	public static void main(String[] args) {
		
	String url="jdbc:mysql://localhost/railage";
	String user="admin";
	String password="password";
	
	Connection con=null;
	
	try {
		con=DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
		
	try {
		PreparedStatement pstmt = con.prepareStatement(
				"select numberOfSeats from train where trainId!=?");
		pstmt.setInt(1, 1);
		ResultSet res=pstmt.executeQuery();
		List list = new ArrayList();
		while (res.next()){
			list.add(res.getString(1));
		}
		System.out.println(list);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
}
