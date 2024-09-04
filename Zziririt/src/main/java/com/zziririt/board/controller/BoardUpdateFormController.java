package com.zziririt.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.board.model.service.BoardService;
import com.zziririt.board.model.vo.Board;
import com.zziririt.board.model.vo.Category;
import com.zziririt.group.model.vo.Attachment;

/**
 * Servlet implementation class BoardUpdateFormController
 */
@WebServlet("/updateForm.bo")
public class BoardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 수정페이지에서 기존의 내용물들이 먼저 보여져야 하므로
		// 조회해올 것
		
		// 글번호 먼저 뽑기 (bno)
		int boardNo 
			= Integer.parseInt(request.getParameter("bno"));
		
		BoardService bService = new BoardService();
		
		// 카테고리 리스트 먼저 조회
		ArrayList<Category> list 
			= bService.selectCategoryList();
		
		// 해당 게시글 정보 조회
		Board b = bService.selectBoard(boardNo);
		
		request.setAttribute("list", list);
		request.setAttribute("b", b);
		
		// 일반게시글 수정 페이지로 포워딩
		request.getRequestDispatcher("views/board/boardUpdate.jsp")
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