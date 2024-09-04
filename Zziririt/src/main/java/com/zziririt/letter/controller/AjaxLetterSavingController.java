package com.zziririt.letter.controller;


/*package com.zziririt.letter.controller;*/
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.zziririt.letter.model.service.LetterService;
import com.zziririt.letter.model.vo.Letter;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class AjaxLetterSavingController
 */
@WebServlet("/letterSaving.lo")
public class AjaxLetterSavingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxLetterSavingController() {
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
		
		String letterTitle
			= request.getParameter("letterTitle");
		
		// 湲� �궡�슜 (letterContent)
		String letterContent
			= request.getParameter("letterContent");
		
		// 諛쒖떊�옄 (userSender)
		String userSender 
			= ((Member)request.getSession().getAttribute("loginUser")).getUserId();; 
		
		// �닔�떊�옄 (userReceiver)
		String saveReceiver 
			= request.getParameter("saveReceiver");
		
		
		Letter l = new Letter();
		l.setLetterTitle(letterTitle);
		l.setLetterContent(letterContent);
		l.setUserSender(userSender);
		l.setUserReceiver(" ");
		l.setSaveReceiver(saveReceiver);
		
		System.out.println(saveReceiver);
		
		response.setContentType("text/html; charset=UTF-8");
		
		if(request.getParameter("letterNo").equals("")) {
				
			new LetterService().letterSaving(l);
			
			//蹂닿��븿 踰덊샇 4, �쑀���븘�씠�뵒 �꽆湲곌퀬, 留� 留덉�留� 以꾩쓽 踰덊샇 媛��졇�삤湲�
			int letterNo = new LetterService().selectFirstLetterNo(4, userSender);
			System.out.println(letterNo);
			response.getWriter().print(letterNo);
			
				
		} else {
			
			int letterNo = Integer.parseInt(request.getParameter("letterNo"));
			
			new LetterService().letterUpSaving(letterNo, l);
		 
			response.getWriter().print(letterNo);
			
			
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
