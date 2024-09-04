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
 * Servlet implementation class ChangeGroupImgAjax
 */
@WebServlet("/changeGroupImg")
public class ChangeGroupImgAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeGroupImgAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int maxSize = 10 * 1024 * 1024;
		String savePath = request.getServletContext().getRealPath("/resources/images/group/");
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new FileRenameUseUserId(((Member) request.getSession().getAttribute("loginUser")).getUserId()));
		String groupProfile = multiRequest.getOriginalFileName("groupProfile");
		groupProfile= groupProfile == null ? "/Zziririt/resources/images/group/default.JPG"
				: request.getContextPath()+"/resources/images/group/" + multiRequest.getFilesystemName("groupProfile");
		String gno = multiRequest.getParameter("gno");
		int result = new GroupService().changeGroupImg(groupProfile,gno);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
