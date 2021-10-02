/*package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

*//**
 * Servlet implementation class Servlet
 *//*
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	String totalCount="";

	public Servlet(){
		DataBasejdbc dataBaseObj=new DataBasejdbc();
		conn=dataBaseObj.doDataBaseConnection();
		getTotalCount();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			fetchingDataFromDb(request,response);
		}catch(Exception e) {
			System.out.println("Exception Occured in get method: "+e.getMessage()+"\n"+e);
		}
		//response.getWriter().print("Hello from browser");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void fetchingDataFromDb(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String sql="SELECT * FROM film LIMIT ? OFFSET ?";
		String limit=request.getParameter("limit");
		String offset=request.getParameter("start");
		
		PreparedStatement preparedStatement=null;
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(limit));
		preparedStatement.setInt(2, Integer.parseInt(offset));
		
		ResultSet resultSet=preparedStatement.executeQuery();
		ArrayList<FilmPOJO> movieList=new ArrayList<FilmPOJO>();
		Gson gson=new Gson();
		while(resultSet.next()) {
			FilmPOJO filmRow=new FilmPOJO();
			filmRow.setFilmId(resultSet.getInt("film_id"));
			filmRow.setTitle(resultSet.getString("title"));
			filmRow.setDescription(resultSet.getString("description"));
			filmRow.setYear(resultSet.getString("release_year"));
			filmRow.setLanguageId(resultSet.getInt("language_id"));
			filmRow.setOriginalLanguageId(resultSet.getInt("original_language_id"));
			filmRow.setRentalDuration(resultSet.getInt("rental_duration"));
			filmRow.setRentalRate(resultSet.getFloat("rental_rate"));
			filmRow.setLength(resultSet.getInt("length"));
			filmRow.setReplacementCost(resultSet.getFloat("rental_rate"));
			filmRow.setRating(resultSet.getString("rating"));
			filmRow.setSpecialFeatures(resultSet.getString("special_features"));
			filmRow.setLastUpdate(resultSet.getDate("last_update"));
			movieList.add(filmRow);
		}
		String jsonResponse=gson.toJson(movieList);
		jsonResponse="{\"success\":true,\"total\":"+ totalCount +",\"FilmData\":"+jsonResponse+"}";
		PrintWriter out=response.getWriter();
		System.out.println("Data sent to view Successfully");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
		
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
	
	

}
*/