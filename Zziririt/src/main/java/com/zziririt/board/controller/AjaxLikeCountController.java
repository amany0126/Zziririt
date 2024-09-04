package com.zziririt.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.board.model.service.BoardService;

/**
 * Servlet implementation class AjaxLikeCountController
 */
@WebServlet("/lselect.bo")
public class AjaxLikeCountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxLikeCountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 요청값 뽑기
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 2) boardNo를 매개변수로 넘기며 서비스 호출&결과값 담을 변수 생성
		int likeCount = new BoardService().selectLikeCount(boardNo);
		
		// 3) AJAX 처리를 위한 인코딩 설정
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(likeCount);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}