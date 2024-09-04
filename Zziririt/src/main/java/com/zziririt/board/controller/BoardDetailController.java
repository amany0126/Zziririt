package com.zziririt.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.board.model.service.BoardService;
import com.zziririt.board.model.vo.Board;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 게시글 번호 먼저 뽑기 (bno)
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// BoardService 객체가 계속 필요한 상황
		BoardService bService = new BoardService();
		
		// 조회수 증가용 서비스 먼저 다녀오기
		int result = bService.increaseCount(boardNo);
		
		// 조회수 증가에 성공 했다면 게시글, 첨부파일 각각 조회해오기
		if(result > 0) { // 성공
			// 게시글 단일행조회
			Board b = bService.selectBoard(boardNo);
			
			request.setAttribute("b", b);
			
			System.out.println(b);
			
			// 포워딩
			request.getRequestDispatcher("views/board/boardSelect.jsp")
						.forward(request, response);
		} else { // 실패
			
			// 에러문구를 담아서 에러페이지로
			request.setAttribute("errorMsg", 
								 "게시글 상세조회에 실패했습니다.");
			
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