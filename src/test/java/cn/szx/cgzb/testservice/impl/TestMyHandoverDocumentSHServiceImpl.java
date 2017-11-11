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
		 * 解决：java.lang.OutOfMemoryError: Java heap space 选中被运行的类，点击菜单‘Run as ->Open Run Dialog...’，选择(x)=Argument标签页下的vm arguments框里输入 -Xmx512m, 保存运行就ok了
		 */
//		MyHandoverDocumentSHModel hdsModel = MyHandoverDocumentSHModelHelper.initTemp();
//		hdsModel.setExcelFileName("E:\\项目文档\\个人文档\\任务\\2016年工作\\2016.01\\2016.01.09\\交接文档（合同-专业-人员）temp.xlsx");
//		String[] strs = hdsModel.getExcelFileSheetNames();
//		logger.info(JSON.toJSON(strs));
//		// MyAfferentParametersModel paramsModel1 = new MyAfferentParametersModel(hdsModel.getExcelFileName(), "18", hdsModel.getFirstRowOfFifthColumn(), "王喆", "宋桢熙", "wangzhe753", "songzx_2326");
//		//
//		// logger.info(JSON.toJSON(paramsModel1));
//		//
//		// handoverDocumentSHService.updateFirstRowOfFifthAndSixthColumns(paramsModel1);
//		//
//		// MyAfferentParametersModel paramsModel2 = new MyAfferentParametersModel(hdsModel.getExcelFileName(), "19", hdsModel.getFirstRowOfFifthColumn(), "王喆", "宋桢熙", "wangzhe753", "songzx_2326");
//		// logger.info(JSON.toJSON(paramsModel2));
//		// handoverDocumentSHService.updateFirstRowOfFifthAndSixthColumns(paramsModel2);
//		for (int i = 0; i < strs.length; i++) {
//			String sheetName = strs[i];
//			logger.info(sheetName);
//			if (sheetName != null && !(sheetName.equals("") || sheetName.equals("28"))) {
//				MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(hdsModel.getExcelFileName(), sheetName, hdsModel.getFirstRowOfFifthColumn(), "宋桢熙", "王喆", "songzx_2326", "wangzhe753");
//				logger.info(JSON.toJSON(paramsModel));
//				handoverDocumentSHService.updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams(paramsModel);
//			}
//		}

	}
}
