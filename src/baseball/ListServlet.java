package baseball;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 初期画面を表示するServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DetailDto dto = new DetailDto();
	DetailDao dao = new DetailDao();
	List<DetailDto> resultList = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//daoに投げる→結果を受け取る
		resultList = dao.load(0);

		//戻り値をリクエストスコープにセットする
		request.setAttribute("resultList", resultList);


		//遷移先を指定する
		RequestDispatcher rd = request.getRequestDispatcher("/List.jsp");

		//フォワードする
		rd.forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
