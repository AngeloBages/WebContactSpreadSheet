package com.contactspreadsheet.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.contactspreadsheet.utils.ServletAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/"})
public class ServletController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action") == null? "ListRegisterServlet" : req.getParameter("action");
		ServletAction servletAction = loadServlet(action);
		
		req.getSession().setAttribute("action", action);
		
		
		String page = servletAction.executeGet(req, resp);
		
		if(page != null) {
			req.getRequestDispatcher(page).forward(req, resp);
		}
		
		req.getSession().removeAttribute("errorMessage");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = (String) req.getSession().getAttribute("action");
		ServletAction servletAction = loadServlet(action);
		
		servletAction.executePost(req, resp);
		
		req.getSession().removeAttribute("action");
	}
	
	private ServletAction loadServlet(String action) throws ServletException, IOException {
		String servletName = "com.contactspreadsheet.servlets."+ action;
		Class<?> servlet = null;
		
		try {
			servlet = Class.forName(servletName);
			return (ServletAction) servlet.getConstructor().newInstance();
			
		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
				 IllegalArgumentException | InvocationTargetException | NoSuchMethodException 
				 | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
