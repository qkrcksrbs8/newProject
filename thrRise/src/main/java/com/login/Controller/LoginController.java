package com.login.Controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.javafx.collections.MappingChange.Map;

import com.login.Model.LoginControllerModel;
import com.login.Service.LoginService;

/**
 * 
 * <pre>
 * Statements
 * </pre>
 * 
 * @ClassName   : LoginController.java
 * @Description : Ŭ���� ������ ����մϴ�.
 * @author qkrck
 * @since 2020. 1. 24.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *  since         author    description
 * ============   ======  ====================
 * 2020. 1. 24.   qkrck    ���ʻ��� 2020. 1. 24.
 * </pre>
 */
@SuppressWarnings("unused")
@Controller
public class LoginController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public LoginService loginService;
	
	/**
	 * �α����������� �̵�
	 * @param req
	 * @return
	 */
	@RequestMapping(value="login.do",method=RequestMethod.GET)
	public String getWelcome(HttpServletRequest req) {
		log.info(":: getWelcome ::");
		return "log/login";
	}
	
	/**
	 * ������������ �̵�
	 * @param req
	 * @param user_id
	 * @param user_password
	 * @return
	 */
	@RequestMapping(value="/main.do",method=RequestMethod.POST)
	public String getLogin(HttpServletRequest req
			,@RequestParam("hiddenId")String user_id
			,@RequestParam("hiddenPass")String user_password) {
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		log.info(":: login.Controller.LoginController ���� :: getLogin");
		log.info(":: �������̵� ::"+user_id);
		log.info(":: ������й�ȣ ::"+user_password);
		try {
		HashMap <String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("user_password", user_password);
		log.info(" ���� ���̵�		:: "+map.get("user_id"));
		log.info(" ���� ��й�ȣ	:: "+map.get("user_password"));
		String result_code = "";
		result_code = loginService.getLogin(map);
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
		
		}catch(Exception e) {
			log.error("::ERROR::"+e.toString());
			return "index";
			
		}
		return "log/main";
	}
}
