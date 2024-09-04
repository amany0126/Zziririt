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
 * Servlet implementation class jqAjaxTotalBoard
 */
@WebServlet("/TotalBoard")
public class jqAjaxTotalBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jqAjaxTotalBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int GroupBoard = new AdminService().SelectGroupBoardReportListCount();
		int CommonBoard = new AdminService().SelectGeneralBulletinBoardReportListCount();
		
			
		int TotalBoard = GroupBoard + CommonBoard;
		
		// 응답데이터를 반환하기
		// 1. 혹시라도 응답데이터에 한글이 있을경우를 대비해서
		//	  응답데이터에 대한 현식과 인코딩 설정을 지정
		response.setContentType("text/html ; charset=UTF-8");
		
		// 2. 요청했었던 jsp 파일과의 출력통로를 열어주기
		PrintWriter out = response.getWriter();
		
		// 3. 만들어진 출력통로로 응답데이터 넘기기
		out.print(TotalBoard);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
