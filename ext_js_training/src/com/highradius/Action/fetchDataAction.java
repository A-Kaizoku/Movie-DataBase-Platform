package com.highradius.Action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.DAO.MovieDAO;
import com.highradius.Manager.manager;

public class fetchDataAction {
	//private int page;
	private int limit;
	private int start;
	private Map<String,Object> Obj=new HashMap<>();
	
	
	public String execute() throws SQLException, IOException {
		//MovieDAO MovieData=new MovieDAO();
		System.out.println("check 1");
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		manager co=(manager) context.getBean("Manager");
		Obj=co.fetchAction(getLimit(),getStart());
		return "success";
	}
	
	public Map<String, Object> getObj() {
		return Obj;
	}

	public void setObj(Map<String, Object> Obj) {
		this.Obj = Obj;
	}

/*	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
*/
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	
}
