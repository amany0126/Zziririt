package com.zziririt.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;
import com.zziririt.admin.model.vo.RegularUser;

/**
 * Servlet implementation class DeleteReularUser
 */
@WebServlet("/delete.rul")
public class DeleteReularUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReularUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userNo= Integer.parseInt(request.getParameter("userNo"));
		int status=  Integer.parseInt(request.getParameter("status"));
		// System.out.println(userNo);
		// System.out.println(status);
		
		RegularUser user = new RegularUser();
		user.setUserNo(userNo);
		user.setStatus(status);
		
		// System.out.println(user);
		
		int result = new AdminService().deleteReularUser(user);
		
		// System.out.println(result);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath()+"/list.rul?currentPage=1");
			
		} else {
			
			

			response.sendRedirect(request.getContextPath()+"/errorPage");			
		}
		
		/*
		if(status == 0) {
			
			int result = new AdminService().DeleteReularUser(userNo);
			response.sendRedirect(request.getContextPath()+"/list.rul?currentPage=1"); 
		}
		*/
		 
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
