package com.zziririt.group.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;

/**
 * Servlet implementation class WishAjax
 */
@WebServlet("/wish")
public class WishAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String gno = request.getParameter("gno");
		String userNo = request.getParameter("userNo");
		response.setContentType("text/html; charset=UTF-8");
		int check = new GroupService().checkWish(gno,userNo);
		if( check>0) { // ����
			if(new GroupService().removeWish(gno, userNo)>0)response.getWriter().print("remove");
			else response.getWriter().print("fail");
		}else { // �߰�
			if(new GroupService().addWish(gno,userNo)>0)response.getWriter().print("add");
			else response.getWriter().print("fail");
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
