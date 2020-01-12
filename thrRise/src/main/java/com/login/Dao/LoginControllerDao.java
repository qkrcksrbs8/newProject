package com.login.Dao;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.login.Model.LoginControllerModel;

public class LoginControllerDao extends SqlSessionDaoSupport{
	private Logger log = Logger.getLogger(this.getClass());
	
	/* 로그인체크 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getLogin(HashMap<String, Object> param) {
		return (HashMap<String, Object>) getSqlSession().selectList("getLogin", param);
	}
//	public LoginControllerModel getLogin(LoginControllerModel param) {
//		return (LoginControllerModel) getSqlSession().selectList("getLogin",param);
//	}
	
	/* 로그인 히스토리 */
	public void getLoginHistory(LoginControllerModel param) {
		getSqlSession().update("getLoginHistory",param);
	}


}
