package com.contactspreadsheet.servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.contactspreadsheet.dao.implementations.ContactDaoJpaImpl;
import com.contactspreadsheet.dao.interfaces.ContactDao;
import com.contactspreadsheet.utils.ServletAction;

@WebServlet(urlPatterns= {"/registerDelete"})
public class RegisterDeleteServlet extends HttpServlet implements ServletAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Connection connection = (Connection) req.getAttribute("connection");
		ContactDao dao = new ContactDaoJpaImpl();
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			req.setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}
		
		resp.sendRedirect("/contact-spreadsheet/");	
	}

	@Override
	public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		return null;
	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
}
