package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.GroupBoard;

/**
 * Servlet implementation class GroupDetailController
 */
@WebServlet("/detail.gr")
public class GroupDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	  // 글번호 (gno)
        int groupNo = Integer.parseInt(request.getParameter("gno"));

        GroupService gService = new GroupService();
    	
        // 상세조회 서비스 요청 후 상세조회 페이지 포워딩
        GroupBoard gb = gService.selectGroup(groupNo);
          
        request.setAttribute("gb", gb); // 조회된 그룹 정보를 request에 담기

        // 포워딩
        request.getRequestDispatcher("views/group/groupDetail.jsp")
               .forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doGet() 메서드로 포워딩
        doGet(request, response);
    }

}
