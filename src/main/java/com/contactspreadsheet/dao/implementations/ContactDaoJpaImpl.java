package com.contactspreadsheet.dao.implementations;

import java.util.List;

import com.contactspreadsheet.dao.interfaces.ContactDao;
import com.contactspreadsheet.models.Contact;
import com.contactspreadsheet.utils.JpaUtils;

import jakarta.persistence.EntityManager;

public class ContactDaoJpaImpl implements ContactDao{

	@Override
	public Contact get(Integer id) {

		EntityManager em = null;
		Contact contact = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			contact = em.find(Contact.class, id);
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
		return contact;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAll() {

		EntityManager em = null;
	    List<Contact> list = null;
	    
	    try {
	    	em = JpaUtils.getEntityManager();
	    	
	    	list = em.createQuery("from Contact").getResultList();
	    	
	    }finally {
	    	if(em != null) {
	    		em.close();
	    	}
	    }

		return list;
	}

	@Override
	public Contact insert(Contact contact) {

		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			em.getTransaction().begin();
			em.persist(contact);
			em.getTransaction().commit();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}

		return contact;
	}

	@Override
	public Contact update(Contact contact) {
		
		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			em.getTransaction().begin();
			em.merge(contact);
			em.getTransaction().commit();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
		return contact;
	}

	@Override
	public Contact updateById(Integer id, Contact contact) {

		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			contact.setId(id);
			
			em.getTransaction().begin();                      
			em.merge(contact);
			em.getTransaction().commit();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
		return contact;
	}

	@Override
	public void delete(Contact contact) {

		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			em.getTransaction().begin();
			em.remove(contact);
			em.getTransaction().commit();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
	}

	@Override
	public void deleteById(Integer id) {
		
		EntityManager em = null;
		Contact contact = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			contact = em.find(Contact.class, id);
			
			em.getTransaction().begin();                         
			em.remove(contact);
			em.getTransaction().commit();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Contact> getByName(String name) {

		EntityManager em = null;
		List<Contact> list = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			list = em.createQuery("from Contact c where c.name like concat('%', :name, '%')", Contact.class)
			.setParameter("name", name).getResultList();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
		return list;
	}

}
