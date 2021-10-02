package com.highradius.Action;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.DAO.MovieDAO;
import com.highradius.Manager.manager;

import servlets.FilmPOJO;

public class deleteAction {
	
	private String[] filmId;
	private MovieDAO MovieData=new MovieDAO();

	public String execute() throws SQLException {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		manager co=(manager) context.getBean("Manager");
		co.deleteAction(getFilmId());
		return "success";
	}
	
	public MovieDAO getMovieData() {
		return MovieData;
	}

	public void setMovieData(MovieDAO movieData) {
		MovieData = movieData;
	}

	public String[] getFilmId() {
		return filmId;
	}

	public void setFilmId(String[] filmId) {
		this.filmId = filmId;
	}
}
