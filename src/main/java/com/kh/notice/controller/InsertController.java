package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/notice/insert.do")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/notice/insert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지사항 등록
		request.setCharacterEncoding("UTF-8");
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		Notice notice = new Notice(noticeSubject, noticeContent);
		NoticeService service = new NoticeService();
		
		//INSERT INTO NOTICE_TBL VALUES(SEQ_NOTICENO.NEXTVAL,~~)
		int result = service.insertNotice(notice);
		if(result > 0) {
			//성공 -> 공지사항 목록으로
			response.sendRedirect("/notice/list.do");
		} else {
			//실패 -> 실패 메세지
			request.setAttribute("msg", "공지사항 등록 실패");
			request.setAttribute("url", "/notice/list.do");
			request.getRequestDispatcher("/WEB-INF/views/common/Error.jsp").forward(request, response);
		}
		
	}

}
