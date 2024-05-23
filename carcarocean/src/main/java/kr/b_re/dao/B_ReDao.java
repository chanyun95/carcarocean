package kr.b_re.dao;

public class B_ReDao {
	private static B_ReDao dao = new B_ReDao();
	public static B_ReDao getDao() {
		return dao;
	}
	private B_ReDao() {}
}
