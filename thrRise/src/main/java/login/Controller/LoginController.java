package login.Controller;

import org.apache.log4j.Logger;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.Model.LoginControllerModel;
import login.Service.LoginService;

public class LoginController {

	private Logger log = Logger.getLogger(this.getClass());
	private LoginService loginService;
	
	
	/* �α���������! */
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String getWelcome(HttpServletRequest req) {
		return "log/login";
	}
	
	/* �α��� */
	@RequestMapping(value="/main.do",method=RequestMethod.POST)
	public String getLogin(HttpServletRequest req
			,@RequestParam("hiddenId")String user_id
			,@RequestParam("hiddenPass")String user_password) {
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		log.info(":: login.Controller.LoginController ���� :: getLogin");
		log.info(":: �������̵� ::"+user_id);
		log.info(":: ������й�ȣ ::"+user_password);
		LoginControllerModel param = new LoginControllerModel();
		param.setUser_id(user_id);
		param.setUser_password(user_password);
		String result_code = loginService.getLogin(param);
		log.info("result_code :: "+result_code);
		
		if(result_code.equals("0000")) {
			/* �α��� ���� : 0000 */
			
		}else if(result_code.equals("1000")) {
			/* ��й�ȣ ����ġ : 1000 */
			
		}else if(result_code.equals("5000")) {
			/* ���̵� ����  : 5000 */
			
		}else if(result_code.equals("9000")) {
			/* ������ : 9000 */
			
		}else {
			/* �� �� ���� ���� */
		}
		
		
		log.info(":: login.Controller.LoginController ���� :: getLogin");
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		return "log/main";
		
	}
}
