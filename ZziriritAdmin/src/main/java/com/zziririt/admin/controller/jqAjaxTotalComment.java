package com.zziririt.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.admin.model.service.AdminService;

/**
 * Servlet implementation class jqAjaxTotalComment
 */
@WebServlet("/TotalComment")
public class jqAjaxTotalComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jqAjaxTotalComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int GroupComment = new AdminService().selectGroupCommentReportListCount();
		int CommonComment = new AdminService().selectCommonCommentReportListCount();
		
			
		int TotalComment = GroupComment + CommonComment;
		
		response.setContentType("text/html ; charset=UTF-8");
		
		// 2. 요청했었던 jsp 파일과의 출력통로를 열어주기
		PrintWriter out = response.getWriter();
		
		// 3. 만들어진 출력통로로 응답데이터 넘기기
		out.print(TotalComment);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
