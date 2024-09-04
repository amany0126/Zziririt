package com.zziririt.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.Group;
import com.zziririt.member.model.vo.Member;



/**
 * Servlet implementation class NoticeEnrollFormController
 */
@WebServlet("/enrollForm.gr")
public class GroupEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");
	
			   // 세션에서 로그인 사용자 정보 가져오기
		    HttpSession session = request.getSession();
		    Object loginUser = session.getAttribute("loginUser");
		   
		    String UserId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		    
		    ArrayList<Group> list = new GroupService().searchUserGroup(UserId); 
		    
		    request.setAttribute("loginUser", loginUser);
		    request.setAttribute("list", list);
		    
			// 응답이 이미 커밋되었는지 확인
		    if (response.isCommitted()) {
		        // 이미 커밋된 경우에는 더이상 forward를 수행할 수 없으므로 오류 메시지 출력
		        response.getWriter().write("응답이 이미 커밋되었습니다. forward를 수행할 수 없습니다.");
		    } else {
		        // 응답이 커밋되지 않은 경우에만 forward 수행
		    	
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/group/groupEnrollForm.jsp");
		        dispatcher.forward(request, response);
		    }
		}
			// 로그인 후
			
			// 일반게시판 작성하기 페이지 에서
			// 보여질 모든 카테고리 정보들을 전체 조회해오기
			//ArrayList<Category> list 
				//= new BoardService().selectCategoryList();
			
			//request.setAttribute("list", list);
			
			// 일반게시판 작성하기 페이지 포워딩
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
