package com.contactspreadsheet.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
	
	private static EntityManagerFactory EMF = null;
	
	public static EntityManager getEntityManager() {
		
		if(EMF == null) {
			EMF = Persistence.createEntityManagerFactory("contact-spreadsheet_hibernate");
		}
		
		return EMF.createEntityManager();
	}
}
