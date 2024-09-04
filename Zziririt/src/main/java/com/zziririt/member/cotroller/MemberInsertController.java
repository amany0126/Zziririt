package com.zziririt.member.cotroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.zziririt.member.model.service.MemberService;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 1) 요청 방식이 multipart/form-data 형식인지 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			// 1_1. 전송파일 용량 제한 (int maxSize)
			int maxSize = 10 * 1024 * 1024; // 10MB 로 파일용량 제한
			
			// 1_2. 전달된 파일을 저장할 서버의 실제 폴더 경로 지정
			String savePath = request.getServletContext()
								.getRealPath("/resources/member_profiles/");
		
			// 2. 전달된 파일명을 수정하면서 파일을 서버에 업로드를 하는 작업
			MultipartRequest multiRequest 
				= new MultipartRequest(request, 
									   savePath,
									   maxSize,
									   "UTF-8",
									   new DefaultFileRenamePolicy());

			// String originName = multiRequest.getOriginalFileName("userProfile");
			String changeName = multiRequest.getFilesystemName("userProfile");
			String userProfile = "resources/member_profiles/" + changeName;
			
			String userId = multiRequest.getParameter("userId"); // not null
			String userPwd = multiRequest.getParameter("userPwd"); // not null
			String userNickname = multiRequest.getParameter("userNickname"); // not null
			String userName = multiRequest.getParameter("userName"); // not null
			String userGender = multiRequest.getParameter("userGender"); // not null
			String userPhone = multiRequest.getParameter("userPhone"); // not null
			String userMail = multiRequest.getParameter("userMail"); // not null
			String birthDate = multiRequest.getParameter("birthDate"); // not null		
			
			String regionSelect = multiRequest.getParameter("regionSelect");
			String citySelect = multiRequest.getParameter("citySelect");
			String address = regionSelect + " " + citySelect;
			
			String introduceContent = multiRequest.getParameter("introduceContent"); // not null
			
			// 2) VO 타입으로 가공
			Member m = new Member(userId, userPwd, userNickname, 
								  userName, userGender, userProfile, 
								  userPhone, userMail, birthDate, 
								  address, introduceContent);
			
			// 3) 서비스로 전달값 넘기면서 요청 후 결과 받기
			int result = new MemberService().insertMember(m);
			
			// 4) 결과에 따른 응답페이지 지정
			if(result > 0) { // 성공
				// 알람 문구 띄워주고 다시 메인페이지로 이동
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg", "회원가입에 성공했습니다.");
				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
				request.getRequestDispatcher("/views/common/errorPage.jsp")
					   .forward(request, response);
			}
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