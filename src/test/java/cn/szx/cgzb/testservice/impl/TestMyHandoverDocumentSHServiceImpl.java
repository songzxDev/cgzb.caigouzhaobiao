package cn.szx.cgzb.testservice.impl;

import org.apache.log4j.Logger;
import org.junit.Test;

import cn.szx.cgzb.service.MyHandoverDocumentSHServiceI;
import cn.szx.cgzb.service.impl.MyHandoverDocumentSHServiceImpl;

public class TestMyHandoverDocumentSHServiceImpl {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestMyHandoverDocumentSHServiceImpl.class);

	private MyHandoverDocumentSHServiceI handoverDocumentSHService = new MyHandoverDocumentSHServiceImpl();

	@Test
	public void testUpdateFirstRowOfFifthAndSixthColumns() {
		/*
		 * �����java.lang.OutOfMemoryError: Java heap space ѡ�б����е��࣬����˵���Run as ->Open Run Dialog...����ѡ��(x)=Argument��ǩҳ�µ�vm arguments�������� -Xmx512m, �������о�ok��
		 */
//		MyHandoverDocumentSHModel hdsModel = MyHandoverDocumentSHModelHelper.initTemp();
//		hdsModel.setExcelFileName("E:\\��Ŀ�ĵ�\\�����ĵ�\\����\\2016�깤��\\2016.01\\2016.01.09\\�����ĵ�����ͬ-רҵ-��Ա��temp.xlsx");
//		String[] strs = hdsModel.getExcelFileSheetNames();
//		logger.info(JSON.toJSON(strs));
//		// MyAfferentParametersModel paramsModel1 = new MyAfferentParametersModel(hdsModel.getExcelFileName(), "18", hdsModel.getFirstRowOfFifthColumn(), "����", "������", "wangzhe753", "songzx_2326");
//		//
//		// logger.info(JSON.toJSON(paramsModel1));
//		//
//		// handoverDocumentSHService.updateFirstRowOfFifthAndSixthColumns(paramsModel1);
//		//
//		// MyAfferentParametersModel paramsModel2 = new MyAfferentParametersModel(hdsModel.getExcelFileName(), "19", hdsModel.getFirstRowOfFifthColumn(), "����", "������", "wangzhe753", "songzx_2326");
//		// logger.info(JSON.toJSON(paramsModel2));
//		// handoverDocumentSHService.updateFirstRowOfFifthAndSixthColumns(paramsModel2);
//		for (int i = 0; i < strs.length; i++) {
//			String sheetName = strs[i];
//			logger.info(sheetName);
//			if (sheetName != null && !(sheetName.equals("") || sheetName.equals("28"))) {
//				MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(hdsModel.getExcelFileName(), sheetName, hdsModel.getFirstRowOfFifthColumn(), "������", "����", "songzx_2326", "wangzhe753");
//				logger.info(JSON.toJSON(paramsModel));
//				handoverDocumentSHService.updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams(paramsModel);
//			}
//		}

	}
}
