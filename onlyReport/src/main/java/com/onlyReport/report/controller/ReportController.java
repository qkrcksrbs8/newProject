package com.onlyReport.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.ReportVO;
import com.onlyReport.report.service.ReportService;
import com.onlyReport.report.util.PagingUtil;

/**
 *<pre>
 *�������� ��Ʈ�ѷ�
 *</pre>
 *
 *@ClassName : ReportController.java 
 *@Description : ���������� ���õ� ����� ������ ��Ʈ�ѷ��Դϴ�.
 *@author user
 *@since 2020. 11. 26
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 11. 26   user   ���ʻ���
 *</pre>
 */
@Controller
public class ReportController {

	Logger logger = Logger.getLogger(this.getClass());//�α�

	@Autowired
	private ReportService reportService;//�Խ��� �������̽�
	
	/**
	 * �Խ��� ��ȸ
	 */
	@RequestMapping(value="/reportList")
	public ModelAndView ReportList(@RequestParam(value="pageNum",defaultValue="1")int currentPage
								, @RequestParam(value="keyField", required =false) String keyField
								, @RequestParam(value="keyWord", required =false) String keyWord
								) {

		logger.info("ReportList() : start");						//ReportList ����
		logger.info("keyField : "+keyField);					
		logger.info("keyWord : "+keyWord);
		
		List<ReportVO> reportList = new ArrayList<ReportVO>();		//�Խ��� ����Ʈ
		Map<String, Object> map = new HashMap<String, Object>();//����¡ map
		map.put("keyField", keyField);							//�˻��о�
		map.put("keyWord", keyWord);							//�˻���
		
		int count = reportService.selectReportCnt(map);			//�Խ��� ����Ʈ �� ��ȸ
		logger.info("count : "+count);							//�Խ��� ����Ʈ �� �α׷� ���

		PagingUtil page;//����¡ ó���� ���� ��ü ����
		
		if(keyWord == null) {
			page = new PagingUtil(currentPage, count, 5,2, "reportList.do");							//�˻�� �ִٸ�
		}else {
			page = new PagingUtil(keyField, keyWord, currentPage, count, 5,2, "reportList.do",null);	//�˻�� ���ٸ�
		}
		

		//--------------------------------
		//start->�������� �� ù��° ������ �Խù���ȣ
		//--------------------------------
		map.put("start", page.getStartCount());	//���� �Խù���ȣ
		map.put("end", page.getEndCount());		//�������Խù���ȣ

		//---------------------------
		//�Խù��� 1�� �̻� �����ϸ� ����Ʈ ��ȸ
		//---------------------------
		if(count > 0) {
			
			reportList = reportService.selectReportList(map);//�Խ��� ����Ʈ ��ȸ
			
		};//if
		
		ModelAndView  mav = new ModelAndView("Report");		//Report model ����
		mav.setViewName("main/report");						//jsp ���
		mav.addObject("count", count);						//�ѷ��ڵ��
		mav.addObject("reportList", reportList);				//�Խ��� ����Ʈ
		mav.addObject("pagingHtml", page.getPagingHtml());	//��ũ���ڿ��� ����
		mav.addObject("keyWord", keyWord);					//�˻��� ����
		
		logger.info(reportList.toString());					//VO �α׷� ����
		logger.info("reportList() : end");					//Report ����
		return mav;
	}
	
	/**
	 * �Խ��� ��
	 * @param Report_seq
	 * @return
	 */
	@RequestMapping(value="/reportDetail")
	public ModelAndView ReportDetail(@RequestParam(value="report_seq") int report_seq) {
		
		logger.info("start");	
		logger.info("report_seq : "+report_seq);
		ReportVO reportList = reportService.selectReport(report_seq);//�Խ��� �� ��ȸ
		reportList.getreport_seq();
		
		logger.info(reportList.toString());
		
		ModelAndView  mav = new ModelAndView("ReportDetail");
		mav.setViewName("main/reportDetail");//jsp ���
		mav.addObject("reportList", reportList);//�Խ��� ����Ʈ
		
		logger.info("end");
		
		return mav;
	}

	/**
	 * �Խ��� ������Ʈ
	 * @param Report_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateReport")
	public String updateReport(@RequestParam(value="report_seq") int report_seq
							, @RequestParam(value="report_title") String report_title
							, @RequestParam(value="report_content") String report_content
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("report_seq : "+report_seq);
		logger.info("report_title : "+report_title);
		logger.info("report_content : "+report_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ������Ʈ map
			map.put("report_seq", report_seq);//�Խñ� ������
			map.put("report_title", report_title.replace("\r\n","<br>"));//�Խñ� ����
			map.put("report_content", report_content.replace("\r\n","<br>"));//�Խñ� ����
			map.put("user_name", user_name);//����������
			 
			resultCode = reportService.updateReport(map);//�Խñ� ������Ʈ
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	
	/**
	 * �Խ��� ����
	 * @param Report_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteReport")
	public String deleteReport(@RequestParam(value="report_seq") int report_seq
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("report_seq : "+report_seq);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ���� map
			map.put("report_seq", report_seq);//�Խñ� ������
			map.put("user_name", user_name);//����������
			
			resultCode = reportService.deleteReport(map);//�Խñ� ����
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	/**
	 * �Խñ� �ۼ� ������ �̵�
	 * @return
	 */
	@RequestMapping(value="/reportWrite")
	public ModelAndView ReportWrite() {
				
		ModelAndView  mav = new ModelAndView("ReportWrite");
		mav.setViewName("main/reportWrite");//jsp ���
		
		return mav;
	}
	
	/**
	 * �Խñ� �ۼ�
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertReport")
	public String ReportInsert(@RequestParam(value="report_title", required =false) String report_title
							  , @RequestParam(value="report_content", required =false) String report_content
							  , @RequestParam(value="user_name", required =false) String user_name) {
		
		logger.info("start");
		logger.info("report_title : "+report_title);
		logger.info("report_content : "+report_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ���� map
			map.put("report_title", report_title);//�Խñ� ����
			map.put("report_content", report_content);//�Խñ� ����
			map.put("created_by", user_name);//������
			map.put("last_update_by", user_name);//����������
			
			resultCode = reportService.insertReport(map);//�Խñ� ����
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
				
		logger.info("end");
		
		return resultCode;
		
	}

	
	/**
	 * ���� �޴� 
	 * �޴��� �� �� �ִ� ������
	 * @return
	 */
	@RequestMapping(value="/menuList")
	public ModelAndView MenuList() {

		logger.info("================================ START ================================");								//menuList ����
		
		
		ModelAndView  mav = new ModelAndView("MenuList");				//menuList model ����
		mav.setViewName("main/menuList");								//jsp ���
		
		logger.info("================================ E N D ================================");								//menuList ����
		return mav;
	}
	
	/**
	 * ���� ������ 
	 * �������� �����������Դϴ�.
	 * @return
	 */
	@RequestMapping(value="/mainPage")
	public ModelAndView MainPage() {

		logger.info("================================ START ================================");								//mainPage ����
		
		
		ModelAndView  mav = new ModelAndView("MainPage");				//mainPage model ����
		mav.setViewName("main/mainPage");								//jsp ���
		
		logger.info("================================ E N D ================================");								//mainPage ����
		return mav;
	}
	
	/**
	 * ���������� ����Ʈ
	 * ���������� ����Ʈ�� ���
	 * @return
	 */
	@RequestMapping(value="/scheduleList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	public ModelAndView TableTest(HttpServletRequest request, Model model
								, @RequestParam(value="division", required =false,  defaultValue="AS01") String division
								, @RequestParam(value="addList", required =false,  defaultValue="") String addList) {

		logger.info("================================ START ================================");							//scheduleList ����
		List<Annuail_ScheduleVO> scheduleList = new ArrayList();		//���̺� ����Ʈ
			
		try {
		
			Annuail_ScheduleVO tableVO = new Annuail_ScheduleVO();		//���̺��� �׽�Ʈ�ϱ� ���� ����Ʈ VO	
			scheduleList =  reportService.selectScheduleList(request, division, addList);		//���� ������ ���
			Map resultMap = new HashMap();
			resultMap.put("resultCode", "0000");						//�����ڵ�	 0000:����  / 9000:������
			resultMap.put("scheduleList", scheduleList);				//���̺� ����Ʈ
			resultMap.put("scheduleCnt", scheduleList.size());			//���̺� �� 
			resultMap.put("division", division);						//��������
			
			ModelAndView  mav = new ModelAndView("main/scheduleList",resultMap);			//Report model ����
			logger.info("================================ E N D ================================");						//scheduleList ����
			return mav;													//mav����
			
		}catch(Exception e) {
			ModelAndView  mav = new ModelAndView("ScheduleList");			//Report model ����
			mav.setViewName("main/scheduleList");			
			logger.error(e.toString());									//�����޽���
			return mav;													//mav����
			
		}//catch
		

	}
	
	/**
	 * ���������� ����/���� �޼����Դϴ�.
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/insertSchedule", method = {RequestMethod.POST}) 
	public ModelAndView InsertSchedule(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");						//insertSchedule ����

		HttpSession session = request.getSession();						//����
		
		try {
			
			reportService.insertSchedule(request);						//���������� ����/����
			
		}catch(Exception e) {
			
			logger.error(e.toString());									//�����޽���
			
		};
		
		ModelAndView  mav = new ModelAndView("MenuList");				//Report model ����
		mav.setViewName("main/menuList");								//jsp ���
		logger.info("================================ E N D ================================");						//insertSchedule ����
		return mav;
	}
	
	/**
	 * ���������� ����
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSchedule", method = {RequestMethod.POST}) 
	public ModelAndView DeleteSchedule(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");						//deleteSchedule ����

		HttpSession session = request.getSession();						//����
		
		try {
			
			reportService.deleteSchedule(request);						//���������� ����
			
		}catch(Exception e) {
			
			logger.error(e.toString());									//�����޽���
			
		};
		
		ModelAndView  mav = new ModelAndView("MenuList");				//Report model ����
		mav.setViewName("main/menuList");								//jsp ���
		mav.addObject("resultCode", "0000");							//��ȯ�ڵ�
		logger.info("================================ E N D ================================");						//deleteSchedule ����
		return mav;
		
	}
	
}
