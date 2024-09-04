package com.zziririt.group.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.zziririt.common.JDBC;
import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.group.model.dao.GroupDao;
import com.zziririt.group.model.vo.Group;
import com.zziririt.group.model.vo.GroupBoard;
import com.zziririt.group.model.vo.GroupComment;
import com.zziririt.group.model.vo.Meeting;
import com.zziririt.member.model.vo.Member;

public class GroupService {
	// 용환 구간
	public int searchGroupPage(String category, String area, String keyword, String userId) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().searchGroupPage(conn, category, area, keyword, userId);
		JDBC.close(conn);
		return result;
	}

	public ArrayList<Group> searchGroupList(String category, String area, String keyword, String userId, PageInfo pi) {
		Connection conn = JDBC.getConnection();
		ArrayList list = new GroupDao().searchGroupList(conn, category, area, keyword, userId, pi);
		return list;
	}

	public int checkWish(String gno, String userNo) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().checkWish(conn, gno, userNo);
		JDBC.close(conn);
		return result;
	}

	public int removeWish(String gno, String userNo) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().removeWish(conn, gno, userNo);
		if (result > 0)
			JDBC.commit(conn);
		else
			JDBC.rollback(conn);
		JDBC.close(conn);
		return result;
	}

	public int addWish(String gno, String userNo) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().addWish(conn, gno, userNo);
		if (result > 0)
			JDBC.commit(conn);
		else
			JDBC.rollback(conn);
		JDBC.close(conn);
		return result;
	}

	public int groupNameCheck(String name) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().groupNameCheck(conn, name);
		JDBC.close(conn);
		return result;
	}

	public int createGroup(Group g, int userNo) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().createGroup(conn, g, userNo);
		JDBC.close(conn);
		if (result > 0)
			JDBC.commit(conn);
		else
			JDBC.rollback(conn);
		JDBC.close(conn);
		return result;
	}

	public HashMap<String, ArrayList<Meeting>> getMeetingList(String gno, String year, String month) {
		Connection conn = JDBC.getConnection();
		HashMap<String, ArrayList<Meeting>> result = new GroupDao().getMeetingList(conn, gno, year, month);
		JDBC.close(conn);
		return result;
	}

	// 그룹정보 조회
	public Group getGroupViewNormal(String gno) {
		Connection conn = JDBC.getConnection();
		Group g = new GroupDao().getGroupViewNormal(conn, gno);
		JDBC.close(conn);
		return g;
	}

	// 유저 타입 조회
	public int getUserType(int userNo, int groupNo) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().getUserType(conn, userNo, groupNo);
		JDBC.close(conn);
		return result;
	}

	// 그룹 신청
	public int joinGroup(String descriptSelf, Member m, String gno) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().joinGroup(conn, descriptSelf, m, gno);
		if (result > 0) {
			JDBC.commit(conn);
		} else {
			JDBC.rollback(conn);
		}
		JDBC.close(conn);
		return result;
	}

	// 그룹 이미지 변경
	public int changeGroupImg(String groupProfile, String gno) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().changeGroupImg(conn, groupProfile, gno);
		if (result > 0) {
			JDBC.commit(conn);
		} else {
			JDBC.rollback(conn);
		}
		JDBC.close(conn);
		return result;

	}

	// 그룹 이름 변경
	public int changeGroupName(String name, String gno) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().changeGroupName(conn, name, gno);
		if (result > 0) {
			JDBC.commit(conn);
		} else {
			JDBC.rollback(conn);
		}
		JDBC.close(conn);
		return result;
	}

	// 그룹 설명 변경
	public int changeDescript(String descript, String gno) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().changeDescript(conn, descript, gno);
		if (result > 0) {
			JDBC.commit(conn);
		} else {
			JDBC.rollback(conn);
		}
		JDBC.close(conn);
		return result;
	}

	// 정모 추가
	public int createMeeting(Meeting m) {
		Connection conn = JDBC.getConnection();
		int result = new GroupDao().createMeeting(conn, m);
		if (result > 0) {
			JDBC.commit(conn);
		} else {
			JDBC.rollback(conn);
		}
		JDBC.close(conn);
		return result;
	}

	// 날짜로 모임 조회
	public ArrayList<Meeting> getMeetingListByDay(String gno, String year, String month, String day) {
		Connection conn = JDBC.getConnection();
		ArrayList<Meeting> list = new GroupDao().getMeetingListByDay(conn, gno, year, month, day);
		JDBC.close(conn);
		return list;
	}

	// id로 모임 조회
	public Meeting getMeetingById(String id,String userNo) {
		Connection conn = JDBC.getConnection();
		Meeting m = new GroupDao().getMeetingById(conn, id, userNo);
		JDBC.close(conn);
		return m;
	}
	// id로 문자가져오기
	
	
	// 가입그룹 조회
	public ArrayList<Group> getJoinGroupList(int userNo){
		Connection conn = JDBC.getConnection();
		ArrayList<Group> result = new GroupDao().getJoinGroupList(conn, userNo);
		JDBC.close(conn);
		return result;
	}
	// 찜 그룹 조회
	public ArrayList<Group> getWishGroupList(int userNo) {
		Connection conn = JDBC.getConnection();
		ArrayList<Group> result = new GroupDao().getWishGroupList(conn, userNo);
		JDBC.close(conn);
		return result;
	}
	// 최근 개설 그룹
	public ArrayList<Group> getNewGroupList(){
		Connection conn = JDBC.getConnection();
		ArrayList<Group> result = new GroupDao().getNewGroupList(conn);
		JDBC.close(conn);
		return result;
	}
	// 마감 얼마 안남은 그룹
	public ArrayList<Group> getCloseGroupList(){
		Connection conn = JDBC.getConnection();
		ArrayList<Group> result = new GroupDao().getCloseGroupList(conn);
		JDBC.close(conn);
		return result;
	}
	
	
	// 은서구간
	public int insertGroup(Group g) {

		Connection conn = JDBC.getConnection();

		int result = new GroupDao().insertGroup(conn, g);

		JDBC.close(conn);

		return result;
	}



	//게시글 한건 조회
	public GroupBoard selectGroup(int groupNo) {

		Connection conn = JDBC.getConnection();

		GroupBoard gb 
		= new GroupDao().selectGroup(conn, groupNo);

		JDBC.close(conn);

		return gb;
	}



	// 게시글의 총 갯수 구하는 서비스
	public int selectGroupBoardListCount() {

		Connection conn = JDBC.getConnection();

		int listCount = new GroupDao().selectGroupBoardListCount(conn);

		JDBC.close(conn);

		return listCount;
	}


	public ArrayList<GroupBoard> selectGroupBoardList(PageInfo pi) {
		// 1)
		Connection conn = JDBC.getConnection();

		// 2) 
		ArrayList<GroupBoard> list 
		= new GroupDao().selectGroupBoardList(conn, pi);

		// 3)
		// select문 이므로 패스

		// 4)
		JDBC.close(conn);


		// 5)
		return list;
	}




	public int updateGroup(GroupBoard gb) {
		// 1)
		Connection conn = JDBC.getConnection();


		// 			System.out.println("서비스"+gb.getBoardTitle());
		//			System.out.println("서비스"+gb.getBoardContent());
		//			System.out.println("서비스"+gb.getBoardNo());


		// 2_1) BOARD 테이블 UPDATE 요청 후 결과 받기
		int result = new  GroupDao().updateGroup(conn, gb);



		//System.out.println("서비스"+result);
		// 3)
		// 이 시점 기준으로 트랜잭션 처리
		// > 위의 결과가 모두 성공일 경우에만 커밋
		if(result > 0) {

			JDBC.commit(conn);

		} else {

			JDBC.rollback(conn);
		}

		// 4)
		JDBC.close(conn);

		// 5)
		return result;

	}




	public int insertGroupBoard(GroupBoard gb) {

		Connection conn = JDBC.getConnection();

		int result = new GroupDao().insertGroupBoard(conn, gb); 

		if(result>0) {
			JDBC.commit(conn);
		}else {
			JDBC.rollback(conn);
		}		
		
		JDBC.close(conn);

		return result;
	}




	public String selectUserNum(String userId) {

		Connection conn = JDBC.getConnection();
		
		
		
		String userNum= new GroupDao().selectUserNum(conn, userId);
		
		JDBC.close(conn);

		return userNum;
	}

	public int selectUserNo1(String userNo) {
		Connection conn = JDBC.getConnection();

		String userNum= new GroupDao().selectUserNum(conn, userNo);
		int userNoo = Integer.parseInt(userNum);  
		JDBC.close(conn);

		return userNoo;
	}


	public int selectUserNo(String userId) {
		Connection conn = JDBC.getConnection();

		int userNo = new GroupDao().selectUserNo(conn, userId);
		String userNum = new GroupDao().selectUserNum1(conn, userNo);
		
		int userNoo = Integer.parseInt(userNum);  
		JDBC.close(conn);

		return userNoo;
	}




	public int deleteGroupBoard(int groupNo, int userNo) {

		Connection conn = JDBC.getConnection();

		int result = new GroupDao().deleteGroupBoard(conn, groupNo, userNo);

		JDBC.close(conn);

		return result;
	}



	//게시글 삭제
	public int deleteGroup(int boardNo) {

		Connection conn = JDBC.getConnection();

		int result = new GroupDao().deleteGroup(conn, boardNo);

		if (result > 0) { // 성공 (commit)

			JDBC.commit(conn);

		} else { // 실패 (rollback)

			JDBC.rollback(conn);
		}

		JDBC.close(conn);

		return result;
	}


	public int declareGroupBoard(int groupNo) {

		Connection conn = JDBC.getConnection();

		int result = new GroupDao().declareGroupBoard(conn, groupNo);
		
		if (result > 0) { // 성공 (commit)

			JDBC.commit(conn);

		} else { // 실패 (rollback)

			JDBC.rollback(conn);
		}
		
		JDBC.close(conn);

		return result;
	}





	public ArrayList<Group> searchUserGroup(String userId) {

		Connection conn = JDBC.getConnection();

		ArrayList<Group> list = new GroupDao().searchUserGroup(conn,userId);

		JDBC.close(conn);

		return list;
	}




	public String selectTime(int groupNo) {
		
		Connection conn = JDBC.getConnection();

		String writeTime = new GroupDao().selectTime(conn,groupNo);

		JDBC.close(conn);

		return writeTime;
	}



	// 댓글리스트 조회용 서비스
		public ArrayList<GroupComment> 
					selectCommentList(int groupNo) {
			
			// 1)
			Connection conn = JDBC.getConnection();
			
			// 2)
			ArrayList<GroupComment> list
				= new GroupDao().selectCommentList(conn, groupNo);
			
			// 3)
			// > select 문이므로 패스
			
			// 4)
			JDBC.close(conn);

			// 5)
			return list;
		}
		
		// 댓글작성용 서비스
		public int insertComment(GroupComment gc) {
			
			// 1)
			Connection conn = JDBC.getConnection();
			
			// 2) 
			int result 
				= new GroupDao().insertComment(conn, gc);
			
			// 3)
			if(result > 0) { // 성공 (commit)
				
				JDBC.commit(conn);

				
			} else { // 실패 (rollback)
				
				JDBC.rollback(conn);

			}
			
			// 4) 
			JDBC.close(conn);
			
			// 5)
			return result;
		}
		
		
	//댓글 삭제
	public int deleteComment(int commentNo) {

		Connection conn = JDBC.getConnection();

		int result = new GroupDao().deleteComment(conn, commentNo);

		if (result > 0) { // 성공 (commit)

			JDBC.commit(conn);

		} else { // 실패 (rollback)

			JDBC.rollback(conn);
		}

		JDBC.close(conn);

		return result;
	}



	//댓글 수정
	public int updateComment(int commentNo, String updatedContent) {
		// 1)
		Connection conn = JDBC.getConnection();

	     System.out.println(commentNo);
		

		// 2_1) BOARD 테이블 UPDATE 요청 후 결과 받기
		int result 
		= new  GroupDao().updateComment(conn, commentNo,updatedContent);

		// 3)
		// 이 시점 기준으로 트랜잭션 처리
		// > 위의 결과가 모두 성공일 경우에만 커밋
		if(result > 0) {

			JDBC.commit(conn);

		} else {

			JDBC.rollback(conn);
		}

		// 4)
		JDBC.close(conn);

		// 5)
		return result;			
	}
	
	
//댓글 신고
public int declareComment(int commentNo) {

	Connection conn = JDBC.getConnection();

	int result = new GroupDao().declareComment(conn, commentNo);

	if (result > 0) { // 성공 (commit)

		JDBC.commit(conn);

	} else { // 실패 (rollback)

		JDBC.rollback(conn);
	}

	JDBC.close(conn);

	return result;
}


}


