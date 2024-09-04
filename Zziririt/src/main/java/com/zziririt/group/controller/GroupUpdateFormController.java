package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.Group;
import com.zziririt.group.model.vo.GroupBoard;


/**
 * Servlet implementation class BoardUpdateFormController
 */
@WebServlet("/updateFrom.gr")
public class GroupUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 수정페이지에서 기존의 내용물들이 먼저 보여져야 하므로
		// 조회해올 것
		request.setCharacterEncoding("UTF-8");

		int groupNo 
			= Integer.parseInt(request.getParameter("gno"));
		
		//System.out.println(groupNo);
		
		GroupService gService = new GroupService();
	
		// 해당 게시글 정보 조회
		GroupBoard gb = gService.selectGroup(groupNo);
		// 게시글번호, 카테고리명, 제목, 내용, 작성자아이디, 작성일
	
		

        request.setAttribute("gb", gb); 
        // 조회된 그룹 정보를 request에 담기


		// 일반게시글 수정 페이지로 포워딩
		request.getRequestDispatcher("views/group/groupUpdateForm.jsp")
								.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
