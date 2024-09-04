package com.zziririt.letter.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.zziririt.common.JDBC;
import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.letter.model.vo.Letter;

public class LetterDao {

private Properties prop = new Properties();
	
	public LetterDao() {
		
		String fileName 
			= LetterDao.class
				.getResource("/sql/letter/letter-mapper.xml")
									.getPath();
		
		try {
			
			prop.loadFromXML(new FileInputStream(fileName));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int letterSending(Connection conn,
			   Letter l) {

		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("letterSending");
		
		try {
		
		// 1)
		pstmt = conn.prepareStatement(sql);
		
		// 2) 
		pstmt.setString(1, l.getLetterTitle());
		pstmt.setString(2, l.getLetterContent());
		pstmt.setString(3, l.getUserSender());
		pstmt.setString(4, l.getUserReceiver());
		
		// 3)
		result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
		
		// 4)
		JDBC.close(pstmt);
		}
		
		// 5)
		return result;
	}
	
	public ArrayList<Letter> selectList(Connection conn,
			   PageInfo pi, int letterStatus, String userId) {
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Letter> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = null;
		
		if(letterStatus==4) {
			sql = prop.getProperty("selectSaveList");
		} else if(letterStatus==1) {
			sql = prop.getProperty("selectReceiveList");
		} else if(letterStatus==2) {
			sql = prop.getProperty("selectSendList");
		}
		try {
		
		// 1)
		pstmt = conn.prepareStatement(sql);
		
		// 2)
		int startRow = (pi.getCurrentPage() - 1) 
			* pi.getBoardLimit() + 1;
		
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		pstmt.setString(1, userId);
		pstmt.setInt(2, startRow);
		pstmt.setInt(3, endRow);
		
		// 3) 쿼리문 실행 후 결과 받기
		rset = pstmt.executeQuery();
		
		// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
		while(rset.next()) {
		
		Letter l = new Letter(rset.getInt("LETTER_NO"),
					rset.getString("LETTER_TITLE"),
					rset.getString("LETTER_CONTENT"),
					rset.getString("SEND_TIME"),
					rset.getString("RECEIVE_TIME"),
					rset.getInt("STATUS"),
					rset.getString("USER_SENDER"),
					rset.getString("USER_RECEIVER"),
					rset.getInt("COUNT"),
					rset.getString("SAVE_RECEIVER"));
					
					list.add(l);
				}
		
				} catch (SQLException e) {
						e.printStackTrace();
				} finally {
			
		// 5)
						JDBC.close(rset);
						JDBC.close(pstmt);
				}
		
		for(Letter l : list) {
			if(l.getReceiveTime()==null) {
				l.setReceiveTime("미열람");
			}
		}
		// 6)
		return list;
	}

	public ArrayList<Letter> selectReadNotList(Connection conn,
			PageInfo pi, String userId) {
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Letter> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = null;
		
		sql = prop.getProperty("selectReadNotList");
		
		try {
			
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			int startRow = (pi.getCurrentPage() - 1) 
					* pi.getBoardLimit() + 1;
			
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while(rset.next()) {
				
				Letter l = new Letter(rset.getInt("LETTER_NO"),
						rset.getString("LETTER_TITLE"),
						rset.getString("LETTER_CONTENT"),
						rset.getString("SEND_TIME"),
						rset.getString("RECEIVE_TIME"),
						rset.getInt("STATUS"),
						rset.getString("USER_SENDER"),
						rset.getString("USER_RECEIVER"),
						rset.getInt("COUNT"),
						rset.getString("SAVE_RECEIVER"));
				
				list.add(l);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		for(Letter l : list) {
			if(l.getReceiveTime()==null) {
				l.setReceiveTime("미열람");
			}
		}
		// 6)
		return list;
	}

	public ArrayList<Letter> selectKeywordList(Connection conn,
			PageInfo pi, int letterStatus, String userId, String keyword) {
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Letter> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = null;
		
		if(letterStatus==4) {
			sql = prop.getProperty("selectKeywordSaveList");
		} else if(letterStatus==1) {
			sql = prop.getProperty("selectKeywordReceiveList");
		} else if(letterStatus==2) {
			sql = prop.getProperty("selectKeywordSendList");
		}
		try {
			
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			int startRow = (pi.getCurrentPage() - 1) 
					* pi.getBoardLimit() + 1;
			
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, userId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while(rset.next()) {
				
				Letter l = new Letter(rset.getInt("LETTER_NO"),
						rset.getString("LETTER_TITLE"),
						rset.getString("LETTER_CONTENT"),
						rset.getString("SEND_TIME"),
						rset.getString("RECEIVE_TIME"),
						rset.getInt("STATUS"),
						rset.getString("USER_SENDER"),
						rset.getString("USER_RECEIVER"),
						rset.getInt("COUNT"),
						rset.getString("SAVE_RECEIVER"));
				
				list.add(l);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		for(Letter l : list) {
			if(l.getReceiveTime()==null) {
				l.setReceiveTime("미열람");
			}
		}
		
		// 6)
		return list;
	}
	
	
	
	// 총 게시글 갯수 구하는 메소드
	public int[] selectListCounts(Connection conn, String userId) {
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > int 변수에 옮겨담기
		
		// 필요한 변수들 먼저 셋팅
		int[] listCounts = new int[3]; // 총 게시글의 갯수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Integer> list = new ArrayList<>();
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectListCounts");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
	
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			pstmt.setString(3, userId);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를
			//    listCount 변수에 옮겨담기
			while(rset.next()) {
				String none = rset.getString("SOURCE");
				list.add(rset.getInt("COUNT"));
				
				}
	
				} catch (SQLException e) {
				e.printStackTrace();
				} finally {
			
			// 5) 자원반납 (역순)
				JDBC.close(rset);
				JDBC.close(pstmt);
			}
			listCounts[0] = list.get(0);
			listCounts[1] = list.get(1);
			listCounts[2] = list.get(2);
			// 6) Service 로 결과 리턴
			return listCounts;
	}
	public int listReadNot(Connection conn, String userId) {
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > int 변수에 옮겨담기
		
		// 필요한 변수들 먼저 셋팅
		int listReadNot = 0; // 총 게시글의 갯수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("listReadNot");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			
			pstmt.setString(1, userId);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를
			//    listCount 변수에 옮겨담기
			if(rset.next()) {
				listReadNot = rset.getInt("COUNT");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5) 자원반납 (역순)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) Service 로 결과 리턴
		return listReadNot;
	}
	
	public int keywordListCount(Connection conn, int letterStatus, String userId, String keyword) {
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > int 변수에 옮겨담기
		
		// 필요한 변수들 먼저 셋팅
		int listCount = 0; // 총 게시글의 갯수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql;
		
		if(letterStatus==4) {
			sql = prop.getProperty("keywordSaveListCount");
		} else if(letterStatus==1) {
			sql = prop.getProperty("keywordReceiveListCount");
		} else {
			sql = prop.getProperty("keywordSendListCount");
		}
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			
			pstmt.setString(1, userId);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를
			//    listCount 변수에 옮겨담기
			if(rset.next()) {
				
				listCount = rset.getInt("COUNT"); // 별칭
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5) 자원반납 (역순)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) Service 로 결과 리턴
		return listCount;
	}

	public int selectFirstLetterNo(Connection conn, int letterStatus, String userSender) {
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > int 변수에 옮겨담기
		
		// 필요한 변수들 먼저 셋팅
		int letterNo = 0; // 총 게시글의 갯수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql;
		
		sql = prop.getProperty("selectFirstLetterNo");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			
			pstmt.setInt(1, letterStatus);
			pstmt.setString(2, userSender);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			
			
			if(rset.next()) {	
			letterNo = rset.getInt("LETTER_NO"); // 별칭
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5) 자원반납 (역순)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) Service 로 결과 리턴
		return letterNo;
	}
	
	public int checkUserStatus(Connection conn, String userReceiver) {
		
		int cs = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("checkUserStatus");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			pstmt.setString(1, userReceiver);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			cs = rset.getInt("STATUS");
			}			
			} catch(SQLException e) {
			e.printStackTrace();
			} finally {
		
		// 5) 자원반납 (역순)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return cs;
	}
	
	public int getUpStatusNo(Connection conn, int letterNo, String userId) {
		
		int upStatusNo = 0;
		int status = 0;
		String userSender="";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("checkStatusNo");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			pstmt.setInt(1, letterNo);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			status = rset.getInt("STATUS");
			userSender = rset.getString("USER_SENDER");
			}
			System.out.println(userSender);
			if(userId.equals(userSender)) {
				if(status==0) {
					upStatusNo = 1;
				} else { upStatusNo = 3;}
			} else{if(status==0) {
				upStatusNo = 2;
			} else{
				upStatusNo = 3;
				}
			}
			} catch(SQLException e) {
			e.printStackTrace();
			} finally {
		
		// 5) 자원반납 (역순)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		return upStatusNo;
	
	}
	
	public int letterDelete(Connection conn, int letterNo, int upStatusNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("letterDelete");
		
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			pstmt.setInt(1, upStatusNo);
			
			pstmt.setInt(2, letterNo);
			
			// 3) 쿼리문 실행 후 결과 받기
			result = pstmt.executeUpdate();
			
			} catch(SQLException e) {
			e.printStackTrace();
			} finally {
		
		// 5) 자원반납 (역순)
			JDBC.close(pstmt);
		}
		
		return result;
		
	} 
	
	public int letterSaving(Connection conn,
			   Letter l) {

		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("letterSaving");
		
		try {
		
		// 1)
		pstmt = conn.prepareStatement(sql);
		
		// 2) 
		pstmt.setString(1, l.getLetterTitle());
		pstmt.setString(2, l.getLetterContent());
		pstmt.setString(3, l.getUserSender());
		pstmt.setString(4, l.getUserReceiver());
		pstmt.setString(5, l.getSaveReceiver());
		
		// 3)
		result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
		
		// 4)
		JDBC.close(pstmt);
		}
		
		// 5)
		return result;
	}
	
	public int letterUpSaving(Connection conn, int letterNo,
			Letter l) {
		
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("letterUpSaving");
		
		try {
			
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2) 
			pstmt.setString(1, l.getLetterTitle());
			pstmt.setString(2, l.getLetterContent());
			pstmt.setString(3, l.getUserReceiver());
			pstmt.setString(4, l.getSaveReceiver());
			pstmt.setInt(5, letterNo);
			
			// 3)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4)
			JDBC.close(pstmt);
		}
		
		// 5)
		return result;
	}
	
	public int letterSaveSending(Connection conn, int letterNo,
			Letter l) {
		
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("letterSaveSending");
		
		try {
			
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2) 
			pstmt.setString(1, l.getLetterTitle());
			pstmt.setString(2, l.getLetterContent());
			pstmt.setString(3, l.getUserReceiver());
			pstmt.setInt(4, letterNo);
			
			// 3)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4)
			JDBC.close(pstmt);
		}
		
		// 5)
		return result;
	}
	public Letter selectLetter(Connection conn,
			 int letterNo) {

		// SELECT 문 > ResultSet 객체 (단일행)
		// > Board 객체
		
		// 필요한 변수들 먼저 셋팅
		Letter l = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL 문
		String sql = prop.getProperty("selectLetter");
		
		try {
		
		// 1)
		pstmt = conn.prepareStatement(sql);
		
		// 2)
		pstmt.setInt(1, letterNo);
		
		// 3)
		rset = pstmt.executeQuery();
		
		// 4) 
		if(rset.next()) {
		
			l = new Letter(rset.getInt("LETTER_NO"),
					rset.getString("LETTER_TITLE"),
					rset.getString("LETTER_CONTENT"),
					rset.getString("SEND_TIME"),
					rset.getString("RECEIVE_TIME"),
					rset.getInt("STATUS"),
					rset.getString("USER_SENDER"),
					rset.getString("USER_RECEIVER"),
					rset.getInt("COUNT"),
					rset.getString("SAVE_RECEIVER"));}
		
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
		
		// 5)
		JDBC.close(rset);
		JDBC.close(pstmt);
		}
		
		// 6)
		return l;
		
		}
	
	public int increaseCount(Connection conn,
			 int letterNo) {

		// UPDATE 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL 문
		String sql = prop.getProperty("increaseCount");
		
		try {
		
		// 1)
		pstmt = conn.prepareStatement(sql);
		
		// 2)
		pstmt.setInt(1, letterNo);
		
		// 3)
		result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
		
		// 4)
		JDBC.close(pstmt);
		}
		
		// 5)
		return result;
	}
	
	public int setReadTime(Connection conn, int letterNo) {
		
		// INSERT 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("setReadTime");
		
		try {
			
			// 1) 
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			pstmt.setInt(1, letterNo);
			
			// 3)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4) 
			JDBC.close(pstmt);
		}
		
		// 5)
		return result;
	}

}
