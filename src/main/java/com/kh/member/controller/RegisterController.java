package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지 이동 용도
		request.getRequestDispatcher("/WEB-INF/views/member/enroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("id");
		String memberPw = request.getParameter("password");
		String memberName = request.getParameter("name");
		int memberAge = Integer.parseInt( request.getParameter("age"));
		String memberGender = request.getParameter("gender");
		String memberEmail = request.getParameter("email");
		String memberPhone = request.getParameter("phone");
		String memberAddress = request.getParameter("address");
		String memberHobby = request.getParameter("hobby");
		
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);
	
		MemberService service = new MemberService();
		int result = service.insertMember(member);
		if(result >0) {
			//성공 = 로그인 하도록 함
			response.sendRedirect("/index.jsp");
		} else {
			//실패 = 에러 메세지를 출력함
			request.setAttribute("msg", "회원등록이 완료되지 않았습니다.");
			request.setAttribute("url", "/member/register.do");
			request.getRequestDispatcher("/WEB-INF/views/common/Error.jsp").forward(request, response);
		}
	}

}
