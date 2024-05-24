package kr.qa_comment.dao;

public class Qa_CommentDao {
	//싱글턴 패턴
	private static Qa_CommentDao instance = new Qa_CommentDao();
	
	public static Qa_CommentDao getDao() {
		return instance;
	}
	private Qa_CommentDao() {}
	
}
