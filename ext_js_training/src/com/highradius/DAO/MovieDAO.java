package com.highradius.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import com.google.gson.Gson;

import servlets.DataBasejdbc;
import servlets.FilmPOJO;

public class MovieDAO{
	
	Connection conn=null;
	String totalCount="";
	String jsonResponse=null;
	Configuration con=new Configuration();
    @SuppressWarnings("deprecation")
	SessionFactory sf=con.configure().buildSessionFactory();
	
	public MovieDAO(){
		DataBasejdbc dataBaseObj=new DataBasejdbc();
		conn=dataBaseObj.doDataBaseConnection();
		getTotalCount();
	}
	
	public Map<String,Object> fetchData(int limit, int offset) throws SQLException, IOException{
		/*String sql="SELECT * FROM film LIMIT ? OFFSET ?";	
		PreparedStatement preparedStatement=null;
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setInt(1, limit);
		preparedStatement.setInt(2, offset);*/
		Map<String,Object> temp=new HashMap<>();
		
			//ResultSet resultSet=preparedStatement.executeQuery();
		//ArrayList<FilmPOJO> movieList = new ArrayList<FilmPOJO>();
		//Gson gson=new Gson();
		/*while(resultSet.next()) {
			FilmPOJO filmRow=new FilmPOJO();
			filmRow.setFilmId(resultSet.getInt("film_id"));
			filmRow.setTitle(resultSet.getString("title"));
			filmRow.setDescription(resultSet.getString("description"));
			filmRow.setDirector(resultSet.getString("director"));
			filmRow.setYear(resultSet.getString("release_year"));
			filmRow.setLanguageId(resultSet.getInt("language_id"));
			filmRow.setOriginalLanguageId(resultSet.getInt("original_language_id"));
			filmRow.setRentalDuration(resultSet.getInt("rental_duration"));
			filmRow.setRentalRate(resultSet.getFloat("rental_rate"));
			filmRow.setLength(resultSet.getInt("length"));
			filmRow.setReplacementCost(resultSet.getFloat("rental_rate"));
			filmRow.setRating(resultSet.getString("rating"));
			filmRow.setSpecialFeatures(resultSet.getString("special_features"));
			filmRow.setLastUpdate(resultSet.getString("last_update"));
			movieList.add(filmRow);
		}
		temp.put("FilmData", movieList);
		temp.put("total", totalCount);
		*/
		Session session=sf.openSession();
	    Transaction transaction=null;
	    try{
	    	transaction=session.beginTransaction();
	    	Criteria criteria=session.createCriteria(FilmPOJO.class);
	    	criteria.setFirstResult(offset);
	    	criteria.setMaxResults(limit);
	    	ArrayList<FilmPOJO> movieList = (ArrayList<FilmPOJO>) criteria.list();
			criteria.setFirstResult(0);
			int count=Integer.parseInt(String.valueOf((long)criteria.setProjection(Projections.rowCount()).uniqueResult()));
	    	transaction.commit();
	    	temp.put("FilmData", movieList);
			temp.put("total", count);
			temp.put("success", true);
	    	
		}catch(Exception e) {
			if(transaction!=null)
				transaction.rollback();
			System.out.println("SQL Exception Occured : " + e.getMessage()+"\n" + e);
			temp.put("failure", true);
		}
		return temp;
	}
	
	private void getTotalCount(){
		String total="SELECT COUNT(*) FROM film";
		try {
			PreparedStatement preparedStatement=null;
			preparedStatement=conn.prepareStatement(total);
			ResultSet res=preparedStatement.executeQuery();
			while(res.next()) {
			totalCount=res.getString("COUNT(*)");
		}
	
		}catch(Exception e) {
			System.out.println("Total Count Exception Occured : "+e.getMessage()+"\n"+e);
		}
	}


	public void addData(FilmPOJO insertObj) throws SQLException {
			//String sql2="INSERT INTO `film` (`title`, `description`, `release_year`, `language_id`, `rating`, `special_features`, `last_update`, `director`) VALUES(?,?,?,?,?,?,NULL,?);";
			
	      Session session=sf.openSession();
	      Transaction transaction=null;
	      
		try {
			/*PreparedStatement preparedStatement=null;
			preparedStatement=conn.prepareStatement(sql2);
			preparedStatement.setString(1, insertObj.getTitle());
			preparedStatement.setString(2, insertObj.getDescription());
			preparedStatement.setString(3, insertObj.getYear());
			preparedStatement.setInt(4, insertObj.getLanguageId());
			preparedStatement.setString(5, insertObj.getRating());
			preparedStatement.setString(6, insertObj.getSpecialFeatures());
			preparedStatement.setString(7, insertObj.getDirector());
			
			preparedStatement.executeUpdate();*/
	        transaction=session.beginTransaction();
	        session.save(insertObj);
	        transaction.commit();
	        //responseData.put("success":true);
			
			}catch(Exception e) {
				if(transaction!=null)
					transaction.rollback();
				System.out.println("SQL Exception Occured : "+e.getMessage()+"\n"+e);
			}
		session.close();
		}
	
	public void editData(FilmPOJO insertObj) throws SQLException {
		//String sql="UPDATE `film` SET `title`=?,`description`=?,`release_year`=?,`language_id`=?,`rating`=?,`special_features`=?,`director`=? WHERE film_id=?";
		 Session session=sf.openSession();
	      Transaction transaction=null;
		try {
		/*PreparedStatement preparedStatement=null;
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1, insertObj.getTitle());
		preparedStatement.setString(2, insertObj.getDescription());
		preparedStatement.setString(3, insertObj.getYear());
		preparedStatement.setInt(4, insertObj.getLanguageId());
		preparedStatement.setString(5, insertObj.getRating());
		preparedStatement.setString(6, insertObj.getSpecialFeatures());
		preparedStatement.setString(7, insertObj.getDirector());
		preparedStatement.setInt(8, insertObj.getFilmId());
		
		preparedStatement.executeUpdate();*/
			
		transaction=session.beginTransaction();
		session.update(insertObj);
		transaction.commit();
		        //responseData.put("success":true);
				
		}catch(Exception e) {
			if(transaction!=null)
				transaction.rollback();
			System.out.println("SQL Exception Occured : "+e.getMessage()+"\n"+e);
		}
		session.close();
	}
	
	public void deleteData(String [] array) throws SQLException {
		try {
			String[] words=null;
			PreparedStatement preparedStatement=null;
			for(int i=0;i<array.length;i++) {
				words=array[i].split(",");
		}
		
		for(String w:words) {
			String sql="DELETE FROM `film` WHERE `film_id`=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, w);
			preparedStatement.executeUpdate();
			System.out.println("Query executed");
		}
		
		}catch(Exception e) {
			System.out.println("SQL Exception Occured : "+e.getMessage()+"\n"+e);
		}
	}
}




