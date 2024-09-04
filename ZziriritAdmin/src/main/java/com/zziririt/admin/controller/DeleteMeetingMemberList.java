package com.zziririt.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.Meeting;
import com.zziririt.admin.model.vo.MeetingMember;

/**
 * Servlet implementation class DeleteMeetingMemberList
 */
@WebServlet("/delete.mtm")
public class DeleteMeetingMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMeetingMemberList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String meetingNo= request.getParameter("MEETING_NO");
		String userId= request.getParameter("userId");
		String userNo= request.getParameter("userNo");
		int status=  Integer.parseInt(request.getParameter("status"));
		int mtm_userStatus=  Integer.parseInt(request.getParameter("mtm_userStatus"));
		// System.out.println(userNo);
		// System.out.println(status);
		
		MeetingMember mm = new MeetingMember();
		mm.setMeetingNo(meetingNo);
		mm.setGroupMemId(userId);
		mm.setGroupMemNo(userNo);
		mm.setStatus(status);
		mm.setGroupMemNoStatus(mtm_userStatus);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteMeetingMember(mm);
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.mtm?currentPage=1");
			
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
