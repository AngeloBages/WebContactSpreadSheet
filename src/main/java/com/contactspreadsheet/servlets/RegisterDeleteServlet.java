package com.contactspreadsheet.servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.contactspreadsheet.dao.*;
import com.contactspreadsheet.models.Contact;

@WebServlet(urlPatterns= {"/registerDelete"})
public class RegisterDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao<Contact> dao = new ContactDaoImpl();
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
			dao.delete(id);
		} catch (SQLException e) {
			req.setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}
		
		resp.sendRedirect("/contact-spreadsheet/");	
	}

	
}
