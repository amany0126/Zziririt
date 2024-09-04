package com.zziririt.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.GeneralBulletinComment;

/**
 * Servlet implementation class GeneralBulletinBoardCommentReport
 */
@WebServlet("/report.rguc")
public class GeneralBulletinBoardCommentReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneralBulletinBoardCommentReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int commentNo= Integer.parseInt(request.getParameter("CommentNo"));
		
		
		
		String comment= request.getParameter("common_comment");
		
		int status=  Integer.parseInt(request.getParameter("comment_status"));
		
		String groupMemNo= request.getParameter("userId");
		int groupMemNoStatus=  Integer.parseInt(request.getParameter("user_status"));
		String boardTitle= request.getParameter("common_comment_title");
		
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
		
		
		GeneralBulletinComment gbc = new GeneralBulletinComment();
		
		gbc.setReplyNo(commentNo);
		gbc.setReplyComment(comment);
		
		gbc.setStatus(status);
		gbc.setReplyWriter(groupMemNo);
		
		gbc.setGroupMemNoStatus(groupMemNoStatus);
		gbc.setTitle(boardTitle);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteCommonBoardComment(gbc,action_result );
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.rguc?currentPage=1");
			
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
