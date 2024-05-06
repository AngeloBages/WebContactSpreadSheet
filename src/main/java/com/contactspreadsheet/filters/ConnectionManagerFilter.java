package com.contactspreadsheet.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.contactspreadsheet.main.ConnectionFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class ConnectionManagerFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		Connection connection = ConnectionFactory.getConnection();	
		req.setAttribute("connection", connection);
		
		chain.doFilter(req, resp);
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
