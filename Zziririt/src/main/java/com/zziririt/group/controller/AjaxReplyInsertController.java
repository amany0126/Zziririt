package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.GroupBoard;
import com.zziririt.group.model.vo.GroupComment;
import com.zziririt.member.model.vo.Member;


/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.gr")
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

		// AJAX 의 경우 POST 방식이여도
		// UTF-8 로 요청이 들어옴
		
		
		// 요청 시 전달값 뽑기
		String content = request.getParameter("replyContent");
		//String userNickName = request.getParameter("userNickName");
	
		int boardNo = Integer.parseInt(request.getParameter("gno"));
		 //String userNo = request.getParameter("userNo");
		
	

		// String userNo = request.getParameter("userNo");
		// > 오류 발생하는 방법
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");  
       
		int groupMemNo = loginUser.getUserNo(); // 로그인한 회원의 번호
      
        // String userNo = String.valueOf(m.getUserNo());
		//String userNo = m.getUserNo() + "";
		// > 숫자를 문자열로 바꾸는 방법들
		//System.out.println("로그인한 사용자의 회원 번호: " + groupMemNo);

	
	
        // GroupComment 객체 생성 및 설정
		GroupComment gc = new GroupComment();
		
        gc.setContent(content);
        gc.setBoardNo(boardNo);
        gc.setGroupMemNo(groupMemNo);
		// 서비스로 요청 후 결과 받기
		int result = new GroupService().insertComment(gc);
		
		// 위의 result 결과값을 바로 응답하기
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
