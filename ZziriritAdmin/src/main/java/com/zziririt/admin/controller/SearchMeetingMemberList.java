package com.zziririt.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.Meeting;
import com.zziririt.admin.model.vo.MeetingMember;
import com.zziririt.common.model.vo.PageInfo;

/**
 * Servlet implementation class SearchMeetingMemberList
 */
@WebServlet("/search.mtm")
public class SearchMeetingMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMeetingMemberList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

String search= request.getParameter("search");
		
		ArrayList<String> categoryName = new AdminService().categoryList();
		
		
		if(search == null) {
			search = " ";
		}
		// System.out.println(searchUser);
		
		int listCount; // 현재 총 게시글 갯수
		int currentPage; // 현재 페이지 (즉, 사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징 바의 최대 갯수
		int boardLimit; // 한 페이지의 보여질 게시글의 최대 갯수 (즉, 몇개의 게시글이 보여질 것인가)
		
		int maxPage; // 가장 마지막 페이지가 몇번페이지인지 (즉, 총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징 바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징 바의 끝수
		
		listCount =  new AdminService().searchSelectMeetingMemberListCount(search);
		
		// System.out.println(listCount);
		

		currentPage = Integer.parseInt(request.getParameter("currentPage")); 
		pageLimit= 10;
		boardLimit= 10;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit +1;
		endPage = startPage + pageLimit -1;
		
		if(endPage>maxPage) {
			endPage=maxPage;
		}
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<MeetingMember> list =  new AdminService().searchSelectMeetingMemberList(pi, search);
		
		 if(search == " ") {
		
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		RequestDispatcher view = request.getRequestDispatcher("views/admin/meetingMember.jsp");
		view.forward(request, response);
		}else {
			
			request.setAttribute("search", search);
			request.setAttribute("categoryName", categoryName);
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);
			
			RequestDispatcher view = request.getRequestDispatcher("views/admin/meetingMember.jsp");
			view.forward(request, response);
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
