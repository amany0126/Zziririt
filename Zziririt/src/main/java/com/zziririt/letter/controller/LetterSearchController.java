package com.zziririt.letter.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.letter.model.service.LetterService;
import com.zziririt.letter.model.vo.Letter;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class LetterSearchController
 */
@WebServlet("/letterSearch.lo")
public class LetterSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterSearchController() {
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
		
		int listCount; 
		int currentPage;  
						 
		int pageLimit; 
					   
		int boardLimit; 
						
						
		
		int maxPage;
					 
		int startPage; 
		int endPage; 
		
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		int letterStatus = Integer.parseInt(request.getParameter("letterStatus"));
		String keyword = request.getParameter("keyword");
		
		int[] listCounts = new LetterService().selectListCounts(userId);
		
		int listCountRe = listCounts[0];
		int listCountSe = listCounts[1];
		int listCountSa = listCounts[2];
		
		
		listCount = new LetterService().keywordListCount(letterStatus ,userId, keyword);
		
		currentPage 
			= Integer.parseInt(request.getParameter("currentPage"));
		
		pageLimit = 5;
		
		boardLimit = 10;
		
		maxPage 
			= (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage,
								   pageLimit, boardLimit,
								   maxPage, startPage,
								   endPage);
	
		ArrayList<Letter> list 
			= new LetterService().selectKeywordList(pi, letterStatus, userId, keyword);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("letterStatus", letterStatus);
		request.setAttribute("keyword", keyword);
		request.setAttribute("listCountRe", listCountRe);
		request.setAttribute("listCountSe", listCountSe);
		request.setAttribute("listCountSa", listCountSa);
		request.setAttribute("listCount", listCount);
		
		if(letterStatus==1) {
			request
				.getRequestDispatcher("views/letter/receiveSearchListView.jsp")
				.forward(request, response);
		} else if(letterStatus==2) {
			request
				.getRequestDispatcher("views/letter/sendSearchListView.jsp")
				.forward(request, response);
		} else if(letterStatus==4){
			request
				.getRequestDispatcher("views/letter/saveSearchListView.jsp")
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
