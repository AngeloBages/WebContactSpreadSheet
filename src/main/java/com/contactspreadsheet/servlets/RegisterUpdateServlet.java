package com.contactspreadsheet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.contactspreadsheet.dao.*;
import com.contactspreadsheet.models.Contact;

@WebServlet(urlPatterns= {"/registerUpdate"})
public class RegisterUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao<Contact> dao = new ContactDaoImpl();
		int id = Integer.parseInt(req.getParameter("id"));
		Contact contact = null;
		
		try {
			contact = dao.get(id);
		} catch (NumberFormatException | SQLException e) {
			req.getSession().setAttribute("errorMessage", e.getMessage());
			
			if(contact == null) {
				req.getSession().setAttribute("errorMessage", "The contact id does not exist");
			}
			resp.sendRedirect("/contact-spreadsheet/");
			e.printStackTrace();
		}
		
		req.setAttribute("contact", contact);
		
		req.getRequestDispatcher("WEB-INF/javapages/registerUpdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao<Contact> dao = new ContactDaoImpl();
		int id = Integer.parseInt(req.getParameter("id"));
		Contact contact = new Contact();
		
		contact.setName(req.getParameter("name"));
		contact.setEmail(req.getParameter("email"));
		contact.setAddress(req.getParameter("address"));
		contact.setBirthDate(LocalDate.parse(req.getParameter("birth_date"), DateTimeFormatter.ofPattern("dd/M/yyyy")));
		
		try {
			dao.update(id, contact);
		} catch (SQLException e) {
			req.getSession().setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}
		
		resp.sendRedirect("/contact-spreadsheet/");
	}
}
