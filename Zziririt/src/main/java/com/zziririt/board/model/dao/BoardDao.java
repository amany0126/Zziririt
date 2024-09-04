package com.zziririt.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.zziririt.board.model.vo.Board;
import com.zziririt.board.model.vo.Category;
import com.zziririt.board.model.vo.Like;
import com.zziririt.board.model.vo.Reply;
import com.zziririt.common.JDBC;
import com.zziririt.common.model.vo.PageInfo;

public class BoardDao { // BoardDao 클래스 영역 시작
	
	// Properties 객체 생성
	private Properties prop = new Properties();
	
	// 쿼리문들이 담긴 xml 파일 불러오는 기본생성자
	public BoardDao() {
		String fileName 
			= BoardDao.class
				.getResource("/sql/board/board-mapper.xml")
				.getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 총 게시글 수 구하는 DAO
	public int selectListCount(Connection conn) { // selectListCount 메서드 영역 시작
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > int
		
		// 필요한 변수들 먼저 세팅
		int listCount = 0; // 총 게시글의 개수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectListCount");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			// > 패스
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 listCount 변수에 옮겨담기
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
		
		// 6) 서비스로 결과 반환
		return listCount;
	} // selectListCount 메서드 영역 끝
	
	// 전체 게시글 목록 조회용 DAO
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) { // selectList 메서드 영역 시작
		
		// SELECT문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 세팅
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			int startRow = (pi.getCurrentPage() - 1) 
							* pi.getBoardLimit() + 1;
			
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while(rset.next()) {
				Board b = new Board(rset.getInt("BOARD_NO"), 
									rset.getString("BOARD_TITLE"), 
									rset.getString("CREATE_TIME"), 
									rset.getString("USER_NICKNAME"), 
									rset.getString("CATEGORY_NAME"), 
									rset.getInt("MEETING_STATUS"), 
									rset.getInt("COUNT"), 
									rset.getInt("REPLY_COUNT"), 
									rset.getInt("LIKE_COUNT"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6)
		return list;
	} // selectList 메서드 영역 끝
	
	// 카테고리 목록 조회용 DAO
	public ArrayList<Category> selectCategoryList(Connection conn) { // selectCategoryList 메서드 영역 시작
		
		// SELECT문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Category> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			// > 완성된 쿼리문이니까 패스
			
			// 3) 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4) 한 줄씩 읽어들이기
			while(rset.next()) {
				list.add(new Category(rset.getString("CATEGORY_NAME"),
									  rset.getInt("STATUS")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) 리스트 반환
		return list;
	} // selectCategoryList 메서드 영역 끝

	// 게시글 작성용 DAO
	public int insertBoard(Board b, Connection conn) { // insertBoard 메서드 영역 시작
		
		// INSERT문 > int
		
		// 필요한 변수 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("insertBoard");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setString(1, b.getBoardWriter());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getCategoryName());
			pstmt.setString(4, b.getMeetingTime());
			pstmt.setInt(5, b.getPeopleLimit());
			pstmt.setString(6, b.getMeetingSpot());
			pstmt.setString(7, b.getBoardContent());
			pstmt.setString(8, b.getFileInfo());
			pstmt.setString(9, b.getFileOriginName());
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 6) result 결과값 반환
		return result;
	} // insertBoard 메서드 영역 끝

	// 일반게시글 조회수 증가용 DAO
	public int increaseCount(Connection conn, int boardNo) { // increaseCount 메서드 영역 시작
		
		// UPDATE문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL 문
		String sql = prop.getProperty("increaseCount");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, boardNo);
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) result 결과값 반환
		return result;
	} // increaseCount 메서드 영역 끝

	// 게시글 한건 조회용 DAO
	public Board selectBoard(Connection conn, int boardNo) { // selectBoard 메서드 영역 시작
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > Board 객체
		
		// 필요한 변수들 먼저 셋팅
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL 문
		String sql = prop.getProperty("selectBoard");
		
		try {
			
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, boardNo);
			
			// 3) 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 객체 b 에 옮겨담기
			if(rset.next()) {
				b = new Board(rset.getInt("BOARD_NO"), 
							  rset.getString("BOARD_TITLE"), 
							  rset.getString("BOARD_CONTENT"), 
							  rset.getString("CREATE_TIME"), 
							  rset.getString("MEETING_TIME"), 
							  rset.getString("MEETING_SPOT"),  
							  rset.getInt("PEOPLE_LIMIT"), 
							  rset.getString("BOARD_WRITER"), 
							  rset.getString("CATEGORY_NAME"), 
							  rset.getInt("MEETING_STATUS"), 
							  rset.getInt("COUNT"), 
							  rset.getInt("REPLY_COUNT"), 
							  rset.getInt("LIKE_COUNT"), 
							  rset.getString("FILE_INFO"), 
							  rset.getString("FILE_ORIGINNAME"), 
							  rset.getString("WRITER_PROFILE")
							  );
				
				// System.out.println(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) b 객체 반환
		return b;
	} // selectBoard 메서드 영역 끝

	// 일반게시글 삭제용 DAO
	public int deleteBoard(Connection conn, int boardNo) { // deleteBoard 메서드 영역 시작
		
		// UPDATE 문 > 처리된 행의 개수 (int)
		
		// 필요한 변수들 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("deleteBoard");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, boardNo);
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // deleteBoard 메서드 영역 끝

	// 일반게시글 수정용 DAO
	public int updateBoard(Connection conn, Board b) { // updateBoard 메서드 영역 시작
		
		// UPDATE문 > 처리된 행의 개수 (int)
		
		// 필요한 변수들 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("updateBoard");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 넘기기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, b.getMeetingStatus());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getCategoryName());
			pstmt.setString(4, b.getMeetingTime());
			pstmt.setInt(5, b.getPeopleLimit());
			pstmt.setString(6, b.getMeetingSpot());
			pstmt.setString(7, b.getBoardContent());
			pstmt.setString(8, b.getFileInfo());
			pstmt.setString(9, b.getFileOriginName());
			pstmt.setInt(10, b.getBoardNo());
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // updateBoard 메서드 영역 끝
	
	// 댓글 개수 세는 DAO
	public int selectReplyCount(Connection conn, int boardNo) { // selectReplyCount 메서드 영역 시작

		// SELECT문 > ResultSet 객체 (단일행)
		// > int
		
		// 필요한 변수들 먼저 세팅
		int replyCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("selectReplyCount");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, boardNo);
			
			// 3) 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4) 결과 읽어들이기
			if(rset.next()) {
				replyCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) 결과값 반환
		return replyCount;
	} // selectReplyCount 메서드 영역 끝
	
	// 댓글목록 조회용 DAO
	public ArrayList<Reply> selectReplyList(Connection conn, int boardNo) {
		
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList<Reply>
		
		// 필요한 변수들 먼저 세팅
		ArrayList<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("selectReplyList");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, boardNo);
			
			// 3) 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4) 한 줄씩 읽어들이기
			while(rset.next()) {
				Reply r = new Reply();
				
				r.setReplyNo(rset.getInt("REPLY_NO"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setReplyWriter(rset.getString("REPLY_WRITER"));
				r.setcreateTime(rset.getString("CREATE_TIME"));
				r.setReplyWriterProfile(rset.getString("REPLY_WRITER_PROFILE"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) 리스트 반환
		return list;
	}
	
	// 댓글 작성용 DAO
	public int insertReply(Connection conn, Reply r) { // insertReply 메서드 영역 시작
		
		// INSERT문 > 처리된 행의 개수 (int)
		
		// 필요한 변수 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("insertReply");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBoardNo());
			pstmt.setInt(3, Integer.parseInt(r.getReplyWriter()));
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // insertReply 메서드 영역 끝

	// 댓글 삭제용 DAO
	public int deleteReply(Connection conn, int replyNo) { // deleteReply 메서드 영역 시작

		// UPDATE문 > 처리된 행의 개수 (int)
		
		// 필요한 변수 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("deleteReply");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, replyNo);
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // deleteReply 메서드 영역 끝
	
	// 좋아요 개수 세는 DAO
	public int selectLikeCount(Connection conn, int boardNo) { // selectLikeCount 메서드 영역 시작
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > int
		
		// 필요한 변수들 먼저 세팅
		int likeCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("selectLikeCount");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, boardNo);
			
			// 3) 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4) 결과 읽어들이기
			if(rset.next()) {
				likeCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) 결과값 반환
		return likeCount;
	} // selectLikeCount 메서드 영역 끝

	// 좋아요 확인용 DAO
	public int likeCheck(Connection conn, Like l) { // likeCheck 메서드 영역 시작
		
		// SELECT문 > ResultSet 객체 (단일행)
		// > int
		
		// 필요한 변수들 먼저 세팅
		int checkResult = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("likeCheck");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, l.getLikeUserNo());
			pstmt.setInt(2, l.getLikeBoardNo());
			
			// 3) 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4) 결과 읽어들이기
			if(rset.next()) {
				checkResult = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) 결과값 반환
		return checkResult;
	} // likeCheck 메서드 영역 끝

	// 좋아요 클릭 DAO
	public int insertLike(Connection conn, Like l) { // insertLike 메서드 영역 시작
		
		// INSERT문 > 처리된 행의 개수 (int)
		
		// 필요한 변수들 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("insertLike");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, l.getLikeUserNo());
			pstmt.setInt(2, l.getLikeBoardNo());
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // insertLike 메서드 영역 끝

	// 좋아요 해제 DAO
	public int deleteLike(Connection conn, Like l) { // deleteLike 메서드 영역 시작
		
		// INSERT 문 > 처리된 행의 개수 (int)
		
		// 필요한 변수들 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("deleteLike");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, l.getLikeUserNo());
			pstmt.setInt(2, l.getLikeBoardNo());
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // deleteLike 메서드 영역 끝

	// 게시글 신고 요청용 DAO
	public int reportBoard(Connection conn, int boardNo) { // reportBoard 메서드 영역 시작
		
		// UPDATE 문 > 처리된 행의 개수 (int)
		
		// 필요한 변수들 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("reportBoard");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, boardNo);
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // reportBoard 메서드 영역 끝

	// 댓글 신고 요청용 DAO
	public int reportReply(Connection conn, int replyNo) { // reportReply 메서드 영역 시작

		// UPDATE 문 > 처리된 행의 개수 (int)
		
		// 필요한 변수들 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("reportReply");
		
		try {
			// 1) PreparedStatement 객체 생성 후 쿼리문 담기
			pstmt = conn.prepareStatement(sql);
			
			// 2) 쿼리문 빈칸 채우기
			pstmt.setInt(1, replyNo);
			
			// 3) 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납
			JDBC.close(pstmt);
		}
		
		// 5) 결과값 반환
		return result;
	} // reportReply 메서드 영역 끝

	// 게시글 목록 정렬 조회용 DAO (카테고리별)
	public ArrayList<Board> selectSort(Connection conn, PageInfo pi, String category) { // selectCategorySort 메서드 영역 시작

		// SELECT문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 세팅
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = null;
		if(category != null) {
			sql = prop.getProperty("selectSort") + 
				" AND CATEGORY_NAME = ? ORDER BY BOARD_NO DESC) A ) WHERE RNUM BETWEEN ? AND ?";
		} else {
			sql = prop.getProperty("selectSort") + 
				"ORDER BY BOARD_NO DESC ) WHERE RNUM BETWEEN ? AND ?";
		}
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			int startRow = (pi.getCurrentPage() - 1) 
							* pi.getBoardLimit() + 1;
			
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while(rset.next()) {
				Board b = new Board(rset.getInt("BOARD_NO"), 
									rset.getString("BOARD_TITLE"), 
									rset.getString("CREATE_TIME"), 
									rset.getString("USER_NICKNAME"), 
									rset.getString("CATEGORY_NAME"), 
									rset.getInt("MEETING_STATUS"), 
									rset.getInt("COUNT"), 
									rset.getInt("REPLY_COUNT"), 
									rset.getInt("LIKE_COUNT"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6)
		return list;
	} // selectCategorySort 메서드 영역 끝

	// 게시글 목록 정렬 조회용 DAO (모집여부별)
	public ArrayList<Board> selectSort(Connection conn, PageInfo pi, int exceptClosed) { // selectCategorySort 메서드 영역 시작

		// SELECT문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 세팅
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		if(exceptClosed == 1) {
			sql = prop.getProperty("selectSort") + 
				" AND MEETING_STATUS = ? ORDER BY BOARD_NO DESC) A ) WHERE RNUM BETWEEN ? AND ?";
		} else {
			sql = prop.getProperty("selectSort") + 
				"ORDER BY BOARD_NO DESC ) WHERE RNUM BETWEEN ? AND ?";
		}
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			int startRow = (pi.getCurrentPage() - 1) 
							* pi.getBoardLimit() + 1;
			
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, exceptClosed);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while(rset.next()) {
				Board b = new Board(rset.getInt("BOARD_NO"), 
									rset.getString("BOARD_TITLE"), 
									rset.getString("CREATE_TIME"), 
									rset.getString("USER_NICKNAME"), 
									rset.getString("CATEGORY_NAME"), 
									rset.getInt("MEETING_STATUS"), 
									rset.getInt("COUNT"), 
									rset.getInt("REPLY_COUNT"), 
									rset.getInt("LIKE_COUNT"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6)
		return list;
	} // selectCategorySort 메서드 영역 끝
} // BoardDao 클래스 영역 끝