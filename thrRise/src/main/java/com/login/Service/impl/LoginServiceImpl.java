package com.login.Service.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import com.login.Dao.LoginControllerDao;
import com.login.Model.LoginControllerModel;
import com.login.Service.LoginService;

@SuppressWarnings("unused")
@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired(required=true) 
	LoginControllerDao LoginDao = new LoginControllerDao();
	
	public String getLogin(HashMap<String, Object> param) {
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		log.info(":: login.Service.impl.LoginServiceImpl ���� ::");
		String result_code = "9000"; /* ������ : 9000 */
		try {		
			HashMap<String, Object> map = new HashMap<String, Object>();
			map = LoginDao.getLogin(param);
			String user_id = (String) map.get("user_id");
			if(user_id.length() > 0) {
				
//				if(param.getUser_password().equals(loginController.getUser_password())) {
//					loginController.setFail_cnt(0);				
//					loginController.setLogin_result("T");
//					result_code= "0000"; /* �α��μ��� : 0000 */
//				}else {
//					loginController.setFail_cnt(loginController.getFail_cnt()+1);
//					loginController.setLogin_result("F");
//					result_code= "1000"; /* ��й�ȣ ����ġ : 1000 */
//				}						
			}else {
				result_code= "5000"; /* ���̵� ����  : 5000 */
			}
			
		}catch(Exception e) {
			log.error("::ERROR::"+e.toString());
		}
		
		
		log.info(":: login.Service.impl.LoginServiceImpl ���� ::");
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");

		return result_code;
	}
	
	public int getInt() {
		return 100;
	}
}
