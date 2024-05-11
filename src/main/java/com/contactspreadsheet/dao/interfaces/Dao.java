package com.contactspreadsheet.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, K> {

	public T get(K id) throws SQLException;
	
	public List<T> getAll() throws SQLException;
	
	public T insert(T element) throws SQLException;
	
	public T update(T element) throws SQLException;
	
	public T updateById(K id, T element) throws SQLException;
	
	public void delete(T element) throws SQLException;
	
	public void deleteById(K id) throws SQLException;
}
