package com.zziririt.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/delete.bo")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 글번호 먼저 뽑기 (bno)
		int boardNo 
			= Integer.parseInt(request.getParameter("bno"));
		
		// 서비스로 삭제 요청 후 결과 받기
		int result 
			= new BoardService().deleteBoard(boardNo);
		
		// 결과에 따른 응답페이지 처리
		if(result > 0) { // 성공
			// list.bo 로 url 재요청
			request.getSession()
					.setAttribute("alertMsg", 
							      "성공적으로 일반게시글이 삭제되었습니다.");
			
			response.sendRedirect(request.getContextPath() 
									+ "/list.bo?currentPage=1");
		} else { // 실패
			// 에러 문구를 담아서 에러페이지로 포워딩
			request.setAttribute("errorMsg", 
								 "게시글 삭제에 실패했습니다.");
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