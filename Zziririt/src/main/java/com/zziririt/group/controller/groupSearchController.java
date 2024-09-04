package com.zziririt.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.common.model.service.CategoryService;
import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.Group;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class groupSearchController
 */
@WebServlet("/group/search")
public class groupSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public groupSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String category = request.getParameter("category");
		String area = request.getParameter("area");
		String keyword = request.getParameter("keywords");
		String page = request.getParameter("page");
		
		request.setAttribute("categoryList", new CategoryService().getCategoryList());
		request.setAttribute("category", category);
		request.setAttribute("area", area);
		request.setAttribute("keywords", keyword);
		
		Member m = (Member) request.getSession().getAttribute("loginUser");
		int listCount = new GroupService().searchGroupPage(category, area, keyword,
				m != null ? m.getUserNo() + "" : null);
		int currentPage = page == null ? 1 : Integer.parseInt(page);
		int pageLimit = 10;
		int boardLimit = 12;
		int maxPage = (int) Math.ceil((double) listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit -1;
		endPage = endPage>maxPage?maxPage:endPage;
		// ����¡ �Ϸ�
		if (listCount == 0) {// ��� ���� ��� ���� �ݳ�
			// ��� ����
			request.getRequestDispatcher("/views/group/searchGroup.jsp").forward(request, response);
			return;
		}
		PageInfo pi = new PageInfo(listCount, currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		ArrayList<Group> list = new GroupService().searchGroupList(category, area, keyword,
				m != null ? m.getUserNo() + "" : null,pi);
		
		if(list.isEmpty()) {
			request.getRequestDispatcher("/views/group/searchGroup.jsp").forward(request, response);
			return;
		}
		
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/group/searchGroup.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
