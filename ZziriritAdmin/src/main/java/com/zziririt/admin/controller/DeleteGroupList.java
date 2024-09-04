package com.zziririt.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.Group;
import com.zziririt.admin.model.vo.RegularUser;

/**
 * Servlet implementation class DeleteGroupList
 */
@WebServlet("/delete.gl")
public class DeleteGroupList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGroupList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int groupNo= Integer.parseInt(request.getParameter("groupNo"));
		int status=  Integer.parseInt(request.getParameter("status"));
		// System.out.println(userNo);
		// System.out.println(status);
		
		Group gr = new Group();
		gr.setGroupNo(groupNo);
		gr.setStatus(status);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteGroupList(gr);
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.gl?currentPage=1");
			
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
