package com.zziririt.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.Group;
import com.zziririt.admin.model.vo.GroupBoard;

/**
 * Servlet implementation class ReportGroupBoardList
 */
@WebServlet("/report.gbbrl")
public class ReportGroupBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGroupBoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userNo = request.getParameter("userNo");
		
	
		String board_title= request.getParameter("board_title");
		String action_result= request.getParameter("action_result");
		
		
		int bno=  Integer.parseInt(request.getParameter("groupBoardNo"));
		int user_status=  Integer.parseInt(request.getParameter("user_status"));
		int board_status=  Integer.parseInt(request.getParameter("board_status"));
		// System.out.println(userNo);
		// System.out.println(status);
		
		// String userId = new AdminService().checkUserId(userNo);
		
		
	
		
		GroupBoard gb = new GroupBoard();
	
		gb.setGroupMemNo(userNo);
		gb.setGroupMemNoStatus(user_status);
		gb.setStatus(board_status);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteGroupBoard(gb,board_title,action_result,bno,userNo );
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.gbbrl?currentPage=1");
			
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
