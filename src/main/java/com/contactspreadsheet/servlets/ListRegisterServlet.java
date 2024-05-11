package com.contactspreadsheet.servlets;

import java.io.IOException;

import com.contactspreadsheet.dao.implementations.ContactDaoJpaImpl;
import com.contactspreadsheet.dao.interfaces.ContactDao;
import com.contactspreadsheet.utils.ServletAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/listRegister"})

public class ListRegisterServlet extends HttpServlet implements ServletAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Connection connection = (Connection) req.getAttribute("connection");
		ContactDao dao = new ContactDaoJpaImpl();
		
		req.setAttribute("dao", dao);
	}

	@Override
	public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		return "WEB-INF/javapages/index.jsp";
	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

	
}
