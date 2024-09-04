package com.zziririt.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.GroupComment;

/**
 * Servlet implementation class ReportGroupBoardCommentList
 */
@WebServlet("/report.gbbcrl")
public class ReportGroupBoardCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGroupBoardCommentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int commentNo= Integer.parseInt(request.getParameter("Comment"));
		
		
		
		String comment= request.getParameter("board_comment");
		
		int status=  Integer.parseInt(request.getParameter("comment_status"));
		
		String groupMemNo= request.getParameter("userId");
		int groupMemNoStatus=  Integer.parseInt(request.getParameter("user_status"));
		String boardTitle= request.getParameter("board_comment_title");
		
		String action_result= request.getParameter("action_result");
		
		/*
		 * System.out.println(commentNo); System.out.println(comment);
		 * System.out.println(status); System.out.println(groupMemNo);
		 * System.out.println(groupMemNoStatus); System.out.println(boardTitle);
		 * System.out.println(action_result);
		 */
		
		
		
		// System.out.println(commentNo);
		// System.out.println(status);
		
		/*String userId = new AdminService().checkUserId(userNo);*/
		
		
		GroupComment gc = new GroupComment();
		
		gc.setCommentNo(commentNo);
		gc.setComment(comment);
		
		gc.setStatus(status);
		gc.setGroupMemNo(groupMemNo);
		
		gc.setGroupMemNoStatus(groupMemNoStatus);
		gc.setBoardTitle(boardTitle);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteGroupBoardComment(gc,action_result );
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.gbbcrl?currentPage=1");
			
		} else {
			
			

			response.sendRedirect(request.getContextPath()+"/errorPage");			
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
