package com.zziririt.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.board.model.service.BoardService;
import com.zziririt.board.model.vo.Reply;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.bo")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// AJAX 전송 방식이므로 인코딩 생략 가능
		
		// 요청값 뽑기
		String replyContent 
			= request.getParameter("replyContent"); // 댓글 내용
		int boardNo 
			= Integer.parseInt(request.getParameter("bno")); // 댓글이 달린 게시글 번호
		
		// 세션에서 현재 로그인한 사용자 정보 가져오기
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("loginUser");
		String userNo = m.getUserNo() + ""; // 회원번호를 문자열형으로 변환
		
		// VO 객체로 가공
		Reply r = new Reply();
		r.setReplyContent(replyContent);
		r.setRefBoardNo(boardNo);
		r.setReplyWriter(userNo);
		
		// 서비스로 요청 후 결과 받기
		int result = new BoardService().insertReply(r);
		
		// 위의 결과값으로 응답
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}