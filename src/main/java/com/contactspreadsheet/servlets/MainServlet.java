package com.contactspreadsheet.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.contactspreadsheet.main.ServletAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/main"})
public class MainServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		req.getSession().setAttribute("action", action);
		String servletName = "com.contactspreadsheet.servlets."+ action;
		Class<?> servlet = null;
		
		try {
			servlet = Class.forName(servletName);
		
		
			ServletAction servletAction = (ServletAction) servlet.getConstructor().newInstance();
			
			String page = servletAction.executeGet(req, resp);
			
			if(page != null) {
				req.getRequestDispatcher(page).forward(req, resp);
			}
		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
				 IllegalArgumentException | InvocationTargetException | NoSuchMethodException 
				 | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.getSession().removeAttribute("errorMessage");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = (String) req.getSession().getAttribute("action");
		String servletName = "com.contactspreadsheet.servlets."+ action;
		Class<?> servlet = null;
		
		try {
			servlet = Class.forName(servletName);
		
		
			ServletAction servletAction = (ServletAction) servlet.getConstructor().newInstance();
			
			String page = servletAction.executePost(req, resp);
			
			if(!(page == null)) {
				req.getRequestDispatcher(page).forward(req, resp);
			}
		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
				 IllegalArgumentException | InvocationTargetException | NoSuchMethodException 
				 | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().removeAttribute("action");
	}

	
	
}
