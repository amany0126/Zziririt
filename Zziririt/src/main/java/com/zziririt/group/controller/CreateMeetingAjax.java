package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.Meeting;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class CreateMeetingAjax
 */
@WebServlet("/CreateMeeting")
public class CreateMeetingAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMeetingAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		Meeting m = new Meeting();
		System.out.println(request.getParameter("gno")+"@@@@@@@@@@@@@@@@@");
		m.setMeetingName(request.getParameter("meetingName"));
		m.setMeetingSpot(request.getParameter("meetingArea"));
		m.setMeetingYear(request.getParameter("gno"));//year 재활용
		m.setMeetingMonth(request.getParameter("meetingDay")+" "+request.getParameter("meetingTime"));//달 재활용
		m.setMeetingContent(request.getParameter("meetingDescript"));
		m.setMeetingLimit(Integer.parseInt(request.getParameter("meetingLimit")));
		m.setCreateUser(((Member)request.getSession().getAttribute("loginUser")).getUserNo());
		
		int result = new GroupService().createMeeting(m);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
