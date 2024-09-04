package com.zziririt.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.GroupMember;

/**
 * Servlet implementation class DeleteGroupMemberList
 */
@WebServlet("/delete.gml")
public class UpdateGroupMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGroupMemberList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int groupMenNo= Integer.parseInt(request.getParameter("groupMenNo"));
		int groupMenType=  Integer.parseInt(request.getParameter("rank"));
		int status=  Integer.parseInt(request.getParameter("status"));
		
		GroupMember gm = new GroupMember();
		gm.setGroupMenNo(groupMenNo);
		gm.setGroupMenType(groupMenType);
		gm.setStatus(status);
		
		int result = new AdminService().updateGroupMemberList(gm);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/list.gml?currentPage=1");
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
