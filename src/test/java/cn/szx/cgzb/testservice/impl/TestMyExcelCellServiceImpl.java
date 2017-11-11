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

	private static final String EXCEL_FILE_PATH_STR = "E:\\项目文档\\个人文档\\任务\\2016年工作\\2016.01\\2016.01.15\\交接文档（合同-专业-人员）-temp.xlsx";

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
		// logger.info("############         删除了的所有数据总数为：" + myExcelCellService.removeAll() + "         ############");
		/*
		 * 解决：java.lang.OutOfMemoryError: Java heap space 选中被运行的类，点击菜单‘Run as ->Open Run Dialog...’，选择(x)=Argument标签页下的vm arguments框里输入 -Xmx512m, 保存运行就ok了
		 */
		// myExcelCellService.save(new MyAfferentParametersModel("E:\\项目文档\\采购绿色通道20150415\\上海市\\最新配置文档\\交接文档（合同-专业-人员）-2016年01月08日.xlsx", "37", null, ""));
	}

	@Test
	public void testGetMyExcelCellModelList() throws Exception {
		// MyHandoverDocumentSHModel tempModel = MyHandoverDocumentSHModelHelper.initTemp();
		// String[] arrayStrs = tempModel.getExcelFileSheetNames();
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(EXCEL_FILE_PATH_STR, arrayStrs[36], tempModel.getFirstRowOfFourthColumn(), "上海市南区分公司商企二号营销网格", new ArrayList<MyExcelCellModel>());
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
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(EXCEL_FILE_PATH_STR, arrayStrs[36], tempModel.getFirstRowOfFourthColumn(), "上海市南区分公司商企二号营销网格", new ArrayList<MyExcelCellModel>());
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
