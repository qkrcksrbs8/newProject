package login.Service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import login.Dao.LoginControllerDao;
import login.Model.LoginControllerModel;

@SuppressWarnings("unused")
@Repository("LoginService")
public class LoginServiceImpl {
	private Logger log = Logger.getLogger(this.getClass());
	LoginControllerDao LoginDao = new LoginControllerDao();
	
	public String getLogin(LoginControllerModel param) {
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		log.info(":: login.Service.impl.LoginServiceImpl ���� ::");
		String result_code = "9000"; /* ������ : 9000 */
		try {		
			LoginControllerModel loginController = new LoginControllerModel();
			loginController = LoginDao.getLogin(param);
			if(loginController.getUser_id().length() > 0) {
				loginController.setUser_id(param.getUser_id());
				loginController.setUser_no(param.getUser_no());
				if(param.getUser_password().equals(loginController.getUser_password())) {
					loginController.setFail_cnt(0);				
					loginController.setLogin_result("T");
					result_code= "0000"; /* �α��μ��� : 0000 */
				}else {
					loginController.setFail_cnt(loginController.getFail_cnt()+1);
					loginController.setLogin_result("F");
					result_code= "1000"; /* ��й�ȣ ����ġ : 1000 */
				}						
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
}
