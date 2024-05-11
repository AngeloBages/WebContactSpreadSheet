package com.contactspreadsheet.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.contactspreadsheet.models.Contact;

public interface ContactDao extends Dao<Contact, Integer> {

	public List<Contact> getByName(String name) throws SQLException;
}
