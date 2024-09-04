package com.zziririt.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.Group;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class GroupCareController
 */
@WebServlet("/group/care")
public class GroupCareController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCareController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member m =  ((Member)request.getSession().getAttribute("loginUser"));
		if(m==null) {
			response.sendRedirect("/Zziririt");
			return ;
		}
		int userNo = m.getUserNo();
		ArrayList<Group> joinList = new GroupService().getJoinGroupList(userNo);
		ArrayList<Group> wishList = new GroupService().getWishGroupList(userNo);
		request.setAttribute("joinList", joinList);
		request.setAttribute("wishList", wishList);
		request.getRequestDispatcher("/views/group/groupcare.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
