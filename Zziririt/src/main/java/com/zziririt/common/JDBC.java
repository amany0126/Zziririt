package com.zziririt.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC {


	
	// 1. Connection 객체 생성(db접속) 후 해당 Connection을 반환하는 메소드
	
		public static Connection getConnection() {
			// driver.properties 파일로부터
			// 접속 정보들을 key + value 세트들로 읽어들여서 접속
			
			Properties prop = new Properties(); // Map 계열 컬렉션
			
			// 읽어들이고자 하는 driver.properties 파일의 물리적인 경로
			String fileName = JDBC.class.getResource("/sql/driver/driver.properties").getPath();//web-inf/classes/sql/driver/driver.properties
			// > 배포될때를 생각해서 파일의 경로를 잡아줘야함!!
			//   classes 폴더로부터 시작하는 경로를 잡아준 것
			//   (/가 의미하는 것이 classes 폴더임)
			
			// fileName 변수에는 이게 담겨있음
			// C:\05_Web-workspace2\JSP_Project\src\main\webapp\WEB-INF\classes\sql\driver\driver.properties
			
			try {
				prop.load(new FileInputStream(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Connection conn =null;
			
			
			// 1) JDBC Driver 등록
			try {
				Class.forName(prop.getProperty("driver"));
				// 2) db와 접속된 Connection 객체 생성
				// > DB 접속 정보를 넘기면서 객체를 생성하겠다.
				conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
				
				
				// 3) 자동커밋 해제
				conn.setAutoCommit(false);
				
				// > 그동안 안했던 이유 :
				//   어차피 한개의 트랜잭션에 한개의 DML 문만 처리하는 구조였기 때문
				// > 앞으로는 한개의 트랜잭션에 여러개의 DML 문을 처리하는 일이
				//   자주 생길 것이기 때문에 반드시 처리해야함!!
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;	
		}
		
		// 2. 전달받은 Connection 객체를 가지고 commit 해주는 메소드
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 3. 전달받은 Connection 객체를 가지고 rollback 해주는 메소드
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 4. 전달받은 Connection 객체를 반납시켜주는 메소드
		public static void close(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 5. 전달받은 Statement 객체를 반납시켜주는 메소드
		// > 다형성을 이용해서 자식타입인 PreparedStatement도 처리 가능
		// > 오버 로딩 또한 활용
		public static void close(Statement stmt) {
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 6. 전달받은 ResultSet 객체를 반납시켜주는 메소드
		public static void close(ResultSet rset) {
			try {
				if(rset != null && !rset.isClosed()) {
					rset.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
