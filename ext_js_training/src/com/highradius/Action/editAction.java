package com.highradius.Action;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.DAO.MovieDAO;
import com.highradius.Manager.manager;
import com.opensymphony.xwork2.Action;

import servlets.FilmPOJO;

public class editAction implements Action{
	private String title;
	private String description;
	private String releaseYear;
	private int language;
	private String director;
	private String rating;
	private String[] specialFeatures;
	private int filmId;
	private MovieDAO MovieData=new MovieDAO();
	
	public String execute() throws SQLException {
		String[] specialLocal=getSpecialFeatures();
		String features=null;
		for(int i=0;i<specialLocal.length;i++) {
			if(features!=null) {
				features+=",";
			}
			features+=specialLocal[i];
		}
		if(features.contains("null")) {
			features=features.replace("null", "");
		}
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		manager co=(manager) context.getBean("Manager");
		
		//ArrayList<FilmPOJO> movieList = new ArrayList<FilmPOJO>();
		FilmPOJO insertObj=new FilmPOJO();
		insertObj.setTitle(getTitle());
		insertObj.setDescription(getDescription());
		insertObj.setYear(getReleaseYear());
		insertObj.setLanguageId(getLanguage());
		insertObj.setDirector(getDirector());
		insertObj.setDescription(getDescription());
		insertObj.setSpecialFeatures(features);
		insertObj.setRating(getRating());
		insertObj.setFilmId(getFilmId());
		co.editAction(insertObj);
		return "success";		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getLanguage() {
		return language;
	}
	public void setLanguage(int language) {
		this.language = language;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String[] getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String[] specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}	
}
