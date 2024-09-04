package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.Group;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class GroupView
 */
@WebServlet("/group/view")
public class GroupView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 필요한 거 그룹정보 g(무조건) memberList/ 유저 등급정보(로그인시)
		String gno = request.getParameter("gno");
		
		//그룹에 대한 무조건 필요한 정보
		Group g = new GroupService().getGroupViewNormal(gno); 
		Member m = (Member)request.getSession().getAttribute("loginUser");
		int userType = 0;
		//유저가 로그인시 등급확인 3 방장 1 가입 0 미가입
		if(m!=null) {
			userType = new GroupService().getUserType(m.getUserNo(),Integer.parseInt(gno));
		}
		request.setAttribute("groupInfo", g);
		request.setAttribute("userType", userType);
		request.setAttribute("gno", gno);
		request.getRequestDispatcher("/views/group/groupinfo.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
