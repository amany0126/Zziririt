package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.member.model.vo.Member;




/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/delete.gr")
public class GroupDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 그룹 게시글 번호 먼저 뽑기
		int groupNo
			= Integer.parseInt(request.getParameter("gno"));

			
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();

		   GroupService gService = new GroupService();
		   
		   int userNo = gService.selectUserNo(userId);
		   
		   int result = gService.deleteGroupBoard(groupNo,userNo);
		   
		// 결과에 따른 응답페이지 처리
		if(result > 0) { // 성공
			
			// list.gr 로 url 재요청
			request.getSession()
				   .setAttribute("alertMsg", 
						   		 "성공적으로 게시글이 삭제되었습니다.");
			
			response.sendRedirect(request.getContextPath()
								+ "/list.gr?currentPage=1");
			
		} else { // 실패
			
			// 에러문구를 담아서 에러페이지 포워딩
			request.setAttribute("errorMsg", 
								 "게시글 삭제를 실패했습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
									.forward(request, response);
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
