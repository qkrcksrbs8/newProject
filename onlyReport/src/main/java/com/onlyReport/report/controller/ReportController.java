package com.onlyReport.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlyReport.report.model.ReportVO;
import com.onlyReport.report.service.ReportService;
import com.onlyReport.report.util.PagingUtil;

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
	
}