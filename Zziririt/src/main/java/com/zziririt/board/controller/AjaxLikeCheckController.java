package com.zziririt.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.board.model.service.BoardService;
import com.zziririt.board.model.vo.Like;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class AjaxLikeCheckController
 */
@WebServlet("/likeCheck.bo")
public class AjaxLikeCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxLikeCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// loginUser가 null이 아닌 경우에만 실행
		if(request.getSession().getAttribute("loginUser") != null) {
		    // 1) 요청값 뽑기
		    int boardNo = Integer.parseInt(request.getParameter("bno"));
		    
		    // loginUser에서 userNo 가져오기
		    int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		    
		    // 2) VO 객체로 가공하기
		    Like l = new Like(userNo, boardNo);
		    
		    // 3) 가공한 객체를 매개변수로 넘기며 서비스 호출&결과값 담을 변수 생성
		    int checkResult = new BoardService().likeCheck(l);
		    
		    // 4) AJAX 처리를 위한 인코딩 설정
		    response.setContentType("text/html; charset=UTF-8");
		    response.getWriter().print(checkResult);
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