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
 * Servlet implementation class LetterDetailController
 */
@WebServlet("/letterDetail.lo")
public class LetterDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterDetailController() {
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
		
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId(); // �꽭�뀡�쑝濡쒕��꽣 �뼸�뼱�궪 濡쒓렇�씤 �쑀�� �븘�씠�뵒
		int letterStatus = Integer.parseInt(request.getParameter("letterStatus"));
		int letterNo = Integer.parseInt(request.getParameter("letterNo")); // 履쎌� �겢由��뻽�쓣 �븣 �꽆�뼱�삱 �꽆踰�
		
		int result = new LetterService().increaseCount(letterNo); // 履쎌� �뱾�뼱媛� �슏�닔媛� 利앷��뻽�뒗吏� 泥댄겕
		
		if(result>0) {
			
			Letter l = new LetterService().selectLetter(letterNo);
			
			if(letterStatus!=4) {
			
				if((l.getUserReceiver().equals(userId))) { // 履쎌� 諛쏆� �궗�엺怨� 濡쒓렇�씤 �쑀��媛� 媛숆퀬, �뱾�뼱媛� �슏�닔媛� 1�쉶�씪硫�, �씫�� �떆媛꾩쓣 ���옣 // ���옣 �썑 �떎�떆 遺덈윭���꽌 �솕硫댁뿉 異쒕젰
					
					if(l.getCount()==1) {
						new LetterService().setReadTime(letterNo);
						
						l = new LetterService().selectLetter(letterNo);
						
						request.setAttribute("l", l);
						request.setAttribute("letterStatus", letterStatus);
					
						// �룷�썙�뵫
						request.getRequestDispatcher("views/letter/letterDetailRView.jsp")
								.forward(request, response);
					} else {
						
						request.setAttribute("l", l);
						request.setAttribute("letterStatus", letterStatus);
						
						// �룷�썙�뵫
						request.getRequestDispatcher("views/letter/letterDetailRView.jsp")
								.forward(request, response);
					}
					
				} else {
					
					
					request.setAttribute("l", l);
					request.setAttribute("letterStatus", letterStatus);
					
					// �룷�썙�뵫
					request.getRequestDispatcher("views/letter/letterDetailSView.jsp")
								.forward(request, response);
				}
				
			} else {
		
				
				request.setAttribute("l", l);
				request.setAttribute("letterStatus", letterStatus);
					
				// �룷�썙�뵫
				request.getRequestDispatcher("views/letter/saveSendingForm.jsp")
						.forward(request, response);
			}
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
