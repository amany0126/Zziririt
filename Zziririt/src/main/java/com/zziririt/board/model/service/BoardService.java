package com.zziririt.board.model.service;

import static com.zziririt.common.JDBC.close;
import static com.zziririt.common.JDBC.commit;
import static com.zziririt.common.JDBC.getConnection;
import static com.zziririt.common.JDBC.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.zziririt.board.model.vo.Board;
import com.zziririt.board.model.vo.Category;
import com.zziririt.board.model.vo.Like;
import com.zziririt.board.model.vo.Reply;
import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.board.model.dao.BoardDao;

public class BoardService { // BoardService 클래스 영역 시작
	
	// 총 게시글 수 구하는 서비스
	public int selectListCount() {
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체를 매개변수로 넘기면서 DAO 호출
		int listCount 
			= new BoardDao().selectListCount(conn);
		
		// 3) 트랜잭션 처리
		// > SELECT문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 리스트 반환
		return listCount;
	}
	
	// 전체 게시글 목록 조회용 서비스
	public ArrayList<Board> selectList(PageInfo pi) { // selectList 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 pi를 매개변수로 넘기면서 DAO 호출
		ArrayList<Board> list
			= new BoardDao().selectList(conn, pi);
		
		// 3) 트랜잭션 처리
		// > SELECT문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 리스트 반환
		return list;
	} // selectList 메서드 영역 끝
	
	// 카테고리 전체 조회용 서비스
	public ArrayList<Category> selectCategoryList() { // selectCategoryList 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체를 매개변수로 넘기면서 DAO 호출
		ArrayList<Category> list
			= new BoardDao().selectCategoryList(conn);
		
		// 3) 트랜잭션 처리
		// > SELECT문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 리스트 반환
		return list;
	} // selectCategoryList 메서드 영역 끝

	// 게시글 작성용 서비스
	public int insertBoard(Board b) { // insertBoard 메서드 영역 시작 
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 b를 매개변수로 넘기면서 DAO 호출
		int result 
			= new BoardDao().insertBoard(b, conn);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 리스트 반환
		return result;
	} // insertBoard 메서드 영역 끝

	// 일반게시글 조회수 증가용 서비스
	public int increaseCount(int boardNo) { // increaseCount 메서드 영역 시작

		// 1)  Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 boardNo를 매개변수로 넘기면서 DAO 호출
		int result 
			= new BoardDao().increaseCount(conn, boardNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) result 반환
		return result;
	} // increaseCount 메서드 영역 끝

	// 일반게시글 한건 조회용 서비스
	public Board selectBoard(int boardNo) { // selectBoard 메서드 영역 시작

		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 boardNo를 매개변수로 넘기면서 DAO 호출
		Board b 
			= new BoardDao().selectBoard(conn, boardNo);
		
		// 3) 트랜잭션 처리
		// > SELECT문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) b 객체 반환
		return b;
	} // selectBoard 메서드 영역 끝
	
	// 일반게시글 삭제용 서비스
	public int deleteBoard(int boardNo) { // deleteBoard 메서드 영역 시작
		// 1)  Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 boardNo를 매개변수로 넘기면서 DAO 호출
		int result 
			= new BoardDao().deleteBoard(conn, boardNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) result 반환
		return result;
	} // deleteBoard 메서드 영역 끝
	
	// 일반게시글 수정용 서비스
	public int updateBoard(Board b) { // updateBoard 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2_1) BOARD 테이블 UPDATE 요청 후 결과 받기
		int result 
			= new BoardDao().updateBoard(conn, b);
		
		// 3) 트랜잭션 처리
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5)
		return result;
	} // updateBoard 메서드 영역 끝
	
	// 댓글 개수 세는 서비스
	public int selectReplyCount(int boardNo) { // selectReplyCount 메서드 영역 시작

		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 boardNo를 매개변수로 넘기면서 DAO 호출
		int replyCount
			= new BoardDao().selectReplyCount(conn, boardNo);
		
		// 3) 트랜잭션 처리
		// > select 문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return replyCount;
	} // selectReplyCount 메서드 영역 끝
	
	// 댓글목록 조회용 서비스
	public ArrayList<Reply> selectReplyList(int boardNo) { // selectReplyList 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 boardNo를 매개변수로 넘기면서 DAO 호출
		ArrayList<Reply> list 
			= new BoardDao().selectReplyList(conn, boardNo);
		
		// 3) 트랜잭션 처리
		// > SELECT문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 리스트 반환
		return list;
	} // selectReplyList 메서드 영역 끝

	// 댓글 작성용 서비스
	public int insertReply(Reply r) { // insertReply 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 r를 매개변수로 넘기면서 DAO 호출
		int result 
			= new BoardDao().insertReply(conn, r);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);	
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return result;
	} // insertReply 메서드 영역 끝
	
	// 댓글 삭제용 서비스
	public int deleteReply(int replyNo) { // deleteReply 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 replyNo를 매개변수로 넘기면서 DAO 호출
		int result 
			= new BoardDao().deleteReply(conn, replyNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);	
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return result;
	} // deleteReply 메서드 영역 끝
	
	// 좋아요 개수 세는 서비스
	public int selectLikeCount(int boardNo) { // selectLikeCount 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 boardNo를 매개변수로 넘기면서 DAO 호출
		int likeCount
			= new BoardDao().selectLikeCount(conn, boardNo);
		
		// 3) 트랜잭션 처리
		// > select 문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return likeCount;
	} // selectLikeCount 메서드 영역 끝

	// 좋아요 확인용 서비스
	public int likeCheck(Like l) { // likeCheck 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 l를 매개변수로 넘기면서 DAO 호출
		int checkResult
			= new BoardDao().likeCheck(conn, l);
		
		// 3) 트랜잭션 처리
		// > select 문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return checkResult;
	} // likeCheck 메서드 영역 끝

	// 좋아요 클릭 서비스
	public int insertLike(Like l) { // insertLike 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 l를 매개변수로 넘기면서 DAO 호출
		int result
			= new BoardDao().insertLike(conn, l);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return result;
	} // insertLike 메서드 영역 끝

	// 좋아요 해제 서비스
	public int deleteLike(Like l) { // deleteLike 메서드 영역 시작
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 l를 매개변수로 넘기면서 DAO 호출
		int result
			= new BoardDao().deleteLike(conn, l);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return result;
	} // deleteLike 메서드 영역 끝

	// 게시글 신고 요청용 서비스
	public int reportBoard(int boardNo) { // reportBoard 메서드 영역 시작

		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 boardNo를 매개변수로 넘기면서 DAO 호출
		int result
			= new BoardDao().reportBoard(conn, boardNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return result;
	} // reportBoard 메서드 영역 끝

	// 댓글 신고 요청용 서비스
	public int reportReply(int replyNo) { // reportReply 메서드 영역 시작

		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 replyNo를 매개변수로 넘기면서 DAO 호출
		int result
			= new BoardDao().reportReply(conn, replyNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 결과값 반환
		return result;
	} // reportReply 메서드 영역 끝

	// 게시글 목록 정렬 조회용 서비스 (카테고리별)
	public ArrayList<Board> selectSort(PageInfo pi, String category) { // selectCategorySort 메서드 영역 시작

		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 pi, category 를 매개변수로 넘기면서 DAO 호출
		ArrayList<Board> list
			= new BoardDao().selectSort(conn, pi, category);
		
		// 3) 트랜잭션 처리
		// > SELECT문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 리스트 반환
		return list;
	} // selectCategorySort 메서드 영역 끝

	// 게시글 목록 정렬 조회용 서비스 (모집여부별)
	public ArrayList<Board> selectSort(PageInfo pi, int exceptClosed) { // selectCategorySort 메서드 영역 시작

		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 생성한 Connection 객체와 pi, exceptClosed 를 매개변수로 넘기면서 DAO 호출
		ArrayList<Board> list
			= new BoardDao().selectSort(conn, pi, exceptClosed);
		
		// 3) 트랜잭션 처리
		// > SELECT문이므로 패스
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) 리스트 반환
		return list;
	} // selectCategorySort 메서드 영역 끝
} // BoardService 클래스 영역 끝