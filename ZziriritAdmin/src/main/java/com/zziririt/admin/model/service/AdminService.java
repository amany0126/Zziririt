package com.zziririt.admin.model.service;


import java.sql.Connection;
import java.util.ArrayList;

import com.zziririt.admin.model.dao.AdminDao;
import com.zziririt.admin.model.vo.GeneralBulletinBoard;
import com.zziririt.admin.model.vo.GeneralBulletinComment;
import com.zziririt.admin.model.vo.Group;
import com.zziririt.admin.model.vo.GroupBoard;
import com.zziririt.admin.model.vo.GroupComment;
import com.zziririt.admin.model.vo.GroupMember;
import com.zziririt.admin.model.vo.Meeting;
import com.zziririt.admin.model.vo.MeetingMember;
import com.zziririt.admin.model.vo.RegularUser;
import com.zziririt.common.JDBC;
import com.zziririt.common.model.vo.PageInfo;

public class AdminService {

	public int selectListCount() {
		Connection conn = JDBC.getConnection();

		// 2)
		int listCount = new AdminDao().selectListCount(conn);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)

		// System.out.println(listCount);

		return listCount;
	}

	public ArrayList<RegularUser> selectList(PageInfo pi) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		ArrayList<RegularUser> list = new AdminDao().selectList(conn, pi);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)
		return list;
	}

	public RegularUser selectListOne(int userNo) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		RegularUser lo  = new AdminDao().selectListOne(conn, userNo);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)
		return lo;
	}

	public int deleteReularUser(RegularUser user) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		int result = new AdminDao().deleteReularUser(conn, user);

		// 3)
		// select 문이므로 패스

		// System.out.println(result);

		if(result>0) { JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		// 4)
		JDBC.close(conn);

		// 5)
		return result;
	}

	public int searchSelectListCount(String searchUser) {
		Connection conn = JDBC.getConnection();

		// 2)
		int listCount = new AdminDao().searchSelectListCount(conn, searchUser);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)

		// System.out.println(listCount);

		return listCount;

	}

	public ArrayList<RegularUser> searchSelectList(PageInfo pi, String searchUser) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		ArrayList<RegularUser> list = new AdminDao().searchSelectList(conn, pi, searchUser);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)
		return list;
	}

	public ArrayList<String> categoryList() {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		ArrayList<String> categoryName = new AdminDao().categoryList(conn);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)
		return categoryName;
	}

	public int SelectGroupListCount() {
		Connection conn = JDBC.getConnection();

		// 2)
		int listCount = new AdminDao().SelectGroupListCount(conn);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)

		// System.out.println(listCount);

		return listCount;
	}

	public ArrayList<Group> SelectGroupList(PageInfo pi) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		ArrayList<Group> list = new AdminDao().SelectGroupList(conn, pi);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)
		return list;
	}

	public int deleteGroupList(Group gr) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		int result = new AdminDao().deleteGroupList(conn, gr);

		// 3)
		// select 문이므로 패스

		// System.out.println(result);

		if(result>0) { JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		// 4)
		JDBC.close(conn);

		// 5)
		return result;
	}

	public int searchSelectGruopListCount(String category, String search) {
		Connection conn = JDBC.getConnection();

		// 2)
		int listCount = new AdminDao().searchSelectGruopListCount(conn, category, search);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)

		// System.out.println(listCount);

		return listCount;

	}

	public ArrayList<Group> searchSelectGruopList(PageInfo pi, String category, String search) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		ArrayList<Group> list = new AdminDao().searchSelectGruopList(conn, pi, category, search);

		// 3)
		// select 문이므로 패스

		// 4)
		JDBC.close(conn);

		// 5)
		return list;
	}

	public int SelectGroupUserCount() {
		Connection conn = JDBC.getConnection();


		int listCount = new AdminDao().SelectGroupUserCount(conn);


		JDBC.close(conn);


		return listCount;
	}

	public ArrayList<GroupMember> SelectGroupUserList(PageInfo pi) {

		Connection conn = JDBC.getConnection();
		ArrayList<GroupMember> list = new AdminDao().SelectGroupUserList(conn, pi);
		JDBC.close(conn);
		return list;
	}

	public int updateGroupMemberList(GroupMember gm) {

		Connection conn = JDBC.getConnection();
		int result = new AdminDao().updateGroupMemberList(conn, gm);


		if(result>0) { JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		JDBC.close(conn);

		return result;

	}

	public int searchSelectGruopMemberListCount(String category, String search) {
		
		Connection conn = JDBC.getConnection();
		int listCount = new AdminDao().searchSelectGruopMemberListCount(conn, category, search);
		JDBC.close(conn);

		return listCount;
	}

	public ArrayList<GroupMember> searchSelectGruopMemberList(PageInfo pi, String category, String search) {

		Connection conn = JDBC.getConnection();
		ArrayList<GroupMember> list = new AdminDao().searchSelectGruopMemberList(conn, pi, category, search);
		JDBC.close(conn);

		return list;
	}

	public int SelectGroupBoardReportListCount() {


		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().SelectGroupBoardReportListCount(conn);

		JDBC.close(conn);

		return listCount;
	}

	public ArrayList<GroupBoard> SelectGroupBoardReportList(PageInfo pi) {

		Connection conn = JDBC.getConnection();
		ArrayList<GroupBoard> list = new AdminDao().SelectGroupBoardReportList(conn, pi);
		JDBC.close(conn);
		return list;
		
	}

	public int searchSelectGruopBoardListCount(String category, String boardStatus) {

		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().searchSelectGruopBoardListCount(conn, category, boardStatus);

		JDBC.close(conn);

		return listCount;
	}

	public ArrayList<GroupBoard> searchSelectGruopBoardList(PageInfo pi, String category, String boardStatus) {
		
		Connection conn = JDBC.getConnection();
		ArrayList<GroupBoard> list = new AdminDao().searchSelectGruopBoardList(conn, pi, category, boardStatus);
		JDBC.close(conn);

		return list;
	}


	public int deleteGroupBoard(GroupBoard gb, String board_title, String action_result, int bno, String userNo) {

				// 1)
				Connection conn = JDBC.getConnection();
				// 2)
				
				
				int result1 = new AdminDao().deleteGroupUser(conn, gb,userNo);
				
				int result2 = new AdminDao().deleteGroupBoard(conn, gb, bno);
				
				int result3  = new AdminDao().deleteGroupBoardRepost(conn, board_title,action_result,gb);
				
				
				
				// 3)
				// select 문이므로 패스

				// System.out.println(result);

				if(result1*result2*result3>0) { JDBC.commit(conn); 
				}else { 
					JDBC.rollback(conn); }

				// 4)
				JDBC.close(conn);

				// 5)
				return result1*result2*result3;
		
	}

	public int selectGroupCommentReportListCount() {
		
		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().selectGroupCommentReportListCount(conn);

		JDBC.close(conn);

		return listCount;
	}

	public ArrayList<GroupComment> selectGroupCommentReportList(PageInfo pi) {


		Connection conn = JDBC.getConnection();
		ArrayList<GroupComment> list = new AdminDao().selectGroupCommentReportList(conn, pi);
		JDBC.close(conn);
		return list;
	}

	public int searchSelectGruopCommentListCount(String category, String comment_Status) {


		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().searchSelectGruopCommentListCount(conn, category, comment_Status);

		JDBC.close(conn);

		return listCount;
	}

	public ArrayList<GroupComment> searchSelectGruopCommentList(PageInfo pi, String category, String comment_Status) {
		
		Connection conn = JDBC.getConnection();
		ArrayList<GroupComment> list = new AdminDao().searchSelectGruopCommentList(conn, pi, category, comment_Status);
		JDBC.close(conn);

		return list;
	}

	public int deleteGroupBoardComment(GroupComment gc, String action_result) {
		
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		
		
		int result1 = new AdminDao().deleteGroupCommentUser(conn, gc);
		
		int result2 = new AdminDao().deleteGroupCommentBoard(conn, gc );
		
		
		int	result3 = new AdminDao().deleteGroupBoardCommentRepost(conn,action_result,gc);
	
		// 3)
		// select 문이므로 패스

		// System.out.println(result);

		if(result1*result2*result3>0) { JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		// 4)
		JDBC.close(conn);

		// 5)
		return result1*result2*result3;
	}

	public int SelectGeneralBulletinBoardReportListCount() {
		
		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().SelectGeneralBulletinBoardReportListCount(conn);

		JDBC.close(conn);

		return listCount;
	}

	public ArrayList<GeneralBulletinBoard> SelectGeneralBulletinBoardReportList(PageInfo pi) {
		
		Connection conn = JDBC.getConnection();
		ArrayList<GeneralBulletinBoard> list = new AdminDao().SelectGeneralBulletinBoardReportList(conn, pi);
		JDBC.close(conn);
		return list;
	}

	public int searchSelectGeneralBulletinBoardReportListCount(String category, String boardStatus) {

		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().searchSelectGeneralBulletinBoardReportListCount(conn, category, boardStatus);

		JDBC.close(conn);

		return listCount;
		
	}

	public ArrayList<GeneralBulletinBoard> searchSelectGeneralBulletinBoardReportList(PageInfo pi, String category,
			String boardStatus) {

		Connection conn = JDBC.getConnection();
		ArrayList<GeneralBulletinBoard> list = new AdminDao().searchSelectGeneralBulletinBoardReportList(conn, pi, category, boardStatus);
		JDBC.close(conn);

		return list;
		
	}

	public int deleteCommonBoard(GeneralBulletinBoard gbb, String action_result, String userNo) {
		
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		
		
		int result1 = new AdminDao().deleteCommonUser(conn, gbb,userNo);
		
		int result2 = new AdminDao().deleteCommonBoard(conn, gbb);
		
		/* int userNo = new AdminDao().checkUserNo(conn, gbb); */ 
		int result3 = new AdminDao().deleteCommonBoardRepost(conn, gbb, action_result);
		
		// 3)
		// select 문이므로 패스

		// System.out.println(result);

		if(result1*result2*result3>0) { JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		// 4)
		JDBC.close(conn);

		// 5)
		return result1*result2*result3;
	}

	public int selectCommonCommentReportListCount() {

		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().selectCommonCommentReportListCount(conn);

		JDBC.close(conn);

		return listCount;
	}

	public ArrayList<GeneralBulletinComment> selectCommonCommentReportList(PageInfo pi) {

		Connection conn = JDBC.getConnection();
		ArrayList<GeneralBulletinComment> list = new AdminDao().selectCommonCommentReportList(conn, pi);
		JDBC.close(conn);
		return list;
	}

	public int searchSelectCommonCommentListCount(String category, String comment_Status) {

		Connection conn = JDBC.getConnection();

		int listCount = new AdminDao().searchSelectCommonCommentListCount(conn, category, comment_Status);

		JDBC.close(conn);

		return listCount;
		
	}

	public ArrayList<GeneralBulletinComment> searchSelectCommonCommentList(PageInfo pi, String category,
			String comment_Status) {
		
		Connection conn = JDBC.getConnection();
		ArrayList<GeneralBulletinComment> list = new AdminDao().searchSelectCommonCommentList(conn, pi, category, comment_Status);
		JDBC.close(conn);

		return list;
	}

	public int deleteCommonBoardComment(GeneralBulletinComment gbc, String action_result) {
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		
		
		int result1 = new AdminDao().deleteCommonCommentUser(conn, gbc);
		
		int result2 = new AdminDao().deleteCommonBoardComment(conn, gbc);
		
		/* int userNo = new AdminDao().checkUserNo(conn, gbb); */ 
		int result3  = new AdminDao().deleteCommonBoardCommentRepost(conn, gbc, action_result);
		
		// 3)
		// select 문이므로 패스

		// System.out.println(result);

		if(result1*result2*result3>0) { 
			JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		// 4)
		JDBC.close(conn);

		// 5)
		return result1*result2*result3;
	}

	public int reportCount1(String board) {
		Connection conn = JDBC.getConnection();

		int reportCount = new AdminDao().reportCount1(conn, board);

		JDBC.close(conn);
		
		return reportCount;
	}

	public int reportCount2(String reply) {
		Connection conn = JDBC.getConnection();

		int reportCount = new AdminDao().reportCount2(conn, reply);

		JDBC.close(conn);
		
		return reportCount;
	}

	public int reportCount3(String groupBoard) {
		Connection conn = JDBC.getConnection();

		int reportCount = new AdminDao().reportCount3(conn, groupBoard);

		JDBC.close(conn);
		
		return reportCount;
	}

	public int reportCount4(String groupComment) {
		Connection conn = JDBC.getConnection();

		int reportCount = new AdminDao().reportCount4(conn, groupComment);

		JDBC.close(conn);
		
		return reportCount;
	}

	public int category1(String category1) {
		
		Connection conn = JDBC.getConnection();

		int reportCount = new AdminDao().category1(conn, category1);

		JDBC.close(conn);
		
		return reportCount;
	}
	public int category2(String category2) {
		
		Connection conn = JDBC.getConnection();
		
		int reportCount = new AdminDao().category2(conn, category2);
		
		JDBC.close(conn);
		
		return reportCount;
	}
	public int category3(String category3) {
		
		Connection conn = JDBC.getConnection();
		
		int reportCount = new AdminDao().category3(conn, category3);
		
		JDBC.close(conn);
		
		return reportCount;
	}
	public int category4(String category4) {
		
		Connection conn = JDBC.getConnection();
		
		int reportCount = new AdminDao().category4(conn, category4);
		
		JDBC.close(conn);
		
		return reportCount;
	}
	public int category5(String category5) {
		
		Connection conn = JDBC.getConnection();
		
		int reportCount = new AdminDao().category5(conn, category5);
		
		JDBC.close(conn);
		
		return reportCount;
	}

	public int selectMeetingListCount() {
		
		Connection conn = JDBC.getConnection();
		
		int listCount = new AdminDao().selectMeetingListCount(conn);
		
		JDBC.close(conn);
		
		return listCount;
	}


	public ArrayList<Meeting> selectMeetingList(PageInfo pi) {
		
		Connection conn = JDBC.getConnection();
		ArrayList<Meeting> list = new AdminDao().selectMeetingList(conn, pi);
		JDBC.close(conn);

		return list;
	}

	public int searchSelectMeetingListCount(String search) {
Connection conn = JDBC.getConnection();
		
		int listCount = new AdminDao().searchSelectMeetingListCount(conn, search);
		
		JDBC.close(conn);
		
		return listCount;
	}

	public ArrayList<Meeting> searchSelectMeetingList(PageInfo pi, String search) {
		
		Connection conn = JDBC.getConnection();
		ArrayList<Meeting> list = new AdminDao().searchSelectMeetingList(conn, pi, search);
		JDBC.close(conn);

		return list;
	}

	public int deleteMeeting(Meeting m) {
		
		Connection conn = JDBC.getConnection();
		// 2)
		int result = new AdminDao().deleteMeeting(conn, m);

		// 3)
		// select 문이므로 패스

		// System.out.println(result);

		if(result>0) { JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		// 4)
		JDBC.close(conn);

		// 5)
		return result;
	}

	public int selectMeetingMemberListCount() {
		
		Connection conn = JDBC.getConnection();
		
		int listCount = new AdminDao().selectMeetingMemberListCount(conn);
		
		JDBC.close(conn);
		

		return listCount;
	}

	public ArrayList<MeetingMember> selectMeetingMemberList(PageInfo pi) {

		Connection conn = JDBC.getConnection();
		ArrayList<MeetingMember> list = new AdminDao().selectMeetingMemberList(conn, pi);
		JDBC.close(conn);

		return list;
	}

	public int searchSelectMeetingMemberListCount(String search) {

		Connection conn = JDBC.getConnection();
		
		int listCount = new AdminDao().searchSelectMeetingMemberListCount(conn, search);
		
		JDBC.close(conn);
		
		return listCount;
	}

	public ArrayList<MeetingMember> searchSelectMeetingMemberList(PageInfo pi, String search) {

		Connection conn = JDBC.getConnection();
		ArrayList<MeetingMember> list = new AdminDao().searchSelectMeetingMemberList(conn, pi, search);
		JDBC.close(conn);

		return list;
	}

	public int deleteMeetingMember(MeetingMember mm) {
		
		// 1)
		Connection conn = JDBC.getConnection();
		// 2)
		
		
		int result1 = new AdminDao().deleteMeetingMemberUser(conn, mm);
		
		int result2 = new AdminDao().deleteMeetingMemberRank(conn, mm );
		
		// 3)
		// select 문이므로 패스

		// System.out.println(result);

		if(result1*result2>0) { JDBC.commit(conn); 
		}else { 
			JDBC.rollback(conn); }

		// 4)
		JDBC.close(conn);

		// 5)
		return result1*result2;
	}

	public String checkUserId(String userNo) {
		
		Connection conn = JDBC.getConnection();
		
		String UserId = new AdminDao().checkUserId(conn, userNo);
		
		JDBC.close(conn);
		
		return UserId;
	}


}
