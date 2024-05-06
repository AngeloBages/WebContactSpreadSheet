package com.contactspreadsheet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


import com.contactspreadsheet.models.Contact;

public class ContactDaoImpl implements Dao<Contact>{

	private Connection connection;
	
	public ContactDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	public void insert(Contact contact) throws SQLException {
		
		String sql = "INSERT INTO contatos (nome, email, endereco, dataNascimento) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			
			stm.setString(1, contact.getName());
			stm.setString(2, contact.getEmail());
			stm.setString(3, contact.getAddress());
			
			LocalDateTime birthDateTime = contact.getBirthDate().atTime(12, 0);
			stm.setDate(4, new Date(birthDateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()));
			
			stm.execute();
			
		}
	}
	
	public Contact get(int id) throws SQLException {
		
		Contact contact = null;
		
		String sql = "SELECT * FROM contatos WHERE id = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setInt(1, id);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				
				contact = new Contact();
				
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("nome"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("endereco"));
				contact.setBirthDate(new java.util.Date(rs.getDate("dataNascimento")
														   .getTime())
																.toInstant()
																.atZone(ZoneId.systemDefault())
																.toLocalDate());
			}
			
		}
		return contact;
	}
	
	public List<Contact> getList() throws SQLException{
		
		List<Contact> contactList = new ArrayList<>();
		
		String sql = "SELECT * FROM contatos";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				
                Contact contact = new Contact();
				
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("nome"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("endereco"));
				contact.setBirthDate(new java.util.Date(rs.getDate("dataNascimento")
														   .getTime())
																.toInstant()
																.atZone(ZoneId.systemDefault())
																.toLocalDate());
				
				contactList.add(contact);
			}
		}
		
		return contactList;
	}
	
	public void update(int id, Contact contact) throws SQLException {
		
		String sql = "UPDATE contatos "
				     + "SET nome = ?, email = ?, endereco = ?, dataNascimento = ?"
				     + "WHERE id = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setString(1, contact.getName());
			stm.setString(2, contact.getEmail());
			stm.setString(3, contact.getAddress());
			
			LocalDateTime birthDateTime = contact.getBirthDate().atTime(LocalTime.of(12, 0));
			stm.setDate(4, new Date(birthDateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()));
			stm.setInt(5, id);
			
			stm.execute();
		}
	}
	
	public void delete(int id) throws SQLException {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setInt(1, id);
			
			stm.execute();
		}
	}
}
