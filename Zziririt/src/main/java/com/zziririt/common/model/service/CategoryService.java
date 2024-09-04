package com.zziririt.common.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.zziririt.common.JDBC;
import com.zziririt.common.model.dao.CategoryDao;

public class CategoryService {

	public ArrayList<String> getCategoryList(){
		Connection conn =JDBC.getConnection();
		ArrayList<String> list = new CategoryDao().getCategoryList(conn);
		
		JDBC.close(conn);
		return list;
	};

}
