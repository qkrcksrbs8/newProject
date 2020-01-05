package Test.Controller;
import java.util.HashMap;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import Test.Model.TestControllerModel;
import Test.Service.TestService;
@SuppressWarnings("unused")
@Controller
public class TestController {

	private Logger log = Logger.getLogger(this.getClass());
	private TestService testService;
	
	
/* 새로운 문구 */	/* 로그인페이지! */
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String getWelcome(HttpServletRequest req) {
		return "log/login";
	}
	
	/* 로그인 */
	@RequestMapping(value="/main.do",method=RequestMethod.POST)
	public String getLogin(HttpServletRequest req
			,@RequestParam("hiddenId")String user_id
			,@RequestParam("hiddenPass")String user_password) {
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		log.info(":: Test.Controller.TestController 시작 :: getLogin");
		log.info(":: 유저아이디 ::"+user_id);
		log.info(":: 유저비밀번호 ::"+user_password);
		TestControllerModel param = new TestControllerModel();
		param.setUser_id(user_id);
		param.setUser_password(user_password);
		String result_code = testService.getLogin(param);
		log.info("result_code :: "+result_code);
		
		if(result_code.equals("0000")) {
			/* 로그인 성공 : 0000 */
			
		}else if(result_code.equals("1000")) {
			/* 비밀번호 불일치 : 1000 */
			
		}else if(result_code.equals("5000")) {
			/* 아이디 없음  : 5000 */
			
		}else if(result_code.equals("9000")) {
			/* 비정상 : 9000 */
			
		}else {
			/* 알 수 없는 에러 */
		}
		
		
		log.info(":: Test.Controller.TestController 종료 :: getLogin");
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		return "log/main";
		
	}
	
}
