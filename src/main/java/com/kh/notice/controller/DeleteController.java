package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet(name = "NoticeDeleteController", urlPatterns = { "/notice/delete.do" })
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeService service = new NoticeService();
		int result = service.deleteNoticeByNo(noticeNo);
		if(result > 0) {
			//성공 -> 목록으로 이동
			response.sendRedirect("/notice/list.do");
		} else {
			//실패 -> 실패메세지 및 상세페이지로 이동
			request.setAttribute("msg", "공지사항 삭제 실패");
			request.setAttribute("url", "/WEB-INF/views/notice/detail.jsp");
			request.getRequestDispatcher("/WEB-INF/views/common/Error.jsp");
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
