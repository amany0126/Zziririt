package com.zziririt.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.zziririt.admin.model.service.AdminService;

/**
 * Servlet implementation class jqAjaxReport
 */
@WebServlet("/report")
public class jqAjaxReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jqAjaxReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String board = "BOARD";
		String Reply = "REPLY";
		String GroupBoard = "GROUP_BOARD";
		String GroupComment = "GROUP_COMMENT";
				
		int commonBoard = new AdminService().reportCount1(board);
		int commonComent = new AdminService().reportCount2(Reply);
		int groupBoard  = new AdminService().reportCount3(GroupBoard);
		int groupComent = new AdminService().reportCount4(GroupComment);
		
		/*
		 * System.out.println(commonBoard); System.out.println(commonComent);
		 * System.out.println(groupBoard); System.out.println(groupComent);
		 */
		
		
			
		ArrayList<Integer> list = new ArrayList<>();
		list.add(commonBoard);
		list.add(commonComent);
		list.add(groupBoard);
		list.add(groupComent);
		
		
		
		
				// DB 로부터 전체 회원의 정보들을 조회해왔다는 가정 하에
			
				// ArrayList<Member> --> JSON 형식으로 변환
				
				// > 정석대로라면 위와 같이 일일이 다 JSON 형태로 변환해야함
				
				// GSON 을 사용하면 한번에 변환 후 응답데이터로 넘기기까지 가능하다
				response.setContentType("application/json; charset=UTF-8");
				// Gson gson = new Gson();
				// gson.toJson(list, response.getWriter());
				new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
