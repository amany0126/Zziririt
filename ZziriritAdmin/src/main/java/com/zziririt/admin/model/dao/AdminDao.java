package com.zziririt.admin.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

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


public class AdminDao {
	private Properties prop = new Properties();
	
	public AdminDao() {

		String fileName = AdminDao.class
				.getResource("/sql/admin/admin-mapper.xml")
				.getPath();
	
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectListCount(Connection conn) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<RegularUser> selectList(Connection conn, PageInfo pi) {

		ArrayList<RegularUser> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				RegularUser ru  = new RegularUser(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_NICKNAME"),
						rset.getString("USER_NAME"),
						rset.getDate("JOIN_DATE"),
						rset.getInt("STATUS"));
				list.add(ru);
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
	}

	public RegularUser selectListOne(Connection conn, int userNo) {
		
		RegularUser lo = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListOne");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

		

			pstmt.setInt(1, userNo);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			if (rset.next()){
				
				lo = new RegularUser (
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_NICKNAME"),
						rset.getString("USER_NAME"),
						rset.getDate("JOIN_DATE"),
						rset.getInt("STATUS"));				
			}
		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}

		// 6)
		return lo;
	}

	public int deleteReularUser(Connection conn, RegularUser user) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteReularUser");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, user.getStatus());
			pstmt.setInt(2, user.getUserNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int searchSelectListCount(Connection conn, String searchUser) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+searchUser+"%");
			pstmt.setString(2, "%"+searchUser+"%");
			pstmt.setString(3, "%"+searchUser+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<RegularUser> searchSelectList(Connection conn, PageInfo pi, String searchUser) {
		ArrayList<RegularUser> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, "%"+searchUser+"%");
			pstmt.setString(2, "%"+searchUser+"%");
			pstmt.setString(3, "%"+searchUser+"%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				RegularUser ru  = new RegularUser(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_NICKNAME"),
						rset.getString("USER_NAME"),
						rset.getDate("JOIN_DATE"),
						rset.getInt("STATUS"));
				list.add(ru);
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
	}

	public ArrayList<String> categoryList(Connection conn) {

		ArrayList<String> categoryName = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("categoryList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			while(rset.next()) {
				categoryName.add(rset.getString("CATEGORY_NAME"));
			}
			
			
			
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return categoryName;

	}

	public int SelectGroupListCount(Connection conn) {
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("SelectGroupListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Group> SelectGroupList(Connection conn, PageInfo pi) {
		
		ArrayList<Group> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectGroupList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				Group ru  = new Group(
						rset.getInt("GROUP_NO"),
						rset.getString("GROUP_NAME"),
						rset.getInt("GROUP_LIMIT"),
						rset.getDate("GROUP_CREATEDAY"),
						rset.getInt("STATUS"),
						rset.getString("CATEGORY_NAME"));
				list.add(ru);
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
	}

	public int deleteGroupList(Connection conn, Group gr) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGroupList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gr.getStatus());
			pstmt.setInt(2, gr.getGroupNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int searchSelectGruopListCount(Connection conn, String category, String search) {
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectGruopListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+search+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Group> searchSelectGruopList(Connection conn, PageInfo pi, String category, String search) {
		ArrayList<Group> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectGruopList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				Group ru  = new Group(
						rset.getInt("GROUP_NO"),
						rset.getString("GROUP_NAME"),
						rset.getInt("GROUP_LIMIT"),
						rset.getDate("GROUP_CREATEDAY"),
						rset.getInt("STATUS"),
						rset.getString("CATEGORY_NAME"));
				list.add(ru);
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
	}

	public int SelectGroupUserCount(Connection conn) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("SelectGroupUserCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GroupMember> SelectGroupUserList(Connection conn, PageInfo pi) {
		
		ArrayList<GroupMember> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectGroupUserList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GroupMember gm  = new GroupMember(
						rset.getInt("GROUP_MEM_NO"),
						rset.getInt("GROUP_MEM_TYPE"),
						rset.getDate("GROUP_JOIN_DATE"),
						rset.getInt("STATUS"),
						rset.getString("USER_ID"),
						rset.getString("GROUP_NAME"),
						rset.getString("CATEGORY_NAME"));
				list.add(gm);
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
	}

	public int updateGroupMemberList(Connection conn, GroupMember gm) {

		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateGroupMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gm.getGroupMenType());
			pstmt.setInt(2, gm.getStatus());
			pstmt.setInt(3, gm.getGroupMenNo());

			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int searchSelectGruopMemberListCount(Connection conn, String category, String search) {


		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectGruopMemberListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+search+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GroupMember> searchSelectGruopMemberList(Connection conn, PageInfo pi, String category,
			String search) {

		
		ArrayList<GroupMember> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectGruopMemberList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GroupMember gm  = new GroupMember(
						rset.getInt("GROUP_MEM_NO"),
						rset.getInt("GROUP_MEM_TYPE"),
						rset.getDate("GROUP_JOIN_DATE"),
						rset.getInt("STATUS"),
						rset.getString("USER_ID"),
						rset.getString("GROUP_NAME"),
						rset.getString("CATEGORY_NAME"));
				list.add(gm);
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
	}

	public int SelectGroupBoardReportListCount(Connection conn) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("SelectGroupBoardReportListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GroupBoard> SelectGroupBoardReportList(Connection conn, PageInfo pi) {
		
		ArrayList<GroupBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectGroupBoardReportList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GroupBoard gm  = new GroupBoard(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("WRITE_TIME"),
						rset.getInt("STATUS"),
						rset.getInt("GROUP_MEM_NO_STATUS"),
						rset.getString("USER_ID"),
						rset.getString("CATEGORY_NAME"));
				
				list.add(gm);
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
	}

	public int searchSelectGruopBoardListCount(Connection conn, String category, String boardStatus) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectGruopBoardListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+boardStatus+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GroupBoard> searchSelectGruopBoardList(Connection conn, PageInfo pi, String category,
			String boardStatus) {

		
		ArrayList<GroupBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectGruopBoardList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+boardStatus+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GroupBoard gm  = new GroupBoard(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("WRITE_TIME"),
						rset.getInt("STATUS"),
						rset.getInt("GROUP_MEM_NO_STATUS"),
						rset.getString("USER_ID"),
						rset.getString("CATEGORY_NAME"));
				
				list.add(gm);
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
		
	}

	public int checkUserNo(Connection conn,  GroupBoard gb) {

		int userId=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("checkUserNo");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, gb.getGroupMemNo());
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				userId = rset.getInt("USER_NO");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return userId;
	}

	public int deleteGroupUser(Connection conn, GroupBoard gb, String userNo) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGroupUser");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gb.getGroupMemNoStatus());
			pstmt.setString(2, gb.getGroupMemNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}
	public int deleteGroupBoard(Connection conn, GroupBoard gb, int bno) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGroupBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gb.getStatus());
			pstmt.setInt(2, bno);
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int deleteGroupBoardRepost(Connection conn, String board_title, String action_result, GroupBoard gb) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGroupBoardRepost");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "작성하신 게시글이 신고 되셨습니다 <br>");
			pstmt.setString(2, "신고된 게시글 : "+board_title +
					           "<br>해당 신고의 대한 조치사항 : <br>"+action_result);
			pstmt.setString(3, gb.getGroupMemNo());

			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int selectGroupCommentReportListCount(Connection conn) {

		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectGroupCommentReportListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GroupComment> selectGroupCommentReportList(Connection conn, PageInfo pi) {
		
		ArrayList<GroupComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectGroupCommentReportList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GroupComment gc  = new GroupComment(
						rset.getInt("COMMENT_NO"),
						rset.getString("CONTENT"),
						rset.getInt("COMMENT_STATUS"),
						rset.getString("USER_ID"),
						 rset.getInt("GROUP_MEM_NO_STATUS"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getString("CATEGORY_NAME")
						);
				
				list.add(gc);
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
	}

	public int searchSelectGruopCommentListCount(Connection conn, String category, String commentStatus) {

		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectGruopCommentListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+commentStatus+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GroupComment> searchSelectGruopCommentList(Connection conn, PageInfo pi, String category,
			String comment_Status) {

		ArrayList<GroupComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectGruopCommentList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			/*
			 * * 한 페이지당 boardLimit 갯수많큼 끊어서 조회해야함
			 * 규칙 먼저 구하기
			 * currentPage = 1 => 시작수 1, 끝수 10
			 * currentPage = 2 => 시작수 11, 끝수 20
			 * currentPage = 3 => 시작수 21, 끝수 30
			 * currentPage = 4 => 시작수 31, 끝수 40
			 * ....
			 * 
			 * 시작수 => (currentPage-1)*boardLimit+1
			 * 끝수는 => 시작수*boardLimit-1
			 * 
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+comment_Status+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				
				GroupComment gc  = new GroupComment(
						rset.getInt("COMMENT_NO"),
						rset.getString("CONTENT"),
						rset.getInt("COMMENT_STATUS"),
						rset.getString("USER_ID"),
						 rset.getInt("GROUP_MEM_NO_STATUS"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getString("CATEGORY_NAME")
						);
				
				list.add(gc);
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
		
		
	}

	public int deleteGroupCommentUser(Connection conn, GroupComment gc) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGroupCommentUser");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gc.getGroupMemNoStatus());
			pstmt.setString(2, gc.getGroupMemNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int deleteGroupCommentBoard(Connection conn, GroupComment gc) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGroupCommentBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gc.getStatus());
			pstmt.setInt(2, gc.getCommentNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int checkUserNoComment(Connection conn, GroupComment gc) {

		int userId=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("checkUserNoComment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, gc.getGroupMemNo());
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				userId = rset.getInt("USER_NO");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return userId;
	}

	public int deleteGroupBoardCommentRepost(Connection conn, String action_result, GroupComment gc) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGroupBoardCommentRepost");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "작성하신 댓글이 신고 되셨습니다 ");
			pstmt.setString(2, "신고된 댓글 : "+gc.getComment() +
					           "<br> 해당 신고의 대한 조치사항 : <br>"+action_result);
			pstmt.setString(3, gc.getGroupMemNo());

			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int SelectGeneralBulletinBoardReportListCount(Connection conn) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("SelectGeneralBulletinBoardReportListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GeneralBulletinBoard> SelectGeneralBulletinBoardReportList(Connection conn, PageInfo pi) {

		ArrayList<GeneralBulletinBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectGeneralBulletinBoardReportList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GeneralBulletinBoard gbb  = new GeneralBulletinBoard(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getInt("STATUS"),
							rset.getString("BOARD_WRITER"),
							rset.getInt("GROUP_MEM_NO_STATUS"),
							rset.getString("CATEGORY_NAME")
						
								);
				
				list.add(gbb);
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
		
	}

	public int searchSelectGeneralBulletinBoardReportListCount(Connection conn, String category, String boardStatus) {

		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectGeneralBulletinBoardReportListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+boardStatus+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
		
	}

	public ArrayList<GeneralBulletinBoard> searchSelectGeneralBulletinBoardReportList(Connection conn, PageInfo pi,
			String category, String boardStatus) {
		
		ArrayList<GeneralBulletinBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectGeneralBulletinBoardReportList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+boardStatus+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
//			System.out.println(category);
//			System.out.println(boardStatus);
//			System.out.println(startRow);
//			System.out.println(endRow);

		
			// 쿼리문 실행 후 결과 받기
			
			rset = pstmt.executeQuery();
			
			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GeneralBulletinBoard gbb  = new GeneralBulletinBoard(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getInt("STATUS"),
							rset.getString("BOARD_WRITER"),
							rset.getInt("GROUP_MEM_NO_STATUS"),
							rset.getString("CATEGORY_NAME")
						
								);
				
				list.add(gbb);
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
		
	}

	public int deleteCommonUser(Connection conn, GeneralBulletinBoard gbb, String userNo) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCommonUser");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gbb.getGroupMemNoStatus());
			pstmt.setString(2, gbb.getBoardWriter());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int deleteCommonBoard(Connection conn, GeneralBulletinBoard gbb) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCommonBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gbb.getStatus());
			pstmt.setInt(2, gbb.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int deleteCommonBoardRepost(Connection conn, GeneralBulletinBoard gbb,String action_result) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCommonBoardRepost");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "작성하신 게시글이 신고 되셨습니다.");
			pstmt.setString(2, "신고된 게시글 : "+gbb.getBoardTitle() +
					           "<br>해당 신고의 대한 조치사항 : <br>"+action_result);
			pstmt.setString(3, gbb.getBoardWriter());

			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int selectCommonCommentReportListCount(Connection conn) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectCommonCommentReportListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<GeneralBulletinComment> selectCommonCommentReportList(Connection conn, PageInfo pi) {

		ArrayList<GeneralBulletinComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCommonCommentReportList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GeneralBulletinComment gbc  = new GeneralBulletinComment(
							rset.getInt("REPLY_NO"),
							rset.getString("REPLY_CONTENT"),
							rset.getString("USER_ID"),
							rset.getInt("STATUS"),
							rset.getString("CATEGORY_NAME"),
							rset.getString("TITLE"),
							rset.getInt("GROUP_MEM_NO_STATUS")
						
								);
				
				list.add(gbc);
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
	}

	public int searchSelectCommonCommentListCount(Connection conn, String category, String comment_Status) {

		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectCommonCommentListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+comment_Status+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
		
	}

	public ArrayList<GeneralBulletinComment> searchSelectCommonCommentList(Connection conn, PageInfo pi,
			String category, String comment_Status) {
		
		ArrayList<GeneralBulletinComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectCommonCommentList");
		
		try {
			// 1)
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			

			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, "%"+comment_Status+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			/*
			 * System.out.println(category); System.out.println(comment_Status);
			 * System.out.println(startRow); System.out.println(endRow);
			 */
			
			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				GeneralBulletinComment gbc  = new GeneralBulletinComment(
							rset.getInt("REPLY_NO"),
							rset.getString("REPLY_CONTENT"),
							rset.getString("USER_ID"),
							rset.getInt("STATUS"),
							rset.getString("CATEGORY_NAME"),
							rset.getString("TITLE"),
							rset.getInt("GROUP_MEM_NO_STATUS")
						
								);
				
				list.add(gbc);
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
		
	}

	public int deleteCommonCommentUser(Connection conn, GeneralBulletinComment gbc) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCommonCommentUser");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gbc.getGroupMemNoStatus());
			pstmt.setString(2, gbc.getReplyWriter());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int deleteCommonBoardComment(Connection conn, GeneralBulletinComment gbc) {


		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCommonBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gbc.getStatus());
			pstmt.setInt(2, gbc.getReplyNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int deleteCommonBoardCommentRepost(Connection conn, GeneralBulletinComment gbc, String action_result) {

		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCommonBoardCommentRepost");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "작성하신 댓글이 신고 되셨습니다.");
			pstmt.setString(2, "<br>신고된 댓글 : "+gbc.getReplyComment() +
					           "<br>해당 신고의 대한 조치사항 : <br>"+action_result);
			pstmt.setString(3, gbc.getReplyWriter());

			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public String reportCount(Connection conn, String string) {

		String reportCount=null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("reportCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, string);
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = ""+rset.getInt("COUNT")+"";
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}

	public int reportCount1(Connection conn, String board) {
		int reportCount=0;;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("reportCount1");

		try {
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setString(1, board);
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}

	public int reportCount2(Connection conn, String reply) {
		
		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("reportCount2");

		try {
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setString(1, reply);
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}

	public int reportCount3(Connection conn, String groupBoard) {
		
		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("reportCount3");

		try {
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setString(1, groupBoard);
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}

	public int reportCount4(Connection conn, String groupComment) {
		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("reportCount4");

		try {
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setString(1, groupComment);
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}

	public int category1(Connection conn, String category1) {

		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("category");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category1+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}
	public int category2(Connection conn, String category2) {

		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("category");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category2+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}
	public int category3(Connection conn, String category3) {

		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("category");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category3+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}
	public int category4(Connection conn, String category4) {

		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("category");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category4+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}
	public int category5(Connection conn, String category5) {

		int reportCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("category");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+category5+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				reportCount = rset.getInt("COUNT");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return reportCount;
	}

	public int selectMeetingListCount(Connection conn) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectMeetingListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Meeting> selectMeetingList(Connection conn, PageInfo pi) {
		
		ArrayList<Meeting> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMeetingList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				Meeting m  = new Meeting(
						rset.getInt("MEETING_NO"),
						rset.getString("MEETING_NAME"),
						rset.getString("MEETING_SPOT"),
						rset.getString("GROUP_NO"),
						rset.getInt("STATUS"),
						rset.getString("CREATE_USER"));
				list.add(m);
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
	}

	public int searchSelectMeetingListCount(Connection conn, String search) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectMeetingListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+search+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Meeting> searchSelectMeetingList(Connection conn, PageInfo pi, String search) {

		ArrayList<Meeting> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectMeetingList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				Meeting m  = new Meeting(
						rset.getInt("MEETING_NO"),
						rset.getString("MEETING_NAME"),
						rset.getString("MEETING_SPOT"),
						rset.getString("GROUP_NO"),
						rset.getInt("STATUS"),
						rset.getString("CREATE_USER"));
				list.add(m);
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
	}

	public int deleteMeeting(Connection conn, Meeting m) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMeeting");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, m.getStatus());
			pstmt.setInt(2, m.getMeetingNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int selectMeetingMemberListCount(Connection conn) {
		
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectMeetingMemberListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<MeetingMember> selectMeetingMemberList(Connection conn, PageInfo pi) {

		ArrayList<MeetingMember> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMeetingMemberList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				MeetingMember mm  = new MeetingMember(
						rset.getString("MEETING_NO"),
						rset.getString("MEETING_TITLE"),
						rset.getString("GROUP_MEM_NO"),
						rset.getString("GROUP_MEM_ID"),
						rset.getInt("STATUS"),
						rset.getInt("GROUP_MEM_NO_STATUS"));
				list.add(mm);
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
	}

	public int searchSelectMeetingMemberListCount(Connection conn, String search) {

		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchSelectMeetingMemberListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+search+"%");
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return listCount;
	}

	public ArrayList<MeetingMember> searchSelectMeetingMemberList(Connection conn, PageInfo pi, String search) {

		ArrayList<MeetingMember> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchSelectMeetingMemberList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);


			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rest 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()){
				MeetingMember mm  = new MeetingMember(
						rset.getString("MEETING_NO"),
						rset.getString("MEETING_TITLE"),
						rset.getString("GROUP_MEM_NO"),
						rset.getString("GROUP_MEM_ID"),
						rset.getInt("STATUS"),
						rset.getInt("GROUP_MEM_NO_STATUS"));
				list.add(mm);
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
	}

	public int deleteMeetingMemberUser(Connection conn, MeetingMember mm) {
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMeetingMemberUser");
		
		// System.out.println(mm.getGroupMemNoStatus());
		// System.out.println(mm.getGroupMemId());
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mm.getGroupMemNoStatus());
			pstmt.setString(2, mm.getGroupMemId());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public int deleteMeetingMemberRank(Connection conn, MeetingMember mm) {

		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMeetingMemberRank");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mm.getStatus());
			pstmt.setString(2, mm.getMeetingNo());
			pstmt.setString(3, mm.getGroupMemNo());
			
			result = pstmt.executeUpdate();
			
			// System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5)

			JDBC.close(pstmt);
		}
		
		return result;
	}

	public String checkUserId(Connection conn, String userNo) {
		
		String userId =null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("checkUserId");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userNo);
			
			rset=pstmt.executeQuery();

			if(rset.next()) {
				userId = rset.getString("USER_ID");
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return userId;
	}
}
