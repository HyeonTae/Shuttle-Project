package smu.shuttle.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import smu.shuttle.model.Class;
import smu.shuttle.model.Bus;
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
		} else if (action.equals("regClass")) { // 학생추가페이지
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
		} else if (action.equals("addBus")) { // 셔틀버스추가페이지
			res.sendRedirect("addBusPage.jsp");
		} else if (action.equals("addBusToAdmin")) { // 셔틀버스추가
			addBusToAdmin(req, res);
		} else if (action.equals("updateBusPage")) { // 셔틀버스 수정 페이지 이동
			res.sendRedirect("updateBusPage.jsp");
		} else if (action.equals("searchBusForID")) { // 셔틀버스 ID로 검색
			searchBusForID(req, res);
		} else if (action.equals("updateBus")) { // 셔틀버스정보수정
			updateBus(req, res);
		} else if (action.equals("deleteBus")) { // 셔틀버스정보삭제
			deleteBus(req, res);
		} else if (action.equals("searchAllBus")) { // 전체 셔틀버스 시간표 조회
			searchAllBus(req, res);
		} else if (action.equals("searchBusFromAsanToSMU")) { // 셔틀버스 시간표
																// 조회(아산역->학교)
			searchBusFromAsanToSMU(req, res);
		} else if (action.equals("searchBusFromCheonAnToSMU")) { // 셔틀버스
																	// 시간표조회(천안역->학교)
			searchBusFromCheonAnToSMU(req, res);
		} else if (action.equals("searchBusFromTerminalToSMU")) { // 셔틀버스
																	// 시간표조회(터미널->학교)
			searchBusFromTerminalToSMU(req, res);
		} else if (action.equals("searchBusForAsan")) { // 셔틀버스 시간표조회(학교->아산역)
			searchBusForAsan(req, res);
		} else if (action.equals("searchBusForCheonAn")) { // 셔틀버스
															// 시간표조회(학교->천안역)
			searchBusForCheonAn(req, res);
		} else if (action.equals("searchBusForTerminal")) { // 셔틀버스
															// 시간표조회(학교->터미널)
			searchBusForTerminal(req, res);
		}
		// 학생용
		else if (action.equals("searchAllBusForClass")) { // 전체 셔틀버스 시간표 조회
															// (학생용)
			searchAllBusForClass(req, res);
		} else if (action.equals("classSearchBusFromAsanToSMU")) { // 셔틀버스 시간표
																	// 조회(아산역->학교)
			classSearchBusFromAsanToSMU(req, res);
		} else if (action.equals("classSearchBusFromCheonAnToSMU")) { // 셔틀버스
																		// 시간표조회(천안역->학교)
			classSearchBusFromCheonAnToSMU(req, res);
		} else if (action.equals("classSearchBusFromTerminalToSMU")) { // 셔틀버스
																		// 시간표조회(터미널->학교)
			classSearchBusFromTerminalToSMU(req, res);
		} else if (action.equals("classSearchBusForAsan")) { // 셔틀버스
																// 시간표조회(학교->아산역)
			classSearchBusForAsan(req, res);
		} else if (action.equals("classSearchBusForCheonAn")) { // 셔틀버스
																// 시간표조회(학교->천안역)
			classSearchBusForCheonAn(req, res);
		} else if (action.equals("classSearchBusForTerminal")) { // 셔틀버스
																	// 시간표조회(학교->터미널)
			classSearchBusForTerminal(req, res);
		} else if (action.equals("festSearchBusToSMU")) { // 빠른 시간표 조회 (학교로)
			festSearchBusToSMU(req, res);
		} else if (action.equals("festSearchBusToDest")) { // 빠른 시간표 조회 (집으로)
			festSearchBusToDest(req, res);
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

	// 셔틀버스 추가
	public void regClassToAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cDao.insertClass(new Class(request.getParameter("id"), request.getParameter("pass"),
				request.getParameter("name"), request.getParameter("dept"), request.getParameter("area")));
		System.out.println("셔틀버스 추가완료");
		response.sendRedirect("AdminMain.jsp");
	}

	// 셔틀버스 ID로 검색
	public void searchBusForID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("bus", bDao.getSearchBusForId(request.getParameter("id")));
		RequestDispatcher rd = request.getRequestDispatcher("/updateBusPage.jsp");
		System.out.println("셔틀버스ID조회완료");
		rd.forward(request, response);
	}

	// 학생정보 수정
	public void updateBus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String dep = request.getParameter("dep");
		String dest = request.getParameter("dest");
		int hour = Integer.parseInt(request.getParameter("hour"));
		int min = Integer.parseInt(request.getParameter("min"));
		if (bDao.updateBus(new Bus(id, dep, dest, hour, min)) > 0) {
			System.out.println("셔틀버스" + id + "이 수정되었습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMain.jsp");
		dispatcher.forward(request, response);
	}

	// 학생정보 삭제
	public void deleteBus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (bDao.deleteBus(id) > 0) {
			System.out.println("셔틀버스" + id + "이 삭제 되었습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMain.jsp");
		dispatcher.forward(request, response);
	}

	// 전체 셔틀버스 시간표 조회
	public void searchAllBus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("busList", bDao.getAllBus());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchAllBusPage.jsp");
		System.out.println("전체 셔틀버스 시간표 조회");
		dispatcher.forward(request, response);
	}

	// 전체 셔틀버스 시간표 조회 (학생조회용)
	public void searchAllBusForClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Bus> arr = bDao.getAllBusForClass();
		String result = mapper.writeValueAsString(arr);
		System.out.println("셔틀버스조회 (학생용)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 셔틀버스 시간표 조회(아산역->학교)
	public void searchBusFromAsanToSMU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = "아산역";
		String dest = "학교";
		request.setAttribute("busList", bDao.getSearchBusToSMU(dep, dest));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchBusFromAsanToSMUPage.jsp");
		System.out.println("셔틀버스 시간표 조회(아산역->학교)");
		dispatcher.forward(request, response);
	}

	// 셔틀버스 시간표 조회(천안역->학교)
	public void searchBusFromCheonAnToSMU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = "천안역";
		String dest = "학교";
		request.setAttribute("busList", bDao.getSearchBusToSMU(dep, dest));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchBusFromCheonAnToSMUPage.jsp");
		System.out.println("셔틀버스 시간표 조회(천안역->학교)");
		dispatcher.forward(request, response);
	}

	// 셔틀버스 시간표 조회(터미널->학교)
	public void searchBusFromTerminalToSMU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = "터미널";
		String dest = "학교";
		request.setAttribute("busList", bDao.getSearchBusToSMU(dep, dest));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchBusFromTerminalToSMUPage.jsp");
		System.out.println("셔틀버스 시간표 조회(터미널->학교)");
		dispatcher.forward(request, response);
	}

	// 셔틀버스 시간표 조회(학교->아산역)
	public void searchBusForAsan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "천안아산";
		request.setAttribute("busList", bDao.getSearchBusToDest(dest));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchBusForAsanPage.jsp");
		System.out.println("셔틀버스 시간표 조회(학교->아산역)");
		dispatcher.forward(request, response);
	}

	// 셔틀버스 시간표 조회(학교->천안역)
	public void searchBusForCheonAn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "천안아산";
		request.setAttribute("busList", bDao.getSearchBusToDest(dest));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchBusForCheonAnPage.jsp");
		System.out.println("셔틀버스 시간표 조회(학교->천안역)");
		dispatcher.forward(request, response);
	}

	// 셔틀버스 시간표 조회(학교->터미널)
	public void searchBusForTerminal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "터미널";
		request.setAttribute("busList", bDao.getSearchBusToDest(dest));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchBusForTerminalPage.jsp");
		System.out.println("셔틀버스 시간표 조회(학교->터미널)");
		dispatcher.forward(request, response);
	}

	// 학생용
	// 셔틀버스 시간표 조회(아산역->학교)
	public void classSearchBusFromAsanToSMU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = "아산역";
		String dest = "학교";
		ArrayList<Bus> arr = bDao.getSearchBusToSMU(dep, dest);
		String result = mapper.writeValueAsString(arr);
		System.out.println("셔틀버스조회 (천안역->학교)(학생용)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 셔틀버스 시간표 조회(천안역->학교)
	public void classSearchBusFromCheonAnToSMU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = "천안역";
		String dest = "학교";
		ArrayList<Bus> arr = bDao.getSearchBusToSMU(dep, dest);
		String result = mapper.writeValueAsString(arr);
		System.out.println("셔틀버스조회 (천안역->학교)(학생용)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 셔틀버스 시간표 조회(터미널->학교)
	public void classSearchBusFromTerminalToSMU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = "터미널";
		String dest = "학교";
		ArrayList<Bus> arr = bDao.getSearchBusToSMU(dep, dest);
		String result = mapper.writeValueAsString(arr);
		System.out.println("셔틀버스조회 (터미널->학교)(학생용)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 셔틀버스 시간표 조회(학교->아산역)
	public void classSearchBusForAsan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "천안/아산역";
		ArrayList<Bus> arr = bDao.getSearchBusToDest(dest);
		String result = mapper.writeValueAsString(arr);
		System.out.println("셔틀버스조회 (학교->아산역)(학생용)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 셔틀버스 시간표 조회(학교->천안역)
	public void classSearchBusForCheonAn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "천안/아산역";
		ArrayList<Bus> arr = bDao.getSearchBusToDest(dest);
		String result = mapper.writeValueAsString(arr);
		System.out.println("셔틀버스조회 (학교->천안역)(학생용)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 셔틀버스 시간표 조회(학교->터미널)
	public void classSearchBusForTerminal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "천안터미널";
		ArrayList<Bus> arr = bDao.getSearchBusToDest(dest);
		String result = mapper.writeValueAsString(arr);
		System.out.println("셔틀버스조회 (학교->터미널)(학생용)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 빠른 시간표 조회 (학교로)
	public void festSearchBusToSMU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = request.getParameter("dep");
		String dest = "학교";
		int hour = Integer.parseInt(request.getParameter("hour"));
		int min = Integer.parseInt(request.getParameter("min"));
		ArrayList<Bus> arr = bDao.getFestSearchBusToSMU(dep, dest, hour, min);
		String result = mapper.writeValueAsString(arr);
		System.out.println("빠른 시간표 조회 (학교로)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	// 빠른 시간표 조회 (집으로)
	public void festSearchBusToDest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dep = "학교";
		int hour = Integer.parseInt(request.getParameter("hour"));
		int min = Integer.parseInt(request.getParameter("min"));
		ArrayList<Bus> arr = bDao.getFestSearchBusToDest(dep, hour, min);
		String result = mapper.writeValueAsString(arr);
		System.out.println("빠른 시간표 조회 (학교로)");
		request.setAttribute("result", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}
}
