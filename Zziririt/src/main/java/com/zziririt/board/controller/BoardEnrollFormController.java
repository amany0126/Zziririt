package com.zziririt.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.board.model.service.BoardService;
import com.zziririt.board.model.vo.Category;

/**
 * Servlet implementation class BoardEnrollFormController
 */
@WebServlet("/enrollForm.bo")
public class BoardEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인한 사용자만 보여질 수 있게끔 (권한 체크)
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			// 로그인 전
			// 알람 문구 담아서 메인페이지로 url 재요청
			session.setAttribute("alertMsg", 
								 "로그인한 사용자만 이용 가능한 서비스입니다.");
		
			response.sendRedirect(request.getContextPath());
		} else {
			// 로그인 후
			// 모든 카테고리 정보들 전체 조회
			ArrayList<Category> list 
				= new BoardService().selectCategoryList();
			// VO 생성! > 하지만 board 에서만 쓰일 것이기 때문에 
			// board의 VO 패키지의 하위에 생성!!
			
			request.setAttribute("list", list);
			
			// 일반게시판 작성하기 페이지 포워딩
			request.getRequestDispatcher("views/board/boardWrite.jsp")
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