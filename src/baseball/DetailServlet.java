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
 * 詳細画面を表示するServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DetailDto dto = new DetailDto();
	DetailDao dao = new DetailDao();
	List<DetailDto> resultList = new ArrayList<DetailDto>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//クリックしたidを受け取る
		int id = Integer.parseInt(request.getParameter("id"));

		//daoに投げる→結果を受け取る
		resultList = dao.load(id);

		//検索結果をリクエストスコープにセットする
		request.setAttribute("resultList", resultList);
/*		request.setAttribute("id", resultList.get(0).getId());
		request.setAttribute("teamName", resultList.get(0).getTeamName());
		request.setAttribute("headQuarters", resultList.get(0).getHeadQuarters());
		request.setAttribute("inauguration", resultList.get(0).getInauguration());
		request.setAttribute("history", history);
*/

		//遷移先を指定する
		RequestDispatcher rd = request.getRequestDispatcher("/Detail.jsp");

		//フォワードする
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
