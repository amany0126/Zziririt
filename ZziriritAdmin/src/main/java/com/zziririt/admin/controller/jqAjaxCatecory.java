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
 * Servlet implementation class jqAjaxCatecory
 */
@WebServlet("/category")
public class jqAjaxCatecory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jqAjaxCatecory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String category1 = "예술/문화";
		String category2 = "운동";
		String category3 = "음식";
		String category4 = "스터디";
		String category5 = "기타";

		int category1Count = new AdminService().category1(category1);
		int category2Count = new AdminService().category2(category2);
		int category3Count = new AdminService().category3(category3);
		int category4Count = new AdminService().category4(category4);
		int category5Count = new AdminService().category5(category5);
		
		
		
//		System.out.println(category1Count);
//		System.out.println(category2Count);
//		System.out.println(category3Count);
//		System.out.println(category4Count);
//		System.out.println(category5Count);
		/*
		 * System.out.println(commonBoard); System.out.println(commonComent);
		 * System.out.println(groupBoard); System.out.println(groupComent);
		 */
		
		
			
		ArrayList<Integer> list = new ArrayList<>();
		list.add(category1Count);
		list.add(category2Count);
		list.add(category3Count);
		list.add(category4Count);
		list.add(category5Count);
		
		
		
		
		
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
