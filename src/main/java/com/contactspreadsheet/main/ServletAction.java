package com.contactspreadsheet.main;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServletAction {

	public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	public String executePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
