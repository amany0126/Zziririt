package com.zziririt.member.model.service;

import java.sql.Connection;

import com.zziririt.common.JDBC;
import com.zziririt.member.model.dao.MemberDao;
import com.zziririt.member.model.vo.Member;

public class MemberService { // MemberService �겢�옒�뒪 �쁺�뿭 �떆�옉

	// �쉶�썝媛��엯�슜 �꽌鍮꾩뒪
	public int insertMember(Member m) { // insertMember 硫붿꽌�뱶 �쁺�뿭 �떆�옉
		// 1) Connection 媛앹껜 �깮�꽦
		Connection conn = JDBC.getConnection();

		// 2) Connection 媛앹껜�� Member 媛앹껜瑜� 媛곴컖 DAO 濡� �꽆寃⑥＜湲�
		int result = new MemberDao().insertMember(conn, m);

		// 3) �듃�옖�옲�뀡 泥섎━
		if (result > 0) {
			JDBC.commit(conn);
		} else {
			JDBC.rollback(conn);
		}

		// 4) Connection 媛앹껜 諛섎궔
		JDBC.close(conn);

		// 5) �꽌釉붾┸(而⑦듃濡ㅻ윭)�쑝濡� 寃곌낵媛� 諛섑솚
		return result;
	} // insertMember 硫붿꽌�뱶 �쁺�뿭 �걹

	// �땳�꽕�엫 以묐났泥댄겕�슜 �꽌鍮꾩뒪
	public int nicknameCheck(String checkNickname) { // nicknameCheck 硫붿꽌�뱶 �쁺�뿭 �떆�옉
		// 1) Connection 媛앹껜 �깮�꽦
		Connection conn = JDBC.getConnection();

		// 2) Connection 媛앹껜�� 泥댄겕�븷 �땳�꽕�엫�쓣 媛곴컖 DAO 濡� �꽆寃⑥＜湲�
		int count = new MemberDao().nicknameCheck(conn, checkNickname);

		// 3) �듃�옖�옲�뀡 泥섎━ (SELECT臾몄씠�씪 �뙣�뒪)

		// 4) Connection 媛앹껜 諛섎궔
		JDBC.close(conn);

		// 5) �꽌釉붾┸(而⑦듃濡ㅻ윭)�쑝濡� 寃곌낵媛� 諛섑솚
		return count;
	} // nicknameCheck 硫붿꽌�뱶 �쁺�뿭 �걹

	// �븘�씠�뵒 以묐났泥댄겕�슜 �꽌鍮꾩뒪
	public int idCheck(String checkId) { // idCheck 硫붿꽌�뱶 �쁺�뿭 �떆�옉
		// 1) Connection 媛앹껜 �깮�꽦
		Connection conn = JDBC.getConnection();

		// 2) Connection 媛앹껜�� 泥댄겕�븷 �븘�씠�뵒瑜� 媛곴컖 DAO 濡� �꽆寃⑥＜湲�
		int count = new MemberDao().idCheck(conn, checkId);

		// 3) �듃�옖�옲�뀡 泥섎━ (SELECT臾몄씠�씪 �뙣�뒪)

		// 4) Connection 媛앹껜 諛섎궔
		JDBC.close(conn);

		// 5) �꽌釉붾┸(而⑦듃濡ㅻ윭)�쑝濡� 寃곌낵媛� 諛섑솚
		return count;
	} // idCheck 硫붿꽌�뱶 �쁺�뿭 �걹

	public Member loginMember(Member m) {

		// 1)
		Connection conn = JDBC.getConnection();

		// 2)
		Member loginUser = new MemberDao().loginMember(conn, m);

		// 3) 패스

		// 4)
		JDBC.close(conn);

		// 5)
		return loginUser;

	}
} // MemberService �겢�옒�뒪 �쁺�뿭 �걹