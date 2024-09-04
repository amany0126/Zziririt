package com.zziririt.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.zziririt.board.model.service.BoardService;
import com.zziririt.board.model.vo.Board;
import com.zziririt.common.FileRenameUseUserId;
import com.zziririt.member.model.vo.Member;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @param fileOriginName 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 1) 요청 방식이 multipart/form-data 형식인지 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			// 1_1. 전송파일 용량 제한 (int maxSize)
			int maxSize = 100 * 1024 * 1024; // 100MB 로 파일용량 제한
			
			// 1_2. 전달된 파일을 저장할 서버의 실제 폴더 경로 지정
			String savePath = request.getServletContext()
								.getRealPath("/resources/board_upfiles/");
			
			// 1_3. 파일 이름 변경을 위한 FileRenameUseUserId 객체 생성
            HttpSession session = request.getSession();
            String userId = ((Member)session.getAttribute("loginUser")).getUserId(); // 세션에서 사용자 ID 가져오기
            FileRenameUseUserId fileRenamer = new FileRenameUseUserId(userId);
			
			// 2. 전달된 파일명을 수정하면서 파일을 서버에 업로드를 하는 작업
			MultipartRequest multiRequest 
				= new MultipartRequest(request, 
									   savePath,
									   maxSize,
									   "UTF-8",
									   fileRenamer);
			
			//  3. MultipartRequest 객체를 통해 요청 시 전달값 뽑기
			// 글 제목 (boardTitle)
			String boardTitle 
				= multiRequest.getParameter("boardTitle");
			
			// 작성자의 회원번호 (userNo)
			String boardWriter 
				= multiRequest.getParameter("userNo");
			
			// 카테고리 정보 (categoryName)
			String categoryName 
				= multiRequest.getParameter("categoryName");
			
			// 모임 시간 (meetingTime)
			String ddaySelect = multiRequest.getParameter("ddaySelect");
			String timeSelect = multiRequest.getParameter("timeSelect");
			String meetingTime = ddaySelect + " " + timeSelect;
			
			// meetingTimeStr을 데이터베이스에서 사용하는 형식으로 변환
			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// Date meetingTimeDate = (Date)formatter.parse(meetingTimeStr);
			// Timestamp meetingTime = new Timestamp(meetingTimeDate.getTime());
			
			// 모임 정원 (peopleLimt)
			int peopleLimt 
				= Integer.parseInt(multiRequest.getParameter("peopleLimit"));
			
			// 모임 장소 (meetingSpot)
			String regionSelect2 = multiRequest.getParameter("regionSelect2");
			String citySelect2 = multiRequest.getParameter("citySelect2");
			String detailedAddress = multiRequest.getParameter("detailedAddress");
			
			String meetingSpot = regionSelect2 + " " + citySelect2 + " " + detailedAddress;
			
			// 글 내용 (boardContent)
			String boardContent 
				= multiRequest.getParameter("boardContent");
			
			// 파일 정보 저장
			String fileOriginName = multiRequest.getOriginalFileName("fileInfo");
			String changeName = multiRequest.getFilesystemName("fileInfo");
			String fileInfo = "resources/board_upfiles/" + changeName;
			
			// 위의 정보들을 BOARD 타입의 VO 객체로 가공
			Board b = new Board();
			b.setBoardTitle(boardTitle);
			b.setBoardWriter(boardWriter);
			b.setCategoryName(categoryName);
			b.setMeetingTime(meetingTime);
			b.setPeopleLimit(peopleLimt);
			b.setMeetingSpot(meetingSpot);
			b.setBoardContent(boardContent);
			b.setFileInfo(fileInfo);
			b.setFileOriginName(fileOriginName);

//			System.out.println();			
//			System.out.println();
//			System.out.println(fileInfo);
//			System.out.println();
			
			// 4. 서비스로 요청 후 결과 받기
			int result = new BoardService().insertBoard(b);
			
			// 5. 결과에 따른 응답페이지 처리
			if(result > 0) { // 성공
				// list.bo?currentPage=1 로 url 재요청
				// (방금 쓴 글이 가장 최신글)
				request.getSession()
						.setAttribute("alertMsg", 
									  "게시글 작성에 성공했습니다.");
				
				response.sendRedirect(request.getContextPath()
										+ "/list.bo?currentPage=1");
			} else { // 실패
				// 에러문구를 담아서 에러페이지로 포워딩
				request.setAttribute("errorMsg", 
									 "게시글 작성에 실패했습니다.");
				
				request
					.getRequestDispatcher("views/common/errorPage.jsp")
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