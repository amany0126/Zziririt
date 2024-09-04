package com.zziririt.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0) 인코딩 설정 먼저
		request.setCharacterEncoding("UTF-8");
		
		// 1) multipart/form-data 형식인지 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			// 1. 먼저 필요한 값 세팅
			// 1_1. 전송되는 파일 용량 제한
			int maxSize = 100 * 1024 * 1024;
			
			// 1_2. 전달된 파일을 저장시킬 폴더 경로 지정
			String savePath = request.getServletContext()
									.getRealPath("/resources/board_upfiles");
			
			// 1_3. 파일 이름 변경을 위한 FileRenameUseUserId 객체 생성
            HttpSession session = request.getSession();
            String userId = ((Member)session.getAttribute("loginUser")).getUserId(); // 세션에서 사용자 ID 가져오기
            FileRenameUseUserId fileRenamer = new FileRenameUseUserId(userId);
			
			// 2. MultipartRequest 객체 생성
			MultipartRequest multiRequest 
				= new MultipartRequest(request, 
									   savePath, 
									   maxSize, 
									   "UTF-8", 
									   fileRenamer);
			
			//  3. MultipartRequest 객체를 통해 요청 시 전달값 뽑기
			// 글번호 (bno)
			int boardNo 
			= Integer.parseInt(multiRequest.getParameter("bno"));
			
			// 변경할 모집여부 (meetingStatus)
			int meetingStatus 
				= Integer.parseInt(multiRequest.getParameter("meetingStatus"));
			
			// 변경할 글 제목 (boardTitle)
			String boardTitle 
				= multiRequest.getParameter("boardTitle");
			
			// 변경할 카테고리 정보 (categoryName)
			String categoryName 
				= multiRequest.getParameter("categoryName");
			
			// 변경할 모임 시간 (meetingTime)
			String ddaySelect = multiRequest.getParameter("ddaySelect");
			String timeSelect = multiRequest.getParameter("timeSelect");
			String meetingTime = ddaySelect + " " + timeSelect;
			
			meetingTime = 
					new SimpleDateFormat("yyyyMMddHHmm")
										.format(new Date());
			
			// 변경할 모임 정원 (peopleLimt)
			int peopleLimt 
				= Integer.parseInt(multiRequest.getParameter("peopleLimit"));
			
			// 변경할 모임 장소 (meetingSpot)
			String regionSelect2 = multiRequest.getParameter("regionSelect2");
			String citySelect2 = multiRequest.getParameter("citySelect2");
			String detailedAddress = multiRequest.getParameter("detailedAddress");
			
			String meetingSpot = regionSelect2 + " " + citySelect2 + " " + detailedAddress;
			
			// 변경할 글 내용 (boardContent)
			String boardContent 
				= multiRequest.getParameter("boardContent");
			
			String fileOriginName = multiRequest.getOriginalFileName("fileInfo");
			String changeName = multiRequest.getFilesystemName("fileInfo");
			String filePath = "resources/board_upfiles/";
			String fileInfo = filePath + changeName;
			
			// 기존 첨부 파일이 있는지 확인
			String existingFileName = multiRequest.getParameter("fileOriginName");
			if (existingFileName != null && !existingFileName.isEmpty()) { // 기존 첨부파일이 있었을 경우
			    // 새로운 파일 업로드가 있을 경우
			    String newFileName = multiRequest.getOriginalFileName("reUpfile");
			    if (newFileName != null && !newFileName.isEmpty()) { // 새로운 첨부파일을 업로드하고자 한다면
			        File existingFile = new File(filePath + existingFileName);
			        if (existingFile.exists()) {
			            existingFile.delete(); // 기존 첨부파일 삭제 처리
			        }
			        fileOriginName = newFileName; // 새로운 첨부파일로 원본파일명 유출
			        fileInfo = filePath + multiRequest.getFilesystemName("reUpfile"); // 새로운 첨부파일로 수정 파일명 짓기
			    } else { // 새로운 파일이 업로드되지 않은 경우
			    	// 기존 원본 파일명, 기존 수정 파일명 그대로 감
			    	fileOriginName = multiRequest.getParameter("fileOriginName");
			        fileInfo = multiRequest.getParameter("fileInfo");
			    }
			} else { // 기존 첨부파일이 없었을 경우
			    // 새로운 파일 업로드가 있을 경우
			    String newFileName = multiRequest.getOriginalFileName("reUpfile");
			    if (newFileName != null && !newFileName.isEmpty()) {
			        fileOriginName = newFileName; // 새로운 첨부파일로 원본파일명 유출
			        fileInfo = filePath + multiRequest.getFilesystemName("reUpfile"); // 새로운 첨부파일로 수정 파일명 짓기
			    }
			}

			// 기존 첨부파일이 있는지 확인
//			if(multiRequest.getParameter("fileOriginName") != null) { // 기존 첨부파일이 있었을 경우
//				fileOriginName = multiRequest.getParameter("fileOriginName"); // 기존 첨부파일 불러오기
//				
//				// 새로운 파일 업로드가 있을 경우
//			    if(multiRequest.getOriginalFileName("reUpfile") != null) { // 새로운 첨부파일을 업로드하고자 한다면
//			    	new File(savePath + fileOriginName).delete(); // 기존 첨부파일 삭제 처리
//			    	fileOriginName = multiRequest.getOriginalFileName("reUpfile"); // 새로운 첨부파일로 원본파일명 유출
//			    	fileInfo = filePath + multiRequest.getFilesystemName("reUpfile"); // 새로운 첨부파일로 수정 파일명 짓기
//			    }
//			} else { // 기존 첨부파일이 없었을 경우
//				// 새로운 파일 업로드가 있을 경우
//			    if(multiRequest.getOriginalFileName("reUpfile") != null) {
//			    	fileOriginName = multiRequest.getOriginalFileName("reUpfile"); // 새로운 첨부파일로 원본파일명 유출
//			    	fileInfo = "resources/board_upfiles/" + multiRequest.getFilesystemName("reUpfile"); // 새로운 첨부파일로 수정 파일명 짓기
//			    	filePath = "resources/board_upfiles/"; // 저장 경로 지정
//			    }
//			    
//			    // 첨부파일이 원래도 없었는데 게시글을 수정해도 첨부파일을 새로 올리지 않는 경우는 여기서 해줄 게 없음
//			}
//			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setMeetingStatus(meetingStatus);
			b.setBoardTitle(boardTitle);
			b.setCategoryName(categoryName);
			b.setMeetingTime(meetingTime);
			b.setPeopleLimit(peopleLimt);
			b.setMeetingSpot(meetingSpot);
			b.setBoardContent(boardContent);
			b.setFileInfo(fileInfo);
			b.setFileOriginName(fileOriginName);
			
			System.out.println();
			System.out.println();
			System.out.println("####################");
			System.out.println(fileOriginName);
	    	System.out.println(fileInfo);
			
			// 서비스로 요청 보내기
			int result 
				= new BoardService().updateBoard(b);
			
			// 결과에 따른 응답페이지 처리
			if(result > 0) { // 성공
				// 해당 게시글 상세보기 url 재요청
				request.getSession()
						.setAttribute("alertMsg", 
									  "성공적으로 게시글이 수정되었습니다.");
				
				response.sendRedirect(request.getContextPath()
										+ "/detail.bo?bno=" + boardNo);
			} else { // 실패
				// 에러문구를 담아서 에러페이지로 포워딩
				request.setAttribute("errorMsg", 
									 "게시글 수정에 실패했습니다.");
				
				request.getRequestDispatcher("views/common/errorPage.jsp")
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