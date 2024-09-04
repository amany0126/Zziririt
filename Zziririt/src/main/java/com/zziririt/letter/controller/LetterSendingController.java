package com.zziririt.letter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.letter.model.service.LetterService;
import com.zziririt.letter.model.vo.Letter;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class LetterSending
 */
@WebServlet("/letterSending.lo")
public class LetterSendingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterSendingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			// 로그인 전
			
			// 알람문구 담아서 메인페이지로 url 재요청
			session.setAttribute("alertMsg", 
								 "로그인한 사용자만 이용 가능한 서비스입니다.");
			
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		
		// 쪽지 제목 (letterTitle)
		String letterTitle
			= request.getParameter("letterTitle");
		
		// 글 내용 (letterContent)
		String letterContent
			= request.getParameter("letterContent");
		
		// 발신자 (userSender)
		String userSender = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		// 발신자 (userSender)
		String userReceiver 
		= request.getParameter("userReceiver");
		
		Letter l = new Letter();
		l.setLetterTitle(letterTitle);
		l.setLetterContent(letterContent);
		l.setUserSender(userSender);
		l.setUserReceiver(userReceiver);
		
		int cs = new LetterService().checkUserStatus(userReceiver);
		
		System.out.println(cs);

		if(cs == 1) {
			
			if(request.getParameter("letterNo").equals("")) {
				
				int result = new LetterService().letterSending(l);
				
				// 5. 결과에 따른 응답페이지 처리
				if(result > 0) { // 성공
					
					// sendList.lo?currentPage=1 로 url 재요청
					// (방금 쓴 쪽지가 가장 최신 쪽지이므로)
					
					request.getSession()
							.setAttribute("alertMsg", 
										  "쪽지 보내기에 성공했습니다.");
					
					response.sendRedirect(request.getContextPath()
											+ "/letterList.lo?currentPage=1&letterStatus=2");
				} else {
						request.setAttribute("errorMsg", 
						 "쪽지 보내기에 실패했습니다.");
				
						request
						.getRequestDispatcher("views/common/errorPage.jsp")
						.forward(request, response);
				}
			} else {
				
				int letterNo = Integer.parseInt(request.getParameter("letterNo"));
				int result = new LetterService().letterSaveSending(letterNo, l);
				
				if(result > 0) { // 성공
					
					// sendList.lo?currentPage=1 로 url 재요청
					// (방금 쓴 쪽지가 가장 최신 쪽지이므로)
					
					request.getSession()
							.setAttribute("alertMsg", 
										  "쪽지 보내기에 성공했습니다.");
					
					response.sendRedirect(request.getContextPath()
											+ "/letterList.lo?currentPage=1&letterStatus=2");
				} else {
						request.setAttribute("errorMsg", 
						 "쪽지 보내기에 실패했습니다.");
				
						request
						.getRequestDispatcher("views/common/errorPage.jsp")
						.forward(request, response);
				}
				
			}
		} else {
			

			request.setAttribute("l", l);
				
			
			request.getSession()
			.setAttribute("alertMsg", 
						  "존재하지 않는 아이디입니다.");
	
			request.getRequestDispatcher("views/letter/letterReEnrollForm.jsp")
			.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
