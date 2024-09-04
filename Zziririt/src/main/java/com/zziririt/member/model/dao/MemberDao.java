package com.zziririt.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zziririt.common.JDBC;
import com.zziririt.member.model.vo.Member;

public class MemberDao { // MemberDao �겢�옒�뒪 �쁺�뿭 �떆�옉

	private Properties prop = new Properties();

	// 湲곕낯�깮�꽦�옄 �븞�뿉
	// member-mapper.xml �뙆�씪濡쒕��꽣 荑쇰━臾몃뱾�쓣
	// key + value �꽭�듃濡� �씫�뼱�뱾�씠�뒗 怨듯넻 肄붾뱶 �옉�꽦
	public MemberDao() {
		// �씫�뼱�뱾�씪 member-mapper.xml �뙆�씪�쓽 臾쇰━�쟻�씤 寃쎈줈 (webapp �뿉 �엳�뒗 �뤃�뜑濡� 寃쎈줈
		// �옟湲�!!)
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath(); // 留� �븵�쓽 "/" �뒗
																									// classes �뤃�뜑

		try {
			prop.loadFromXML(new FileInputStream(fileName)); // fileName 蹂��닔紐�!!
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �쉶�썝媛��엯�슜 DAO
	public int insertMember(Connection conn, Member m) { // insertMember 硫붿꽌�뱶 �쁺�뿭 �떆�옉

		// INSERT臾� > 泥섎━�맂 �뻾�쓽 媛쒖닔 (int)

		// �븘�슂�븳 蹂��닔�뱾 癒쇱� �꽭�똿
		int result = 0; // 寃곌낵媛� �떞�쓣 蹂��닔 result
		PreparedStatement pstmt = null; // PreparedStatement 媛앹껜 蹂��닔 pstmt

		// �떎�뻾�븷 SQL臾�
		String sql = prop.getProperty("insertMember");

		try {
			// 1) PreparedStatement 媛앹껜 �깮�꽦
			pstmt = conn.prepareStatement(sql);

			// 2) 誘몄셿�꽦�맂 SQL臾� �셿�꽦�떆�궎湲�
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserNickname());
			pstmt.setString(4, m.getUserName());
			pstmt.setString(5, m.getUserGender());
			pstmt.setString(6, m.getUserProfile());
			pstmt.setString(7, m.getUserPhone());
			pstmt.setString(8, m.getUserMail());
			pstmt.setString(9, m.getBirthDate());
			pstmt.setString(10, m.getAddress());
			pstmt.setString(11, m.getIntroduceContent());

			// 3) SQL臾� �떎�뻾 �썑 寃곌낵 諛쏄린
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) PreparedStatement 媛앹껜 諛섎궔
			JDBC.close(pstmt);
		}

		// 5) �꽌鍮꾩뒪濡� 寃곌낵 諛섑솚
		return result;
	} // insertMember 硫붿꽌�뱶 �쁺�뿭 �걹

	public int nicknameCheck(Connection conn, String checkNickname) { // nicknameCheck 硫붿꽌�뱶 �쁺�뿭 �떆�옉

		// SELECT臾� > ResultSet 媛앹껜 (�떒�씪�뻾 議고쉶)
		// > int�삎

		// �븘�슂�븳 蹂��닔�뱾 癒쇱� �꽭�똿
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// �떎�뻾�븷 SQL臾�
		String sql = prop.getProperty("nicknameCheck");

		try {
			// 1) PreparedStatement 媛앹껜 �깮�꽦
			pstmt = conn.prepareStatement(sql);

			// 2) 誘몄셿�꽦�맂 SQL臾� �셿�꽦�떆�궎湲�
			pstmt.setString(1, checkNickname);

			// 3) SQL臾� �떎�뻾
			rset = pstmt.executeQuery();

			// 4) �쉶�썝�젙蹂� 議고쉶 �썑 寃곌낵 諛쏄린
			if (rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) �옄�썝 諛섎궔
			JDBC.close(rset);
			JDBC.close(pstmt);
		}

		// 6) �꽌鍮꾩뒪濡� 寃곌낵 諛섑솚
		return count;
	} // nicknameCheck 硫붿꽌�뱶 �쁺�뿭 �걹

	public int idCheck(Connection conn, String checkId) { // idCheck 硫붿꽌�뱶 �쁺�뿭 �떆�옉

		// SELECT臾� > ResultSet 媛앹껜 (�떒�씪�뻾 議고쉶)
		// > int�삎

		// �븘�슂�븳 蹂��닔�뱾 癒쇱� �꽭�똿
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// �떎�뻾�븷 SQL臾�
		String sql = prop.getProperty("idCheck");

		try {
			// 1) PreparedStatement 媛앹껜 �깮�꽦
			pstmt = conn.prepareStatement(sql);

			// 2) 誘몄셿�꽦�맂 SQL臾� �셿�꽦�떆�궎湲�
			pstmt.setString(1, checkId);

			// 3) SQL臾� �떎�뻾
			rset = pstmt.executeQuery();

			// 4) �쉶�썝�젙蹂� 議고쉶 �썑 寃곌낵 諛쏄린
			if (rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) �옄�썝 諛섎궔
			JDBC.close(rset);
			JDBC.close(pstmt);
		}

		// 6) �꽌鍮꾩뒪濡� 寃곌낵 諛섑솚
		return count;
	} // idCheck 硫붿꽌�뱶 �쁺�뿭 �걹

	public Member loginMember(Connection conn, Member m) {

// SELECT 문 > ResultSet 객체 (단일행조회) > Member

// 필요한 변수들 먼저 셋팅
		Member loginUser = null; // 조회한 결과를 담을 변수
		PreparedStatement pstmt = null; // 쿼리문을 실행할 변수
		ResultSet rset = null; // select 문의 실행 결과를 담을 변수

// 실행할 SQL문
		String sql = prop.getProperty("loginMember");

		try {
// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

//System.out.println(m.getUserId());
//System.out.println(m.getUserPwd());

// 2) 미완성된 SQL 문 완성시키기
// pstmt.setXXXX(n, xxx);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());

// 3) 쿼리문 실행 후 결과 받기
// SELECT : pstmt.executeQuery(); > ResultSet 객체
// DML : pstmt.executeUpdate(); > int (처리된 행의 갯수)
			rset = pstmt.executeQuery();

// 4) ResultSet 으로 부터 조회된 결과를
//    VO 객체로 옮겨담기
// > rset.next() 메소드 활용
			System.out.println(m.getUserId());
			System.out.println(m.getUserPwd());
			if (rset.next()) {
// 컬럼값을 뽑을 때
// rset.getXXX("컬럼명");
				loginUser = new Member(rset.getInt("USER_NO"), rset.getString("USER_ID"), rset.getString("USER_PWD"),
						rset.getString("USER_NICKNAME"), rset.getString("USER_NAME"), rset.getString("USER_GENDER"),
						rset.getString("USER_PROFILE"), rset.getString("USER_PHONE"), rset.getString("USER_MAIL"),
						rset.getString("BIRTH_DATE"), rset.getDate("JOIN_DATE"),rset.getDate("MODIFY_DATE"), rset.getInt("STATUS"),
						rset.getString("ADDRESS"), rset.getString("INTRODUCE_CONTENT"));
				System.out.println(loginUser);
// 이 시점 기준으로
// 조회된 회원이 없었다면
// loginUser 에는 여전히 null 이 담겨있을 것
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

// 5) 다 쓴 JDBC 용 자원 반납 (반드시)
// > 생성 순서의 역순으로
			JDBC.close(rset);
			JDBC.close(pstmt);
		}

// 6) Service 로 결과 리턴
		return loginUser;
	}

} // MemberDao �겢�옒�뒪 �쁺�뿭 �걹