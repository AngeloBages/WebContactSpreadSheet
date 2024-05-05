package com.contactspreadsheet.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public static Connection getConnection() {
		
		Connection con = null;
		
		Properties dbProperties = new Properties();
		InputStream is = ConnectionFactory.class.getResourceAsStream("../resources/dbconnection.properties");
		
		try {
			
			if(is == null) {
				throw new FileNotFoundException();
			}
			
			dbProperties.load(is);
			String url = dbProperties.getProperty("url");
			String user = dbProperties.getProperty("userName");
			String password = dbProperties.getProperty("password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			con = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
