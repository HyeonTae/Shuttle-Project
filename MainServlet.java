package smu.shuttle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import smu.shuttle.model.Class;
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
		if (action.equals("loginAdmin")) { // 관리자 로그인
			loginAdmin(req, res);
		} else if (action.equals("searchAllClass")) { // 전체학생조회
			searchAllClass(req, res);
		} else if (action.equals("regClass")) { //학생추가페이지
			res.sendRedirect("RegClassPage.jsp");
		} else if (action.equals("regClassToAdmin")) { // 학생추가
			regClassToAdmin(req, res);
		} else if (action.equals("searchClass")) { // 학생검색페이지 이동
			res.sendRedirect("SearchClassPage.jsp");
		} else if (action.equals("searchClassForID")) { // 학생 ID로 검색
			searchClassForID(req, res);
		} else if (action.equals("updateClass")) { // 학생정보수정
			updateClass(req, res);
		} else if (action.equals("deleteClass")) { // 학생정보삭제
			deleteClass(req, res);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost..");
		req.setCharacterEncoding("utf-8");
		doGet(req, res);
	}

	// 과리자 로그인
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

	// 전체 학생 조회
	public void searchAllClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("classList", cDao.getAllClass());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchAllClassPage.jsp");
		System.out.println("모든학생조회");
		dispatcher.forward(request, response);
	}

	// 학생 추가
	public void regClassToAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cDao.insertClass(new Class(request.getParameter("id"), request.getParameter("pass"),
				request.getParameter("name"), request.getParameter("dept"), request.getParameter("area")));
		System.out.println("학생추가완료");
		response.sendRedirect("AdminMain.jsp");
	}

	// 학생 ID로 검색
	public void searchClassForID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("class", cDao.selectUserForId(request.getParameter("id")));
		RequestDispatcher rd = request.getRequestDispatcher("/SearchClassPage.jsp");
		System.out.println("학생조회완료");
		rd.forward(request, response);
	}

	// 학생정보 수정
	public void updateClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		String area = request.getParameter("area");
		if (cDao.updateClass(new Class(id, pass, name, dept, area)) > 0) {
			System.out.println(id + "이 수정하였습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMain.jsp");
		dispatcher.forward(request, response);
	}

	// 학생정보 삭제
	public void deleteClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (cDao.deleteClass(id) > 0) {
			System.out.println(id + "이 삭제 되었습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMain.jsp");
		dispatcher.forward(request, response);
	}
}
