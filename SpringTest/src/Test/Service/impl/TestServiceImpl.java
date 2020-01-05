package Test.Service.impl;

import Test.Service.TestService;

import org.apache.log4j.Logger;

import Test.Dao.TestControllerDao;
import Test.Model.TestControllerModel;

public class TestServiceImpl implements TestService {
	private Logger log = Logger.getLogger(this.getClass());
	TestControllerDao TestDao = new TestControllerDao();
	
	public String getLogin(TestControllerModel param) {
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");
		log.info(":: Test.Service.impl.TestServiceImpl ���� ::");
		String result_code = "9000"; /* ������ : 9000 */
		try {		
			TestControllerModel testController = new TestControllerModel();
			testController = TestDao.getLogin(param);
			if(testController.getUser_id().length() > 0) {
				testController.setUser_id(param.getUser_id());
				testController.setUser_no(param.getUser_no());
				if(param.getUser_password().equals(testController.getUser_password())) {
					testController.setFail_cnt(0);				
					testController.setLogin_result("T");
					result_code= "0000"; /* �α��μ��� : 0000 */
				}else {
					testController.setFail_cnt(testController.getFail_cnt()+1);
					testController.setLogin_result("F");
					result_code= "1000"; /* ��й�ȣ ����ġ : 1000 */
				}						
			}else {
				result_code= "5000"; /* ���̵� ����  : 5000 */
			}
			
		}catch(Exception e) {
			log.error("ERROR :: "+e.toString());
		}
		
		
		log.info(":: Test.Service.impl.TestServiceImpl ���� ::");
		log.info(":: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ::");

		return result_code;
	}

}
