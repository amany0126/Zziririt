package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zziririt.common.FileRenameUseUserId;
import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.Group;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class CreateGroupAjax
 */
@WebServlet("/CreateGroup")
public class CreateGroupAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGroupAjax() {
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
		request.setCharacterEncoding("UTF-8");
		int maxSize = 10 * 1024 * 1024;
		String savePath = request.getServletContext().getRealPath("/resources/images/group/");
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new FileRenameUseUserId(((Member) request.getSession().getAttribute("loginUser")).getUserId()));		
		String groupProfile = multiRequest.getOriginalFileName("groupProfile");
		Group g = new Group();
		g.setGroupProfile(groupProfile == null ? "/Zziririt/resources/images/group/default.JPG"
				: request.getContextPath()+"/resources/images/group/" + multiRequest.getFilesystemName("groupProfile"));
		g.setGroupName(multiRequest.getParameter("groupName"));
		g.setCategoryName(multiRequest.getParameter("groupCategory"));
		g.setGroupLimit(Integer.parseInt(multiRequest.getParameter("groupLimit")));
		g.setGroupArea(multiRequest.getParameter("groupArea") + " "+multiRequest.getParameter("groupCity"));
		g.setGroupDescript(multiRequest.getParameter("groupDescript"));
		g.setWish(multiRequest.getParameter("introduce"));//wish 필드 재활용 자기소개 필드임
		int result = new GroupService().createGroup(g,((Member)request.getSession().getAttribute("loginUser")).getUserNo());
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
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
