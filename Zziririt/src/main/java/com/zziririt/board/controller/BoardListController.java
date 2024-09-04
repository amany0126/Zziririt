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
import com.zziririt.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징 처리
		int listCount; // 현재 총 게시글 갯수
		int currentPage; // 현재 페이지 
						 // (즉, 사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의
					   // 페이지 최대 갯수
		int boardLimit; // 한 페이지에 보여질 게시글의
						// 최대 갯수
						// (즉, 몇개 단위씩 게시글이 보여질건지)
		int maxPage; // 가장 마지막 페이지가 몇번페이지인지
		 			 // (즉, 총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글 갯수
		// > 일반게시글 중에서도 삭제 안된 것들의 갯수만!!
		listCount = new BoardService().selectListCount();
		
		// * currentPage : 현재페이지 (즉, 사용자가 요청한 페이지)
		currentPage 
			= Integer.parseInt(request.getParameter("currentPage"));
		
		// * pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		//				 (한 페이지 당 페이징 바 숫자 갯수)
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대 갯수
		//				  (한 페이지 당 몇개 단위씩 보여질거냐)
		boardLimit = 15;
		
		maxPage 
			= (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 위의 7개의 변수를 하나의 VO (PageInfo) 로 가공
		PageInfo pi = new PageInfo(listCount, currentPage,
								   pageLimit, boardLimit,
								   maxPage, startPage,
								   endPage);

		// pi 를 서비스단으로 넘기면서 요청 후 결과 받기
		ArrayList<Board> list 
			= new BoardService().selectList(pi);
		
		// 화면에 보여질 게시글 목록과
		// 화면에 보여질 페이지 정보를 응답데이터로 넘기기
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		// 일반게시판 게시글 목록 화면으로 포워딩
		request
			.getRequestDispatcher("views/board/boardList.jsp")
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