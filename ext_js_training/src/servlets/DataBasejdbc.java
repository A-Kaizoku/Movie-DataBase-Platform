package servlets;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBasejdbc {
	
	private final String URL="jdbc:mysql://localhost:3306/";
	private final String USER="root";
	private final String PASSWORD="root";
	private final String SCHEMA="sakila";
	
	public Connection doDataBaseConnection() {
		
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(URL+SCHEMA,USER,PASSWORD);
			if(conn!=null) {
				System.out.println("Connected to mysql");
			}else {
				System.out.println("Failed to make connection");
			}
		}catch(Exception e){
			System.out.println("Exception occured Db connection : " + e.getMessage()+"\n "+e);
			
		}
		return conn;
	}

}
