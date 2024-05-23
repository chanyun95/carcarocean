package kr.board_comment.dao;

public class Board_commentDao {
	private static Board_commentDao dao = new Board_commentDao();
	public static Board_commentDao getDao() {
		return dao;
	}
	private Board_commentDao() {}
}
