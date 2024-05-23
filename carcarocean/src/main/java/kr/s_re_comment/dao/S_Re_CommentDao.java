package kr.s_re_comment.dao;

public class S_Re_CommentDao {
	//싱글턴 패턴
	private static S_Re_CommentDao instance = new S_Re_CommentDao();
	
	public static S_Re_CommentDao getDao() {
		return instance;
	}
	private S_Re_CommentDao() {}
	
	
}
