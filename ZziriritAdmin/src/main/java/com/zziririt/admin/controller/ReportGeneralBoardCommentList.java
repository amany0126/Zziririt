package com.zziririt.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.GeneralBulletinBoard;

/**
 * Servlet implementation class ReportGeneralBoardCommentList
 */
@WebServlet("/report.gbbr")
public class ReportGeneralBoardCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGeneralBoardCommentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String userNo=request.getParameter("userNo");
		int CommonBoardNo= Integer.parseInt(request.getParameter("CommonBoardNo"));
		String board_title= request.getParameter("board_title");
		
		String action_result= request.getParameter("action_result");
		int groupMemNoStatus=  Integer.parseInt(request.getParameter("user_status"));
		int status=  Integer.parseInt(request.getParameter("board_status"));
		
		/*
		 * System.out.println(commentNo); System.out.println(comment);
		 * System.out.println(status); System.out.println(groupMemNo);
		 * System.out.println(groupMemNoStatus); System.out.println(boardTitle);
		 * System.out.println(action_result);
		 */
		
		
		
		// System.out.println(commentNo);
		// System.out.println(status);
		
		String userId = new AdminService().checkUserId(userNo);
		
		
		GeneralBulletinBoard gbb = new GeneralBulletinBoard();
		
		gbb.setBoardWriter(userNo);
		gbb.setBoardNo(CommonBoardNo);
		
		gbb.setBoardTitle(board_title);
		
		gbb.setGroupMemNoStatus(groupMemNoStatus);
		gbb.setStatus(status);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteCommonBoard(gbb,action_result,userNo );
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.gbbr?currentPage=1");
			
		} else {
			
			

			response.sendRedirect(request.getContextPath()+"/errorPage");	
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
