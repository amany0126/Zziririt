package com.zziririt.letter.model.service;

import static com.zziririt.common.JDBC.commit;
import static com.zziririt.common.JDBC.getConnection;
import static com.zziririt.common.JDBC.rollback;
import static com.zziririt.common.JDBC.close;

import java.sql.Connection;
import java.util.ArrayList;

import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.letter.model.dao.LetterDao;
import com.zziririt.letter.model.vo.Letter;

public class LetterService {
	
	public int letterSending(Letter l) {

		Connection conn = getConnection();
		
		int result = new LetterDao().letterSending(conn, l);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public ArrayList<Letter> selectList(PageInfo pi, int letterStatus, String userId) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2) 
		ArrayList<Letter> list 
			= new LetterDao().selectList(conn, pi, letterStatus, userId);
		
		// 3)
		// select문 이므로 패스
		
		// 4)
		close(conn);
		
		// 5)
		return list;
		
	}

	public ArrayList<Letter> selectReadNotList(PageInfo pi, String userId) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2) 
		ArrayList<Letter> list 
		= new LetterDao().selectReadNotList(conn, pi, userId);
		
		// 3)
		// select문 이므로 패스
		
		// 4)
		close(conn);
		
		// 5)
		return list;
		
	}
	
	public ArrayList<Letter> selectKeywordList(PageInfo pi, int letterStatus, String userId, String keyword) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2) 
		ArrayList<Letter> list 
		= new LetterDao().selectKeywordList(conn, pi, letterStatus, userId, keyword);
		
		// 3)
		// select문 이므로 패스
		
		// 4)
		close(conn);
		
		// 5)
		return list;
		
	}
	
	
	// 쪽지의 총 갯수 구하는 서비스
	public int[] selectListCounts(String userId) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2) 
		int[] listCounts 
			= new LetterDao().selectListCounts(conn, userId);
		
		// 3) 
		// select 문이므로 패스
		
		// 4) 
		close(conn);
		
		// 5) 
		return listCounts;
		
	}

	public int listReadNot(String userId) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2) 
		int listYet
		= new LetterDao().listReadNot(conn, userId);
		
		// 3) 
		// select 문이므로 패스
		
		// 4) 
		close(conn);
		
		// 5) 
		return listYet;
		
	}
	
	public int keywordListCount(int letterStatus, String userId, String keyword) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2) 
		int listCount 
		= new LetterDao().keywordListCount(conn, letterStatus, userId, keyword);
		
		// 3) 
		// select 문이므로 패스
		
		// 4) 
		close(conn);
		
		// 5) 
		return listCount;
		
	}
	
	public int selectFirstLetterNo(int letterStatus, String userSender) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2) 
		int letterNo 
			= new LetterDao().selectFirstLetterNo(conn, letterStatus, userSender);
		
		// 3) 
		// select 문이므로 패스
		
		// 4) 
		close(conn);
		
		// 5) 
		return letterNo;
		
	}
	
	public int checkUserStatus(String userReceiver) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2)
		int cs = new LetterDao().checkUserStatus(conn, userReceiver);
		
		// 3)
		close(conn);
		
		// 4)
		return cs;
		
	}
	
	public int getUpStatusNo(int letterNo, String userId) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2)
		int upStatusNo = new LetterDao().getUpStatusNo(conn, letterNo, userId);
		
		// 3)
		close(conn);
		
		// 4)
		return upStatusNo;
	}
	
	public int letterDelete(int letterNo, int upStatusNo) {
		
		Connection conn = getConnection();
		
		int result = new LetterDao().letterDelete(conn, letterNo, upStatusNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public void letterSaving(Letter l) {

		Connection conn = getConnection();
		
		int result = new LetterDao().letterSaving(conn, l);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
	}
	
	public void letterUpSaving(int letterNo, Letter l) {

		Connection conn = getConnection();
		
		int result = new LetterDao().letterUpSaving(conn, letterNo, l);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
	}
	
	public int letterSaveSending(int letterNo, Letter l) {
		
		Connection conn = getConnection();
		
		int result = new LetterDao().letterSaveSending(conn, letterNo, l);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	} 

	public Letter selectLetter(int letterNo) {
		
		// 1)
		Connection conn = getConnection();
		
		// 2)
		Letter l 
			= new LetterDao().selectLetter(conn, letterNo);
		
		// 3) 
		// > select 문이므로 패스
		
		// 4)
		close(conn);
		
		// 5)
		return l;
	}
	
	public int increaseCount(int letterNo) {
		
		// 1) 
		Connection conn = getConnection();
		
		// 2)
		int result 
			= new LetterDao().increaseCount(conn, letterNo);
		
		// 3) 
		if(result > 0) { // 성공 (commit)
			
			commit(conn);
			
		} else { // 실패 (rollback)
			
			rollback(conn);
		}
		
		// 4)
		close(conn);
		
		// 5)
		return result;
	}
	
	public void setReadTime(int letterNo) {
		
		Connection conn = getConnection();
		
		int result
			= new LetterDao().setReadTime(conn, letterNo);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
			
		}
	}
	
}
