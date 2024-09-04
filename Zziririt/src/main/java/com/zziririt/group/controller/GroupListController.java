package com.zziririt.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zziririt.common.model.vo.PageInfo;
import com.zziririt.group.model.service.GroupService;
import com.zziririt.group.model.vo.GroupBoard;

@WebServlet("/list.gr")
public class GroupListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GroupListController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
        int listCount; 
        int currentPage; 
        int pageLimit = 10;
        int boardLimit = 10;
        int maxPage;
        int startPage;
        int endPage;

        listCount = new GroupService().selectGroupBoardListCount();

        // System.out.println(listCount);
        
        String currentPageParam = request.getParameter("currentPage");
        currentPage = (currentPageParam != null && !currentPageParam.isEmpty()) ? Integer.parseInt(currentPageParam) : 1;

        maxPage = (int)Math.ceil((double)listCount / boardLimit);
        startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
        endPage = startPage + pageLimit - 1;
        if(endPage > maxPage) {
            endPage = maxPage;
        }

        PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
        ArrayList<GroupBoard> list = new GroupService().selectGroupBoardList(pi);
        
       // System.out.println(list.isEmpty());
        
        request.setAttribute("pi", pi);
        request.setAttribute("list", list);

        request.getRequestDispatcher("/views/group/groupList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
