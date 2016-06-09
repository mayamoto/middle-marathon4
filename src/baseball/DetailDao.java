package baseball;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DBへ接続し、検索結果を取得するクラス
 */
public class DetailDao {
	DetailDto list = new DetailDto();
	List<DetailDto> resultList = new ArrayList<DetailDto>();

	public List<DetailDto> load(int id){
		String sql=null;

		//初期化
		resultList.clear();

		//接続
		Connection con = DBManager.createConnection();

		//SQLをセット
		if (id == 0){
			//一覧画面
			sql = "SELECT"
					+ " T01.TEAM_NAME AS TEAMNAME"
					+ " ,T01.ID"
			   + " FROM"
					+ " BASEBALL_TEAMS T01"
			   + " ORDER BY"
			   		+ " T01.ID";

		} else{
			//詳細画面
			sql = "SELECT"
					+ " T01.ID"
					+ " ,T01.TEAM_NAME AS TEAMNAME"
					+ " ,T01.HEADQUARTERS"
					+ " ,T01.INAUGURATION"
					+ " ,T01.HISTORY"
			   + " FROM"
					+ " BASEBALL_TEAMS T01"
			   + " WHERE"
			   		+ " T01.ID = " + id;
		}

		try {
			//SQLの発行準備
			PreparedStatement ps = con.prepareStatement(sql);

			//SQL実行
			ResultSet rs = ps.executeQuery();

			//結果を取り出す
			if(id == 0){
				while(rs.next()){
					list = new DetailDto();

					list.setTeamName(rs.getString("teamName"));

					//リストに追加する
					resultList.add(list);
				}

			} else {
				if(rs.next()){
					list = new DetailDto();

					list.setId(Integer.parseInt(rs.getString("id")));
					list.setTeamName(rs.getString("teamName"));
					list.setHeadQuarters(rs.getString("headQuarters"));
					list.setInauguration(rs.getString("inauguration"));
					list.setHistory(IsReplaceAll(rs.getString("history")));
					resultList.add(list);

				}
			}


		} catch (SQLException e) {
			throw new RuntimeException("SQLの発行準備～結果の取り出しの間でエラーが発生しました");

		} finally {
			System.out.println("sql: " + sql);

			//DB切断
			DBManager.cloneConnection(con);
		}

		//サーブレットに検索結果を返却する
		return resultList;
	}


	/**
	 * 検索結果の歴史の中に、↓があるときは改行して表示するメソッド
	 */
	private String IsReplaceAll(String beforeHistory){

		//↓があるか検索する
		Pattern pattern = Pattern.compile("↓");

		//DBから取得した歴史をセット resultList.get(0).getHistory()
		Matcher matcher = pattern.matcher(beforeHistory);

		//↓の上と下の行を改行する
		String afterHistory = matcher.replaceAll("<br>↓<br>");

		return afterHistory;
	}
}
