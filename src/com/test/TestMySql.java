package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.Test;

public class TestMySql {
	
	@Test
	public void testConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_jtcw","root","root");
		ResultSet rs = conn.prepareStatement("select userName from t_admin").executeQuery() ;
		if(rs.next()){
			System.out.println(rs.getString("userName"));
		}
	}

}
