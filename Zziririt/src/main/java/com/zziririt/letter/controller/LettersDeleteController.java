package com.zziririt.letter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.letter.model.service.LetterService;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class LetterDeleteController
 */
@WebServlet("/lettersDelete.lo")
public class LettersDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LettersDeleteController() {
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
		
		String[] list = request.getParameter("deleteValues").split(",");
		
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		int letterNo=0;
		
		int letterStatus=Integer.parseInt(request.getParameter("letterStatus"));
		
		int upStatusNo=0;
		
		int[] arr = new int[list.length];
		
		if(list.length==1&&list[0]=="") {
			request.getSession()
			.setAttribute("alertMsg", 
					      "선택된 쪽지가 없습니다.");
	
			response.sendRedirect(request.getContextPath() 
							+ "/letterList.lo?currentPage=1&letterStatus="+letterStatus);
		} else {
		
		for(int i=0;i<list.length;i++) {
			
			letterNo=Integer.parseInt(list[i]);
			System.out.println(letterNo);
			System.out.println(userId);
			
			upStatusNo = new LetterService().getUpStatusNo(letterNo, userId);
			System.out.println(upStatusNo);
			int result = new LetterService().letterDelete(letterNo, upStatusNo);
			
			arr[i] = result;
		}
		
		int count = 0;
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==0) {
				count++;
			}
		}
		
		if(count!=0) {
			//String result = String.join(", ", al);
			
			request.setAttribute("errorMsg", 
					 count+"개 쪽지 삭제에 실패했습니다.");

			request.getRequestDispatcher("views/common/errorPage.jsp")
				.forward(request, response);
		} else {
			request.getSession()
			.setAttribute("alertMsg", 
					      "성공적으로 쪽지가 삭제되었습니다.");
	
			response.sendRedirect(request.getContextPath() 
							+ "/letterList.lo?currentPage=1&letterStatus="+letterStatus);
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
