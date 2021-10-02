package com.highradius.Manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.DAO.MovieDAO;

import servlets.FilmPOJO;

public class manager {
	
	MovieDAO dao;
	FilmPOJO pojo;
	
	public FilmPOJO getPojo() {
		return pojo;
	}

	public void setPojo(FilmPOJO pojo) {
		this.pojo = pojo;
	}

	public MovieDAO getDao() {
		return dao;
	}

	public void setDao(MovieDAO dao) {
		this.dao = dao;
	}

	public Map<String, Object> fetchAction(int limit,int offset) throws SQLException, IOException {
		return dao.fetchData(limit,offset);	
	}

	public void addAction(FilmPOJO insertObj) throws SQLException {
		dao.addData(insertObj);
	}
	
	public void editAction(FilmPOJO insertObj) throws SQLException {
		dao.editData(insertObj);
	}
	
	public void deleteAction(String [] array) throws SQLException {
		dao.deleteData(array);
	}
	
}
