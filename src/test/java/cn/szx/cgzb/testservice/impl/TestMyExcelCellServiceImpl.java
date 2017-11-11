package cn.szx.cgzb.testservice.impl;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyHandoverDocumentSHModel;
import cn.szx.cgzb.service.MyExcelCellServiceI;
import cn.szx.cgzb.util.MyHandleExcelUtil;
import cn.szx.cgzb.util.MyHandoverDocumentSHModelHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestMyExcelCellServiceImpl {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestMyExcelCellServiceImpl.class);

	private static final String EXCEL_FILE_PATH_STR = "E:\\��Ŀ�ĵ�\\�����ĵ�\\����\\2016�깤��\\2016.01\\2016.01.15\\�����ĵ�����ͬ-רҵ-��Ա��-temp.xlsx";

	private static final MyHandleExcelUtil EXCEL_UTIL = MyHandleExcelUtil.getInstance();

	private MyExcelCellServiceI myExcelCellService;

	public MyExcelCellServiceI getMyExcelCellService() {
		return myExcelCellService;
	}

	@Autowired
	public void setMyExcelCellService(MyExcelCellServiceI myExcelCellService) {
		this.myExcelCellService = myExcelCellService;
	}

	@Test
	public void testSave1() throws Exception {
		// myExcelCellService.removeAll();
		// MyHandoverDocumentSHModel tempModel = MyHandoverDocumentSHModelHelper.initTemp();
		// String[] arrayStrs = tempModel.getExcelFileSheetNames();
		// for (String sheetName : arrayStrs) {
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(EXCEL_FILE_PATH_STR, sheetName, null);
		// myExcelCellService.save(paramsModel);
		// }
		// logger.info("############         ɾ���˵�������������Ϊ��" + myExcelCellService.removeAll() + "         ############");
		/*
		 * �����java.lang.OutOfMemoryError: Java heap space ѡ�б����е��࣬����˵���Run as ->Open Run Dialog...����ѡ��(x)=Argument��ǩҳ�µ�vm arguments�������� -Xmx512m, �������о�ok��
		 */
		// myExcelCellService.save(new MyAfferentParametersModel("E:\\��Ŀ�ĵ�\\�ɹ���ɫͨ��20150415\\�Ϻ���\\���������ĵ�\\�����ĵ�����ͬ-רҵ-��Ա��-2016��01��08��.xlsx", "37", null, ""));
	}

	@Test
	public void testGetMyExcelCellModelList() throws Exception {
		// MyHandoverDocumentSHModel tempModel = MyHandoverDocumentSHModelHelper.initTemp();
		// String[] arrayStrs = tempModel.getExcelFileSheetNames();
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(EXCEL_FILE_PATH_STR, arrayStrs[36], tempModel.getFirstRowOfFourthColumn(), "�Ϻ��������ֹ�˾�������Ӫ������", new ArrayList<MyExcelCellModel>());
		// DataGrid<MyExcelCellModel> dataGrid = myExcelCellService.getMyExcelCellModelList(paramsModel);
		// double nums = dataGrid.getAmount();
		// if (dataGrid != null && nums > 0) {
		// List<MyExcelCellModel> tempList = dataGrid.getRows();
		// for (int i = 0; i < nums; i++) {
		// MyExcelCellModel myExcelCellModel = tempList.get(i);
		// if (myExcelCellModel != null) {
		// // EXCEL_UTIL.insertCopyRowsToExcelSheet(EXCEL_FILE_PATH_STR, arrayStrs[36], myExcelCellModel.getRowPosition() + i, 1);
		// }
		// }
		//
		// }
		// logger.info(JSON.toJSON(dataGrid));
	}

	@Test
	public void testEdit() throws Exception {
		// MyHandoverDocumentSHModel tempModel = MyHandoverDocumentSHModelHelper.initTemp();
		// String[] arrayStrs = tempModel.getExcelFileSheetNames();
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(EXCEL_FILE_PATH_STR, arrayStrs[36], tempModel.getFirstRowOfFourthColumn(), "�Ϻ��������ֹ�˾�������Ӫ������", new ArrayList<MyExcelCellModel>());
		// DataGrid<MyExcelCellModel> dataGrid = myExcelCellService.getMyExcelCellModelList(paramsModel);
		// double nums = dataGrid.getAmount();
		// if (dataGrid != null && nums > 0) {
		// List<MyExcelCellModel> tempList = dataGrid.getRows();
		// for (MyExcelCellModel model : tempList) {
		// if (model != null) {
		// myExcelCellService.edit(new MyExcelCellModel(model.getId(), "003", "0"));
		// }
		// }
		// }
		// logger.info(JSON.toJSON(dataGrid));
	}
}
