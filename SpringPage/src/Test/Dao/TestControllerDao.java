package Test.Dao;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import Test.Model.TestControllerModel;

public class TestControllerDao extends SqlSessionDaoSupport{
	private Logger log = Logger.getLogger(this.getClass());
	
	/* 로그인체크 */
	public TestControllerModel getLogin(TestControllerModel param) {
		return (TestControllerModel) getSqlSession().selectList("getLogin",param);
	}
	
	/* 로그인 히스토리 */
	public void getLoginHistory(TestControllerModel param) {
		getSqlSession().update("getLoginHistory",param);
	}
}
