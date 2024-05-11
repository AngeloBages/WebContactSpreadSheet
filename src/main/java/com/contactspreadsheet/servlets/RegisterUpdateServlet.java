package com.contactspreadsheet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.contactspreadsheet.dao.implementations.ContactDaoJpaImpl;
import com.contactspreadsheet.dao.interfaces.ContactDao;
import com.contactspreadsheet.models.Contact;
import com.contactspreadsheet.utils.ServletAction;

@WebServlet(urlPatterns= {"/registerUpdate"})
public class RegisterUpdateServlet extends HttpServlet implements ServletAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Connection connection = (Connection) req.getAttribute("connection");
		ContactDao dao =new ContactDaoJpaImpl();
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
		
		Date date = Date.from(contact.getBirthDate().atTime(LocalTime.of(0, 0))
				              .atZone(ZoneOffset.systemDefault()).toInstant());
		
		req.setAttribute("contact", contact);
		req.setAttribute("contactBirthDate", date);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Connection connection = (Connection) req.getAttribute("connection");
		ContactDao dao = new ContactDaoJpaImpl();
		int id = Integer.parseInt(req.getParameter("id"));
		Contact contact = new Contact();
		
		try {
			contact.setId(id);
			contact.setName(req.getParameter("name"));
			contact.setEmail(req.getParameter("email"));
			contact.setAddress(req.getParameter("address"));
			contact.setBirthDate(LocalDate.parse(req.getParameter("birth_date"), DateTimeFormatter.ofPattern("dd/M/yyyy")));
		
			dao.update(contact);
			
		} catch (SQLException e) {
			req.getSession().setAttribute("errorMessage", e.getMessage());
			req.getRequestDispatcher("WEB-INF/javapages/index.jsp");
			e.printStackTrace();
		} catch (RuntimeException e) {
			req.getSession().setAttribute("errorMessage", "An error has ocurred!");
			req.getRequestDispatcher("WEB-INF/javapages/index.jsp");
			e.printStackTrace();
		}
		
		resp.sendRedirect("/contact-spreadsheet/");
	}

	@Override
	public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		return "WEB-INF/javapages/registerUpdate.jsp";
	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
