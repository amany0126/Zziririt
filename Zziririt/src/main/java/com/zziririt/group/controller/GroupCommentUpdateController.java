package com.zziririt.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.group.model.service.GroupService;

@WebServlet("/updateComment.gr")
public class GroupCommentUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GroupCommentUpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 댓글 번호 가져오기
        int commentNo = Integer.parseInt(request.getParameter("commentNo"));
       
        // 수정된 댓글 내용 가져오기
        String updatedContent = request.getParameter("updatedContent");

        // GroupService 객체 생성
        GroupService groupService = new GroupService();

        // 댓글 수정 요청
        int result = groupService.updateComment(commentNo, updatedContent);

        // 결과에 따른 응답 처리
        if(result > 0) { // 성공
        	// 수정 성공 알림 메시지 설정
            response.getWriter().write("success");
        } else { //   // 실패
        	 response.getWriter().write("failure");
            // 게시글 상세 페이지로 리다이렉트
            }
    
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
}
}
