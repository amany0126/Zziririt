package com.zziririt.group.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.zziririt.common.JDBC;
import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.group.model.vo.Group;
import com.zziririt.group.model.vo.GroupBoard;
import com.zziririt.group.model.vo.GroupComment;
import com.zziririt.group.model.vo.Meeting;
import com.zziririt.member.model.vo.Member;

public class GroupDao {
	private Properties prop = new Properties();

	public GroupDao() {

		String fileName = GroupDao.class.getResource("/sql/group/group-mapper.xml").getPath();

		System.out.println(fileName.isEmpty());

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 용환 구간 용환 구간용환 구간용환 구간용환 구간용환 구간용환 구간용환 구간용환 구간용환 구간용환 구간
	public int searchGroupPage(Connection conn, String category, String area, String keyword, String userNo) {
		String sql = prop.getProperty("searchGroupPage");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		// 카테고리가 있을 경우
		if (category != null && !category.isEmpty()) {
			sql += "AND CATEGORY_NAME = ?";
		}
		// 지역선택이 있을 경우
		if (area != null && !area.isEmpty()) {
			sql += "AND GROUP_AREA LIKE ?";
		}
		// 검색어가 있을 경우
		if (keyword != null && !keyword.isEmpty()) {
			sql += "AND (GROUP_NAME LIKE ? OR GROUP_DESCRIPT LIKE ?)";
		}
		// 로그인한 경우
		if (userNo != null && !userNo.isEmpty()) {
			sql += "AND GROUP_NO NOT IN(SELECT GROUP_NO FROM GROUP_MEM WHERE USER_NO = ?)";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if (category != null && !category.isEmpty()) {
				pstmt.setString(index++, category);
			}
			// 지역선택이 있을 경우
			if (area != null && !area.isEmpty()) {
				pstmt.setString(index++, "%" + area + "%");
			}
			// 검색어가 있을 경우
			if (keyword != null && !keyword.isEmpty()) {
				pstmt.setString(index++, "%" + keyword + "%");
				pstmt.setString(index++, "%" + keyword + "%");
			}
			// 로그인한 경우
			if (userNo != null && !userNo.isEmpty()) {
				pstmt.setInt(index, Integer.parseInt(userNo));
			}
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return result;
	}

	public ArrayList<Group> searchGroupList(Connection conn, String category, String area, String keyword,
			String userNo, PageInfo pi) {
		String sql = prop.getProperty("searchGroupList");
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Group> list = new ArrayList();
		;
		if (userNo != null && !userNo.isEmpty()) {
			sql += ",(select count(*) from wishlist where group_no= t.group_no and user_no = ?) as wish";
		}
		sql += " FROM tgroup t WHERE 1=1";
		if (category != null && !category.isEmpty()) {
			sql += "AND CATEGORY_NAME = ?";
		}
		// 지역선택이 있을 경우
		if (area != null && !area.isEmpty()) {
			sql += "AND GROUP_AREA LIKE ?";
		}
		// 검색어가 있을 경우
		if (keyword != null && !keyword.isEmpty()) {
			sql += "AND (GROUP_NAME LIKE ? OR GROUP_DESCRIPT LIKE ?)";
		}
		// 로그인한 경우
		if (userNo != null && !userNo.isEmpty()) {
			sql += "AND GROUP_NO NOT IN(SELECT GROUP_NO FROM GROUP_MEM WHERE USER_NO = ?)";
		}
		sql += ")WHERE row_num BETWEEN ? AND ?";

		try {
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if (userNo != null && !userNo.isEmpty()) {
				pstmt.setInt(index++, Integer.parseInt(userNo));
			}

			if (category != null && !category.isEmpty()) {
				pstmt.setString(index++, category);
			}
			// 지역선택이 있을 경우
			if (area != null && !area.isEmpty()) {
				pstmt.setString(index++, "%" + area + "%");
			}
			// 검색어가 있을 경우
			if (keyword != null && !keyword.isEmpty()) {
				pstmt.setString(index++, "%" + keyword + "%");
				pstmt.setString(index++, "%" + keyword + "%");
			}
			// 로그인한 경우
			if (userNo != null && !userNo.isEmpty()) {
				pstmt.setInt(index++, Integer.parseInt(userNo));
			}
			// paging 넘겨주기
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			pstmt.setInt(index++, startRow);
			pstmt.setInt(index, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Group g = new Group();
				g.setGroupNo(rset.getInt("GROUP_NO"));// pk
				g.setGroupProfile(rset.getString("GROUP_PROFILE"));// 대표 이미지
				g.setGroupName(rset.getString("GROUP_NAME"));// 이름
				g.setGroupArea(rset.getString("GROUP_AREA"));// 주활동지
				g.setGroupLimit(rset.getInt("GROUP_LIMIT"));// 정원
				g.setCurrentMem(rset.getInt("CURRENT_MEM"));// 현재인원
				g.setGroupCreateday(rset.getDate("GROUP_CREATEDAY"));// 개설일
				if (userNo != null && !userNo.isEmpty()) {

					g.setWish(rset.getInt("WISH") + "");// 좋아요 /null 표시안함 //did 빨간//else 갈색
					System.out.println(g.getWish());
				}
				list.add(g);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return list;
	}

	public int checkWish(Connection conn, String gno, String userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("checkWish");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(gno));
			pstmt.setInt(2, Integer.parseInt(userNo));
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return result;
	}

	public int removeWish(Connection conn, String gno, String userNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("removeWish");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(gno));
			pstmt.setInt(2, Integer.parseInt(userNo));

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
		}
		return result;
	}

	public int addWish(Connection conn, String gno, String userNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("addWish");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(gno));
			pstmt.setInt(2, Integer.parseInt(userNo));

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
		}
		return result;
	}

	public int groupNameCheck(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("groupNameCheck");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
				System.out.println(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return result;
	}

	public int createGroup(Connection conn, Group g, int userNo) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql1 = prop.getProperty("createGroup");
		String sql2 = prop.getProperty("insertGroupMember");
		int result1 = 0;
		int result2 = 0;

		// INSERT INTO groups (GROUP_NO, GROUP_NAME, GROUP_DESCRIPT, GROUP_LIMIT,
		// GROUP_CREATEDAY, GROUP_AREA, GROUP_PROFILE, STATUS, CATEGORY_NAME)
		// VALUES (1, ?, ?, ?, sysdate, ?, ?, 1, ?);

		// insert into group_mem (group_mem_no, group_mem_type, group_mem_ment,
		// group_join_date, status, user_no, group_no)
		// values (SEQ_GMNO.nextval, ?, ?, sysdate, 1, ?,
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, g.getGroupName());
			pstmt1.setString(2, g.getGroupDescript());
			pstmt1.setInt(3, g.getGroupLimit());
			pstmt1.setString(4, g.getGroupArea());
			pstmt1.setString(5, g.getGroupProfile());
			pstmt1.setString(6, g.getCategoryName());
			result1 = pstmt1.executeUpdate();

			if (result1 > 0) {
				pstmt2 = conn.prepareStatement(sql2 + "seq_gno.currval)");
				pstmt2.setInt(1, 3);
				pstmt2.setString(2, g.getWish());
				pstmt2.setInt(3, userNo);
				result2 = pstmt2.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt1);
		}
		return result2;
	}

	public HashMap<String, ArrayList<Meeting>> getMeetingList(Connection conn, String gno, String year, String month) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getMeetingList");
		HashMap<String, ArrayList<Meeting>> result = new HashMap<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gno);
			pstmt.setString(2, year);
			pstmt.setString(3, month);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				String meetingDay = rset.getString("DAY");

				// 해당 날짜의 모임 리스트를 가져옴
				ArrayList<Meeting> meetings = result.get(meetingDay);
				if (meetings == null) {
					// 만약 해당 날짜에 모임 리스트가 없으면 새로운 리스트 생성
					meetings = new ArrayList<>();
					result.put(meetingDay, meetings);
				}
				int meetingNo = rset.getInt("MEETING_NO");
				String meetingName = rset.getString("MEETING_NAME");
				String meetingSpot = rset.getString("MEETING_SPOT");
				String meetingYear = rset.getString("YEAR");
				String meetingMonth = rset.getString("MONTH");
				String meetingHour = rset.getString("HOUR");
				String meetingMin = rset.getString("MIN");
				String meetingContent = rset.getString("MEETING_CONTENT");
				int curMem = rset.getInt("CURMEM");
				int meetingLimit = rset.getInt("MEETING_LIMIT");
				// 모임 정보 추가
				Meeting m = new Meeting(meetingNo, meetingName, meetingSpot, meetingYear, meetingMonth, meetingDay,
						meetingHour, meetingMin, meetingContent, curMem, meetingLimit, Integer.parseInt(gno));
				m.setCreateUser(rset.getInt("CREATE_USER"));
				meetings.add(m);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return result;

	}

	public Group getGroupViewNormal(Connection conn, String gno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getGroupViewNormal");
		Group g = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gno);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				g = new Group();
				g.setGroupNo(rset.getInt("GROUP_NO"));
				g.setGroupName(rset.getString("GROUP_NAME"));
				g.setGroupDescript(rset.getString("GROUP_DESCRIPT"));
				g.setGroupLimit(rset.getInt("GROUP_LIMIT"));
				g.setGroupCreateday(rset.getDate("GROUP_CREATEDAY"));
				g.setGroupArea(rset.getString("GROUP_AREA"));
				g.setGroupProfile(rset.getString("GROUP_PROFILE"));
				g.setCategoryName(rset.getString("CATEGORY_NAME"));
				g.setCurrentMem(rset.getInt("CURMEM"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return g;
	}

	// 유저 등급조회
	public int getUserType(Connection conn, int userNo, int groupNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getUserType");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, groupNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("GROUP_MEM_TYPE");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return result;

	}

	// 그룹 신청
	public int joinGroup(Connection conn, String descriptSelf, Member m, String gno) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int result = 0;
		String sql = prop.getProperty("joinGroup");
		String sql2 = "delete wishlist where user_no =? and group_no =?";
		// INSERT INTO GROUP_MEM (GROUP_MEM_NO, GROUP_MEM_TYPE, GROUP_MEM_MENT,
		// GROUP_JOIN_DATE, STATUS, USER_NO, GROUP_NO)
		// VALUES (SEQ_GMNO.NEXTVAL, 1, ?, SYSDATE, 1, ?, ?);
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, descriptSelf);
			pstmt.setInt(2, m.getUserNo());
			pstmt.setString(3, gno);
			result = pstmt.executeUpdate();
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, m.getUserNo());
			pstmt2.setString(2, gno);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
			JDBC.close(pstmt2);
		}
		return result;
	}

	// 그룹 이미지 변경
	public int changeGroupImg(Connection conn, String groupProfile, String gno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("changeGroupImg");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupProfile);
			pstmt.setString(2, gno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
		}
		return result;
	}

	// 그룹 이름 변경
	public int changeGroupName(Connection conn, String name, String gno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("changeGroupName");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, gno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
		}
		return result;
	}

	// 그룹 설명 변경
	public int changeDescript(Connection conn, String descript, String gno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("changeDescript");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, descript);
			pstmt.setString(2, gno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
		}
		return result;

	}

	// 정모 추가
	public int createMeeting(Connection conn, Meeting m) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int result1 = 0;
		int result2 = 0;
		String sql1 = prop.getProperty("createMeeting");
		String sql2 = prop.getProperty("insertCreateMeetingMem");

		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, m.getMeetingName());
			pstmt1.setString(2, m.getMeetingSpot());
			String dateString = m.getMeetingMonth();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			java.util.Date date = null;
			try {
				date = dateFormat.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt1.setTimestamp(3, new Timestamp(date.getTime()));
			pstmt1.setString(4, m.getMeetingContent());
			pstmt1.setInt(5, m.getMeetingLimit());
			pstmt1.setString(6, m.getMeetingYear());
			pstmt1.setInt(7, m.getCreateUser());

			result1 = pstmt1.executeUpdate();
			if (result1 > 0) {
				pstmt2 = conn.prepareStatement(sql2);
				System.out.println(m.getCreateUser());
				pstmt2.setInt(1, m.getCreateUser());
				result2 = pstmt2.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt1);
			JDBC.close(pstmt2);
		}

		return result2;
	}

	// 날짜로 모임 조회
	public ArrayList<Meeting> getMeetingListByDay(Connection conn, String gno, String year, String month, String day) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String sql = prop.getProperty("getMeetingList");
		System.out.println(year + " " + month + " " + day);
		try {
			pstmt = conn.prepareStatement(sql + "and extract(day from meeting_time) = ?");
			pstmt.setString(1, gno);
			pstmt.setString(2, year);
			pstmt.setString(3, month);
			pstmt.setString(4, day);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingSpot(rset.getString("meeting_spot"));
				m.setMeetingYear(rset.getString("year"));
				m.setMeetingMonth(rset.getString("month"));
				m.setMeetingDay(rset.getString("day"));
				m.setMeetingHour(rset.getString("hour"));
				m.setMeetingMinute(rset.getString("min"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setCurMem(rset.getInt("curmem"));
				m.setMeetingLimit(rset.getInt("meeting_limit"));
				m.setCreateUser(rset.getInt("create_user"));
				m.setUserName(rset.getString("user_name"));
				m.setGroupNo(Integer.parseInt(gno));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
			JDBC.close(rset);
		}
		return list;
	}

	// id로 모임 조회
	public Meeting getMeetingById(Connection conn, String id, String userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getMeetingById");
		Meeting m = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userNo);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				m = new Meeting();
				m.setMeetingNo(rset.getInt("MEETING_NO"));
				m.setMeetingName(rset.getString("MEETING_NAME"));
				m.setMeetingSpot(rset.getString("MEETING_SPOT"));
				m.setMeetingYear(rset.getString("time"));//시간
				m.setMeetingContent(rset.getString("MEETING_CONTENT"));
				m.setMeetingLimit(rset.getInt("MEETING_LIMIT"));
				m.setUserName(rset.getString("NICKNAME"));
				m.setStatus(rset.getInt("check"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
			JDBC.close(rset);
		}

		return m;
	}
	
	// 가입 그룹 조회
	public ArrayList<Group> getJoinGroupList(Connection conn, int userNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getJoinGroupList");
		ArrayList<Group> list= new ArrayList<Group>(); 
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Group g = new Group();
				g.setGroupNo(rset.getInt("GROUP_NO"));
				g.setGroupName(rset.getString("GROUP_NAME"));
				g.setGroupLimit(rset.getInt("GROUP_LIMIT"));
				g.setCurrentMem(rset.getInt("CURMEM"));
				list.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
			JDBC.close(rset);
		}
		return list;
		
		
	}
	
	// 찜 그룹 조회
	public ArrayList<Group> getWishGroupList(Connection conn, int userNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getWishGroupList");
		ArrayList<Group> list= new ArrayList<Group>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Group g = new Group();
				g.setGroupNo(rset.getInt("GROUP_NO"));
				g.setGroupName(rset.getString("GROUP_NAME"));
				g.setGroupLimit(rset.getInt("GROUP_LIMIT"));
				g.setCurrentMem(rset.getInt("CURMEM"));
				list.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
			JDBC.close(rset);
		}
		return list;
	}
	
	// 그룹 최신순 조회
	public ArrayList<Group> getNewGroupList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Group> result = new ArrayList<Group>();
		String sql = prop.getProperty("getNewGroupList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Group g = new Group();
				g.setGroupNo(rset.getInt("GROUP_NO"));
				g.setGroupName(rset.getString("GROUP_NAME"));
				g.setGroupProfile(rset.getString("GROUP_PROFILE"));
				g.setGroupLimit(rset.getInt("GROUP_LIMIT"));
				g.setCurrentMem(rset.getInt("COUNT"));
				g.setGroupArea(rset.getString("GROUP_AREA"));
				result.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return result;
	}
	
	// 그룹 인원수 조회
	public ArrayList<Group> getCloseGroupList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Group> result = new ArrayList<Group>();
		String sql = prop.getProperty("getCloseGroupList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Group g = new Group();
				g.setGroupNo(rset.getInt("GROUP_NO"));
				g.setGroupName(rset.getString("GROUP_NAME"));
				g.setGroupProfile(rset.getString("GROUP_PROFILE"));
				g.setGroupLimit(rset.getInt("GROUP_LIMIT"));
				g.setCurrentMem(rset.getInt("COUNT"));
				g.setGroupArea(rset.getString("GROUP_AREA"));
				result.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return result;
	}
	

	// 은서 구간은서 구간은서 구간은서 구간은서 구간은서 구간은서 구간은서 구간은서 구간은서 구간은서 구간
	// 총 게시글 갯수 구하는 메소드
	public int selectListCount(Connection conn) {

		// SELECT문 > ResultSet 객체 (단일행)
		// > int 변수에 옮겨담기

		// 필요한 변수들 먼저 셋팅
		int listCount = 0; // 총 게시글의 갯수를 담을 변수
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

			// 4) rset 으로부터 조회된 결과를
			// listCount 변수에 옮겨담기
			if (rset.next()) {

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

//게시글 한건 조회
	public GroupBoard selectGroup(Connection conn, int groupNo) {

		GroupBoard gb = new GroupBoard();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectGroup");

		try {
			pstmt = conn.prepareStatement(sql);

			// 쿼리에 필요한 매개변수 설정
			pstmt.setInt(1, groupNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {

				// GroupBoard 객체 생성 및 데이터 할당
				gb = new GroupBoard(rset.getInt("GROUP_BOARD_NO"), rset.getString("GROUP_BOARD_TITLE"),
						rset.getString("GROUP_BOARD_CONTENT"), rset.getString("WRITE_TIME"),
						rset.getString("WRITER_NICKNAME"));

				// Group 객체에 GroupBoard 객체 추가

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}

		return gb;
	}

//게시글 작성용 메소드
	public int insertGroup(Connection conn, Group g) {

		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 SQL문
		String sql = prop.getProperty("insertGroup");

		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setInt(1, Integer.parseInt(g.getCategory()));
			pstmt.setString(2, g.getBoardTitle());
			pstmt.setString(3, g.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(g.getBoardWriter()));

			// 3)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4)
			JDBC.close(pstmt);
			// > 여기서 절대로 conn 반납하면 안됨!!
		}

		// 5)
		return result;
	}

	// insertGroupBoard 메서드: 그룹 게시글 추가
	public int insertGroupBoard(Connection conn, GroupBoard gb) {
		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 SQL문
		String sql = prop.getProperty("insertGroupBoard");

		// System.out.println(gb.getGroupNo());
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// SQL문의 ?에 값 설정
			pstmt.setString(1, gb.getBoardTitle());
			pstmt.setString(2, gb.getBoardContent());
			pstmt.setString(3, gb.getGroupMemNo());
			pstmt.setInt(4, gb.getGroupNo());

			// SQL문 실행 및 결과 처리
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBC.close(pstmt);
		}

		return result;
	}

//게시글 리스트 조회 
	public ArrayList<Group> selectGroupList(Connection conn, PageInfo pi) {
		ArrayList<Group> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = prop.getProperty("selectGroupList");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;

			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()) {

				Group g = new Group();

				list.add(g);
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

	public int selectGroupBoardListCount(Connection conn) {

		// SELECT문 > ResultSet 객체 (단일행)
		// > int 변수에 옮겨담기

		// 필요한 변수들 먼저 셋팅
		int listCount = 0; // 총 게시글의 갯수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = prop.getProperty("selectGroupBoardListCount");

		try {

			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 2) 미완성된 쿼리문 완성시키기
			// > 패스

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rset 으로부터 조회된 결과를
			// listCount 변수에 옮겨담기
			if (rset.next()) {

				listCount = rset.getInt("COUNT"); // 별칭
			}

			// System.out.println(listCount);
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

	public ArrayList<GroupBoard> selectGroupBoardList(Connection conn, PageInfo pi) {

		ArrayList<GroupBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = prop.getProperty("selectGroupBoardList");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;

			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// System.out.println(startRow);
			// System.out.println(endRow);

			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()) {

				GroupBoard gb = new GroupBoard(rset.getInt("BOARD_NO"), rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"), rset.getString("WRITE_TIME"), rset.getInt("STATUS"),
						rset.getString("USER_NICKNAME"), rset.getString("GROUP_NAME"));

				list.add(gb);
			}

			// System.out.println(list.isEmpty());
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

// 게시글 수정용 메소드 (UPDATEG GROUP용)
	public int updateGroup(Connection conn, GroupBoard gb) {

		// UPDATE 문 > 처리된 행의 갯수 (int)

		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 SQL문
		String sql = prop.getProperty("updateGroup");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setString(1, gb.getBoardTitle());
			pstmt.setString(2, gb.getBoardContent());
			pstmt.setInt(3, gb.getBoardNo());

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

	public String selectUserNum(Connection conn, String userId) {

		// 필요한 변수들 먼저 셋팅
		String userNum = null; // 총 게시글의 갯수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = prop.getProperty("selectUserNum");

		try {

			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 2) 미완성된 쿼리문 완성시키기
			pstmt.setString(1, userId);

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 4) rset 으로부터 조회된 결과를
			// listCount 변수에 옮겨담기
			if (rset.next()) {

				userNum = rset.getString("GROUP_MEM_NO"); // 별칭
			}

			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 5) 자원반납 (역순)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}

		// 6) Service 로 결과 리턴
		return userNum;
	}
	public String selectUserNum1(Connection conn, int userNo) {
		
		// 필요한 변수들 먼저 셋팅
		String userNum = null; // 총 게시글의 갯수를 담을 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectUserNum");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			pstmt.setInt(1, userNo);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를
			// listCount 변수에 옮겨담기
			if (rset.next()) {
				
				userNum = rset.getString("GROUP_MEM_NO"); // 별칭
			}
			
			// System.out.println(listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5) 자원반납 (역순)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6) Service 로 결과 리턴
		return userNum;
	}

//게시글 삭제
	public int deleteGroup(Connection conn, int boardNo) {

		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteGroup");

		try {
			// 1)

			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setInt(1, boardNo);

			// 3)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4)
			JDBC.close(pstmt);
		}

		return result;
	}

	public int deleteGroupBoard(Connection conn, int groupNo, int userNo) {

		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 SQL문
		String sql = prop.getProperty("deleteGroupBoard");

		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)

			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, userNo);

			// 3)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4)
			JDBC.close(pstmt);
			// > 여기서 절대로 conn 반납하면 안됨!!
		}

		// 5)
		return result;
	}

//게시글 신고
	public int declareGroup(Connection conn, int boardNo) {

		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("declareGroup");

		try {
			// 1)

			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setInt(1, boardNo);

			// 3)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4)
			JDBC.close(pstmt);
		}

		return result;
	}

	public int declareGroupBoard(Connection conn, int groupNo) {

		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 SQL문
		String sql = prop.getProperty("declareGroupBoard");

		try {
			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)

			pstmt.setInt(1, groupNo);

			// 3)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4)
			JDBC.close(pstmt);
			// > 여기서 절대로 conn 반납하면 안됨!!
		}

		// 5)
		return result;
	}

	public ArrayList<Group> searchUserGroup(Connection conn, String userId) {

		ArrayList<Group> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = prop.getProperty("searchUserGroup");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// System.out.println(startRow);
			// System.out.println(endRow);

			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()) {

				Group g = new Group(

						Integer.parseInt(rset.getString("GROUP_NO")), rset.getString("GROUP_NAME"));
				list.add(g);
			}

			// System.out.println(list.isEmpty());
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

	public String selectTime(Connection conn, int groupNo) {

		String writeTime = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = prop.getProperty("selectTime");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, groupNo);

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// System.out.println(startRow);
			// System.out.println(endRow);

			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()) {

				writeTime = rset.getString("WRITE_TIME");

			}

			// System.out.println(list.isEmpty());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}

		// 6)
		return writeTime;

	}

//댓글 리스트 조회용 메소드
	public ArrayList<GroupComment> selectCommentList(Connection conn, int groupNo) {
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList<GroupComment>

		// 필요한 변수들 먼저 셋팅
		ArrayList<GroupComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 쿼리문
		String sql = prop.getProperty("selectCommentList");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setInt(1, groupNo);

			// 3)
			rset = pstmt.executeQuery();

			// 4)
			while (rset.next()) {

				GroupComment gc = new GroupComment();

				gc.setCommentNo(rset.getInt("COMMENT_NO"));
				gc.setContent(rset.getString("CONTENT"));
				gc.setUserNickName(rset.getString("USER_NICKNAME"));
				gc.setWriteTime(rset.getString("WRITE_TIME"));

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

// 댓글 작성용 메소드
	public int insertComment(Connection conn, GroupComment gc) {
		// INSERT 문 > 처리된 행의 갯수 (int)

		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 쿼리문
		String sql = prop.getProperty("insertComment");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setString(1, gc.getContent());
			pstmt.setInt(2, gc.getGroupMemNo());
			pstmt.setInt(3, (gc.getBoardNo()));

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

//댓글 삭제
	public int deleteComment(Connection conn, int commentNo) {

		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteComment");

		try {
			// 1)

			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setInt(1, commentNo);

			// 3)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4)
			JDBC.close(pstmt);
		}

		return result;
	}

// 댓글 수정 메소드
	public int updateComment(Connection conn, int commentNo, String updatedContent) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedContent);
			pstmt.setInt(2, commentNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);

		}
		return result;
	}

//댓글 신고 
	public int declareComment(Connection conn, int commentNo) {

		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("declareComment");

		try {
			// 1)

			pstmt = conn.prepareStatement(sql);

			// 2)
			pstmt.setInt(1, commentNo);

			// 3)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4)
			JDBC.close(pstmt);
		}

		return result;
	}

	public int selectUserNo(Connection conn, String userId) {
		
		int userNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = prop.getProperty("selectUserNo");

		try {

			// 1)
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// System.out.println(startRow);
			// System.out.println(endRow);

			// 4) rset 으로부터 조회된 결과를 VO 로 옮기기
			while (rset.next()) {

				userNo = rset.getInt("USER_NO");

			}

			// System.out.println(list.isEmpty());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 5)
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		
		// 6)
		return userNo;
	}
}