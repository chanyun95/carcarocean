package kr.b_re_comment.dao;

public class B_Re_CommentDao {
	private static B_Re_CommentDao dao = new B_Re_CommentDao();
	public static B_Re_CommentDao getDao() {
		return dao;
	}
	private B_Re_CommentDao() {}
}
