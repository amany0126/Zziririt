package com.zziririt.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.Meeting;
import com.zziririt.admin.model.vo.RegularUser;

/**
 * Servlet implementation class DeleteMeetingList
 */
@WebServlet("/delete.mt")
public class DeleteMeetingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMeetingList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		int meetingNo= Integer.parseInt(request.getParameter("meetingNo"));
		int status=  Integer.parseInt(request.getParameter("status"));
		// System.out.println(userNo);
		// System.out.println(status);
		
		Meeting m = new Meeting();
		m.setMeetingNo(meetingNo);
		m.setStatus(status);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteMeeting(m);
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.mt?currentPage=1");
			
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
