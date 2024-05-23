package kr.s_re.dao;

public class S_ReDao {
	//싱글턴 패턴
	private static S_ReDao instance = new S_ReDao();
	
	public static S_ReDao getDao() {
		return instance;
	}
	private S_ReDao() {}
	
	
}
