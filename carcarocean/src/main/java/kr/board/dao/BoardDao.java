package kr.board.dao;

public class BoardDao {
	private static BoardDao dao = new BoardDao();
	public static BoardDao getDao() {
		return dao;
	}
	private BoardDao() {}
	
}
