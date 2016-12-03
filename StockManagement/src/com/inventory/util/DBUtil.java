package com.inventory.util;

import java.sql.*;

public class DBUtil {
	
	public static Connection getDBConnection() throws SQLException, ClassNotFoundException{
		Connection c = null;
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/inventory","postgres","pgsql");
		return c;
	}
	
}
