package com.zziririt.group.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.GroupBoard;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.gr")
public class GroupUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
	
		
		
		int groupNo = Integer.parseInt(request.getParameter("gno"));
		
		String writeTime = new GroupService().selectTime(groupNo);
				
		GroupBoard gb = new GroupBoard();
		
	    // 이전 게시글 정보 가져오기
	    
		gb.setGroupMemNo(userId);
	    gb.setBoardNo(groupNo);
		gb.setBoardTitle(boardTitle);
		gb.setBoardContent(boardContent);
		gb.setWriteTime(writeTime);

		

			int result 
				= new GroupService().updateGroup(gb);
			
			
			// 결과에 따른 응답페이지 처리
			if(result > 0) { // 성공
				
				
				
				// 해당 게시글 상세보기 url 재요청
				request.getSession()
						.setAttribute("alertMsg", 
									  "성공적으로 게시글이 수정되었습니다.");
				
				request.setAttribute("userId", userId);
				request.setAttribute("gb", gb);
				
				 request.getRequestDispatcher("views/group/groupDetail.jsp")
					.forward(request, response);
				
			} else { // 실패
				
				// 에러문구를 담아서 에러페이지로 포워딩
				request.setAttribute("errorMsg", 
									 "게시글 수정에 실패했습니다.");
				
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
