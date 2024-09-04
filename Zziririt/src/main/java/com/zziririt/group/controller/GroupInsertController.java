package com.zziririt.group.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.common.JDBC;
import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.GroupBoard;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.gr")
public class GroupInsertController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public GroupInsertController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	request.setCharacterEncoding("UTF-8");
        
    	HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        
        String boardTitle = request.getParameter("boardTitle");
        String boardContent = request.getParameter("boardContent");
        int groupNo =Integer.parseInt(request.getParameter("groupList"));
        String userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo() +"";
        
        //int groupNo = Integer.parseInt(request.getParameter("groupNo"));		
        
        String boardWriter = ""+new GroupService().selectUserNo1(userNo);
        
       //  System.out.println(groupNo);
        
        
        GroupBoard gb = new GroupBoard();
        
        gb.setBoardTitle(boardTitle);
        gb.setBoardContent(boardContent);
        gb.setGroupMemNo(boardWriter);
        gb.setGroupNo(groupNo);

        int result = new GroupService().insertGroupBoard(gb);

        if (result > 0) { // 성공
        	
            request.getSession().setAttribute("alertMsg", "게시글 작성에 성공했습니다.");
            response.sendRedirect(request.getContextPath() + "/list.gr?currentPage=1");
        } else { // 실패
        	request.getSession().setAttribute("alertMsg", "게시글 작성에 실패했습니다.");
            response.sendRedirect(request.getContextPath() + "/list.gr?currentPage=1");
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
