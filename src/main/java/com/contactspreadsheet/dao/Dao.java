package com.contactspreadsheet.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	public void insert(T e) throws SQLException;
	
	public T get(int id) throws SQLException;
	
    public List<T> getList() throws SQLException;
    
    public void update(int id, T e) throws SQLException;
    
    public void delete(int id) throws SQLException;
}
