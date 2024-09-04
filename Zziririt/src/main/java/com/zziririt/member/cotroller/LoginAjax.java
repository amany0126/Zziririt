package com.zziririt.member.cotroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zziririt.member.model.service.MemberService;
import com.zziririt.member.model.vo.Member;


/**
 * Servlet implementation class LoginAjax
 */
@WebServlet("/login")
public class LoginAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		
		// 2) Member ���엯�쓽 VO 濡� 媛�怨듯븯湲�
		
		
		// 3) Service �떒�쑝濡� VO 瑜� �꽆湲곕㈃�꽌 �슂泥� �썑 寃곌낵 諛쏄린
		
		
		//System.out.println(userId);
		//System.out.println(userPwd);
		//System.out.println(loginUser);
		
		if (request.getParameter("logout") != null) {
			//濡쒓렇�븘�썐 泥섎━
			request.getSession().removeAttribute("loginUser");
			response.getWriter().print("hello");
			
		} else {
			//濡쒓렇�씤 泥섎━
			//request.getSession().setAttribute("loginUser", "notnull");
			///response.setContentType("text/plain ;charset=UTF-8");
			//response.getWriter().write("�뀒�뒪�꽣");
			HttpSession session = request.getSession();
			Member m = new Member();
			m.setUserId(userId);
			m.setUserPwd(userPwd);
			Member loginUser = new MemberService().loginMember(m);		
			session.setAttribute("loginUser", loginUser);
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().print(loginUser!=null?loginUser.getUserName():"fail");
		}
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
