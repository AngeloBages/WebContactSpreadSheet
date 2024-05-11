package com.contactspreadsheet.dao.implementations;

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

import com.contactspreadsheet.dao.interfaces.ContactDao;
import com.contactspreadsheet.models.Contact;

public class ContactDaoImpl implements ContactDao{

	private Connection connection;
	
	public ContactDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	public Contact insert(Contact contact) throws SQLException {
		
		String sql = "INSERT INTO contacts (name, email, address, birthDate) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			
			stm.setString(1, contact.getName());
			stm.setString(2, contact.getEmail());
			stm.setString(3, contact.getAddress());
			
			LocalDateTime birthDateTime = contact.getBirthDate().atTime(12, 0);
			stm.setDate(4, new Date(birthDateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()));
			
			stm.execute();
		}
		
		return contact;
	}
	
	public Contact get(Integer id) throws SQLException {
		
		Contact contact = null;
		
		String sql = "SELECT * FROM contacts WHERE id = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setInt(1, id);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				
				contact = new Contact();
				
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("address"));
				contact.setBirthDate(new java.util.Date(rs.getDate("birthDate")
														   .getTime())
																.toInstant()
																.atZone(ZoneId.systemDefault())
																.toLocalDate());
			}
			
		}
		return contact;
	}
	
	public List<Contact> getAll() throws SQLException{
		
		List<Contact> contactList = new ArrayList<>();
		
		String sql = "SELECT * FROM contacts";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				
                Contact contact = new Contact();
				
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("address"));
				contact.setBirthDate(new java.util.Date(rs.getDate("birthDate")
														   .getTime())
																.toInstant()
																.atZone(ZoneId.systemDefault())
																.toLocalDate());
				
				contactList.add(contact);
			}
		}
		
		return contactList;
	}
	
	public Contact update(Contact contact) throws SQLException {
		
		String sql = "UPDATE contacts "
				     + "SET name = ?, email = ?, address = ?, birthDate = ?"
				     + "WHERE id = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setString(1, contact.getName());
			stm.setString(2, contact.getEmail());
			stm.setString(3, contact.getAddress());
			
			LocalDateTime birthDateTime = contact.getBirthDate().atTime(LocalTime.of(12, 0));
			stm.setDate(4, new Date(birthDateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()));
			stm.setInt(5, contact.getId());
			
			stm.execute();
		}
		
		return contact;
	}
	
	public void deleteById(Integer id) throws SQLException {
		
		String sql = "DELETE FROM contacts WHERE id = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setInt(1, id);
			
			stm.execute();
		}
	}


	@Override
	public Contact updateById(Integer id, Contact contact) throws SQLException {

		String sql = "UPDATE contacts "
			     + "SET name = ?, email = ?, address = ?, birthDate = ?"
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

		return contact;
	}

	@Override
	public void delete(Contact contact) throws SQLException {
		
		String sql = "DELETE FROM contacts WHERE id = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setInt(1, contact.getId());
			
			stm.execute();
		}
	}

	@Override
	public List<Contact> getByName(String name) throws SQLException {
		
		List<Contact> list = new ArrayList<>();

		String sql = "SELECT * FROM contacts WHERE name LIKE '%?%'";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setString(1, name);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				Contact contact = new Contact();
				
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("address"));
				contact.setBirthDate(new java.util.Date(rs.getDate("birthDate")
														   .getTime())
																.toInstant()
																.atZone(ZoneId.systemDefault())
																.toLocalDate());
				
				list.add(contact);
			}
		}
		return list;
	}
}
