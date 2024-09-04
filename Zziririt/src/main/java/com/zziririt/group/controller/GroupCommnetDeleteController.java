package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.GroupBoard;
import com.zziririt.group.model.vo.GroupComment;
import com.zziririt.member.model.vo.Member;


/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/deleteComment.gr")
public class GroupCommnetDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCommnetDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		
		// 댓글 번호 가져오기
        int commentNo = Integer.parseInt(request.getParameter("commentNo"));

        // GroupService 객체 생성
        GroupService groupService = new GroupService();
        
  
        // 댓글 삭제 요청
        int result = groupService.deleteComment(commentNo);
    	
   
        // 클라이언트로부터의 요청에 대한 응답을 처리합니다.
        if (result > 0) {
            // 댓글 삭제에 성공했을 경우
            response.getWriter().write("success");
        } else {
            // 댓글 삭제에 실패했을 경우
            response.getWriter().write("failure");
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
