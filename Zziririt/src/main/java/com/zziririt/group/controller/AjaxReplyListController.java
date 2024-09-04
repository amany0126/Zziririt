package com.zziririt.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.GroupComment;
import com.zziririt.member.model.vo.Member;



/**
 * Servlet implementation class AjaxReplyListController
 */
@WebServlet("/rlist.gr")
public class AjaxReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 시 전달값 먼저 뽑기
		// 글번호 (gno)
		
		int groupNo
			= Integer.parseInt(request.getParameter("gno"));
		
		
		
		//System.out.println(boardNo);
		
		//서비스로 요청 후 결과 받기
		ArrayList<GroupComment> list 
			= new GroupService().selectCommentList(groupNo);
	
        

		// 조회해온 list 를 바로 응답데이터로 넘기기
		// > GSON 을 이용할것
		response.setContentType("application/json; charset=UTF-8");
		
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
