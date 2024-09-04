package com.zziririt.member.cotroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.member.model.service.MemberService;

/**
 * Servlet implementation class AjaxNicknameCheckController
 */
@WebServlet("/nicknameCheck.me")
public class AjaxNicknameCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxNicknameCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 시 전달값 뽑기 
		String checkNickname = request.getParameter("checkNickname");
		
		// 서비스로 요청 후 결과 받기
		int count = new MemberService().nicknameCheck(checkNickname);
		
		// Ajax 요청이므로 "응답데이터" 만 넘겨야 함
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 사용 가능한 닉네임일 경우 : "NNNNY"
		// 사용 불가능한 닉네임일 경우 : "NNNNN"
		// > 각 상황에 맞는 응답데이터 전송
		
		if(count > 0) { // 사용 불가
			out.print("NNNNN");
		} else { // 사용 가능
			out.print("NNNNY");
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