package smu.shuttle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import smu.shuttle.dao.ClassDao;
import smu.shuttle.dao.BusDao;

/**
 * Servlet implementation class Servlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClassDao cDao = new ClassDao();
	BusDao bDao = new BusDao();
	ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("-------------------------------------------");
		String action = req.getParameter("action");
		if (action.equals("loginAdmin")) { //관리자 로그인
			loginAdmin(req, res);
		} 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost..");
		req.setCharacterEncoding("utf-8");
		doGet(req, res);
	}
	
	private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String adminId = request.getParameter("id");
		String adminPw = request.getParameter("pass");
		if (adminId.equals("admin") & adminPw.equals("1234")) {
			response.sendRedirect("AdminMain.jsp");
			System.out.println("관리자 로그인 성공");
		} else {
			String msg = "* ID 혹은 패스워드가 틀렸습니다.";
			request.setAttribute("msg", msg);
			try {
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
