// 因无需部署到服务器上，故在本机进行测试，代码写的不符合规范，日后完善

package cn.szx.cgzb.action;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.szx.cgzb.pagemodel.DataGrid;
import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyExcelCellModel;
import cn.szx.cgzb.pagemodel.MyExcelCellRangeAddressModel;
import cn.szx.cgzb.service.MyDepartmentAuditStaffInfoSHServiceI;
import cn.szx.cgzb.service.MyExcelCellRangeAddressServiceI;
import cn.szx.cgzb.service.MyExcelCellServiceI;
import cn.szx.cgzb.util.MyAfferentParametersModelHelper;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MyHandoverDocumentSHAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyHandoverDocumentSHAction.class);

	private MyExcelCellServiceI myExcelCellService;

	public MyExcelCellServiceI getMyExcelCellService() {
		return myExcelCellService;
	}

	@Autowired
	public void setMyExcelCellService(MyExcelCellServiceI myExcelCellService) {
		this.myExcelCellService = myExcelCellService;
	}

	private MyDepartmentAuditStaffInfoSHServiceI myDepartmentAuditStaffInfoSHService;

	public MyDepartmentAuditStaffInfoSHServiceI getMyDepartmentAuditStaffInfoSHService() {
		return myDepartmentAuditStaffInfoSHService;
	}

	@Autowired
	public void setMyDepartmentAuditStaffInfoSHService(MyDepartmentAuditStaffInfoSHServiceI myDepartmentAuditStaffInfoSHService) {
		this.myDepartmentAuditStaffInfoSHService = myDepartmentAuditStaffInfoSHService;
	}

	private MyExcelCellRangeAddressServiceI myExcelCellRangeAddressService;

	public MyExcelCellRangeAddressServiceI getMyExcelCellRangeAddressService() {
		return myExcelCellRangeAddressService;
	}

	@Autowired
	public void setMyExcelCellRangeAddressService(MyExcelCellRangeAddressServiceI myExcelCellRangeAddressService) {
		this.myExcelCellRangeAddressService = myExcelCellRangeAddressService;
	}

	/**
	 * 
	 * @Title: copiedRowInsertExcel
	 * @Description: TODO
	 * @param paramsModel
	 * @param insertRowNums
	 * @param generalLastRowIndex
	 * @throws Exception
	 * @return: void
	 */
	public void copiedRowInsertExcel(MyAfferentParametersModel paramsModel, int insertRowNums, int generalLastRowIndex) throws Exception {
		DataGrid<MyExcelCellModel> dataGrid = new DataGrid<MyExcelCellModel>();
		if (paramsModel != null) {
			String excelFilePathStr = paramsModel.getExcelFilePathStr();
			String[] sheetNames = paramsModel.getSheetNames();
			dataGrid = myExcelCellService.getMyExcelCellModelList(paramsModel);
			if (dataGrid != null && dataGrid.getAmount() > 0) {
				List<MyExcelCellModel> list = dataGrid.getRows();
				List<MyExcelCellModel> tempList = MY_EXCEL_UTIL.copiedRowInsertExcel(excelFilePathStr, sheetNames, list, insertRowNums, generalLastRowIndex);
				if (tempList != null && tempList.size() > 0) {
					MY_EXCEL_UTIL.updateExcelCellValue(excelFilePathStr, tempList, sheetNames);
				}
			}
		}
	}

	/**
	 * 
	 * @Title: insertCopyRowAndThenUpdateMethod
	 * @Description: TODO
	 * @param paramsHelperModel
	 * @param insertRowNums
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int insertCopyRowAndThenUpdateMethod(MyAfferentParametersModelHelper paramsHelperModel, int insertRowNums) throws Exception {
		int counts = 0;
		if (paramsHelperModel != null) {
			MyAfferentParametersModel copyUsedParamsModel = paramsHelperModel.getCopyRowUsedParamsModel();
			// MyAfferentParametersModel insertModel = paramsHelperModel.getInsertRowUsedParamsModel();
			List<MyExcelCellModel> myInsertExcelCellModelList = new ArrayList<MyExcelCellModel>();
			MyAfferentParametersModel updateUsedParamsModel = paramsHelperModel.getUpdateRowUsedParamsModel();
			if (copyUsedParamsModel != null && updateUsedParamsModel != null) {
				/*
				 * 得到要被复制EXCEL文件的某个SHEET页签中的某一个整行数据 A_DATA
				 */
				DataGrid<MyExcelCellModel> myCopyUsedDataGrid = myExcelCellService.getMyExcelCellModelList(copyUsedParamsModel);
				if (myCopyUsedDataGrid != null && myCopyUsedDataGrid.getAmount() > 0) {
					double nums = myCopyUsedDataGrid.getAmount();
					List<MyExcelCellModel> tempList = myCopyUsedDataGrid.getRows();
					for (int i = 0; i < nums; i++) {
						MyExcelCellModel myCopyExcelCellModel = tempList.get(i);
						if (myCopyExcelCellModel != null) {
							/*
							 * 将所复制的ADATA插入到 A 行的下一行 B 行
							 */
							MY_EXCEL_UTIL.insertCopyRowsToExcelSheet(copyUsedParamsModel.getExcelFilePathStr(), copyUsedParamsModel.getSheetName(), myCopyExcelCellModel.getRowPosition() + i, insertRowNums);
							// 将当前SHEET页签中的 B行的第四列（文档中的【发起部门】列）的单元格信息保存，用于更新 B 行的部分数据时候使用
							MyExcelCellModel myInsertExcelCellModel = new MyExcelCellModel(myCopyExcelCellModel.getRowPosition() + i + 1, myCopyExcelCellModel.getColumnPosition(), updateUsedParamsModel.getCellValueAfterReplacement(), updateUsedParamsModel.getSheetName());
							myInsertExcelCellModelList.add(myInsertExcelCellModel);
						}
					}
					// for 循环结束
					logger.info(JSON.toJSON(myInsertExcelCellModelList));
				}
				/*
				 * 更新 B 行的部分数据,2016年1月18日拟定只更新当前SHEET页签中的 B行的第四列（文档中的【发起部门】列）的单元格信息
				 */
				if (myInsertExcelCellModelList != null && myInsertExcelCellModelList.size() > 0) {
					counts = myInsertExcelCellModelList.size();
					MY_EXCEL_UTIL.updateExcelCellValueInSheet(updateUsedParamsModel.getExcelFilePathStr(), updateUsedParamsModel.getSheetName(), myInsertExcelCellModelList);
				}

			}
		}
		return counts;
	}

	protected MyAfferentParametersModelHelper getAfferentParamsModel(String sheetName, MyExcelCellModel selectUsedCellModel) throws Exception {
		logger.info(sheetName.length());
		if (sheetName != null && sheetName.length() > 0 && selectUsedCellModel != null) {
			String originalValueOfCell = getPropertieValueByKey("originalValueOfCell");
			String cellValueAfterReplacement = getPropertieValueByKey("cellValueAfterReplacement");
			MyAfferentParametersModel copyUsedParamsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, selectUsedCellModel, originalValueOfCell, "");
			MyAfferentParametersModel updateUsedParamsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, null, "", cellValueAfterReplacement);
			MyAfferentParametersModel deleteUsedParamsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, selectUsedCellModel, originalValueOfCell, "");
			return new MyAfferentParametersModelHelper(copyUsedParamsModel, null, updateUsedParamsModel, deleteUsedParamsModel);
		} else {
			return null;
		}
	}

	private String getPropertieValueByKey(String key) throws Exception {
		FileInputStream file = new FileInputStream(MY_EXCEL_FILE_CHANGE_PATH_STR);
		Properties prop = new Properties();
		prop.load(new InputStreamReader(file, "GBK"));
		return prop.getProperty(key).trim().toString();
	}

	/**
	 * 
	 * @Title: save
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @return: Serializable
	 */
	public Serializable addDatasToExcelCellTab(MyAfferentParametersModel paramsModel) {
		try {
			myExcelCellService.save(paramsModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @Title: removeAllDatasFromExcelCellTab
	 * @Description: TODO
	 * @return: void
	 */
	public void removeAllDatasFromExcelCellTab() {
		try {
			myExcelCellService.removeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: getGeneralLastRowIndex
	 * @Description: TODO
	 * @param sheetName
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int getGeneralLastRowIndex(String sheetName) throws Exception {
		return myExcelCellService.getGeneralLastRowIndex(sheetName);
	}

	/**
	 * 
	 * @Title: eidtExcelCell
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable eidtExcelCell(MyAfferentParametersModel paramsModel) throws Exception {
		DataGrid<MyExcelCellModel> dataGrid = new DataGrid<MyExcelCellModel>();
		/**
		 * editFlag：更新列的标识 "001" 代表只更新文档《交接文档（合同-专业-人员）-temp.xlsx》的前三个列，即：ID、集团合同类型、专业类型这三个列的信息，注：页签36则是4个列
		 */
		if (paramsModel.getEditFlag().equals("001")) {
			dataGrid = myExcelCellService.getMyExcelCellModelList(paramsModel);
		} else {
			dataGrid = myDepartmentAuditStaffInfoSHService.getEditUsedMyExcelCellModelList(paramsModel);
		}
		logger.info(JSON.toJSON(dataGrid));
		if (dataGrid != null && dataGrid.getAmount() > 0) {
			MY_EXCEL_UTIL.updateExcelCellValue(paramsModel.getExcelFilePathStr(), dataGrid.getRows(), paramsModel.getSheetNames());
		}
		logger.info("方法执行完毕，总共更新了：【" + dataGrid.getAmount() + 0 + "】 个单元格！");
		return dataGrid;
	}

	/**
	 * 
	 * @Title: removeExcelCellMergedRegion
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable removeExcelCellMergedRegion(MyAfferentParametersModel paramsModel) throws Exception {
		/*
		 * 下面代码中的变量 index 和 counts 于2016年3月25日写入固定的值，日后待完善
		 */
		int index = 0;
		int counts = 6;
		for (int i = 0; i < counts; i++) {
			MY_EXCEL_UTIL.removeMergedRegion(paramsModel.getExcelFilePathStr(), paramsModel.getSheetNames(), index);
		}
		return counts;
	}

	/**
	 * 
	 * @Title: addExcelCellMergedRegion
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable addExcelCellMergedRegion(MyAfferentParametersModel paramsModel) throws Exception {
		logger.info("清空数据表【excel_cell_range_address_tab】数据：" + myExcelCellRangeAddressService.removeAll() + "条！");
		logger.info("新增数据表【excel_cell_range_address_tab】数据：" + myExcelCellRangeAddressService.save(paramsModel) + "条！");
		DataGrid<MyExcelCellRangeAddressModel> dataGrid = new DataGrid<MyExcelCellRangeAddressModel>();
		if (paramsModel != null) {
			dataGrid = myExcelCellRangeAddressService.getMyExcelCellRangeAddressModelList(paramsModel);
			if (dataGrid != null && dataGrid.getAmount() > 0) {
				List<MyExcelCellRangeAddressModel> cellRangeAddressList = dataGrid.getRows();
				MY_EXCEL_UTIL.addMergedRegion(paramsModel.getExcelFilePathStr(), paramsModel.getSheetNames(), cellRangeAddressList);
			}
		}
		logger.info(JSON.toJSON(dataGrid));
		return dataGrid;
	}

	/**
	 * 
	 * @Title: removeExcelRow
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable removeExcelRow(MyAfferentParametersModel paramsModel) throws Exception {
		DataGrid<MyExcelCellModel> dataGrid = myDepartmentAuditStaffInfoSHService.getRemoveUsedMyExcelCellModelList(paramsModel);
		logger.info(JSON.toJSON(dataGrid));
		if (dataGrid != null && dataGrid.getAmount() > 0) {
			for (int i = 0; i < dataGrid.getAmount(); i++) {
				MyExcelCellModel removeModel = dataGrid.getRows().get(i);
				if (removeModel != null && removeModel.getOwnedSheetName().trim().equals(paramsModel.getSheetName().trim())) {
					removeModel.setRowPosition(removeModel.getRowPosition() - i);
					MY_EXCEL_UTIL.removeRowsFromExcelSheet(paramsModel.getExcelFilePathStr(), paramsModel.getSheetName(), removeModel, -1);
				}
			}
		}
		logger.info("方法执行完毕，总共删除了：【" + dataGrid.getAmount() + 0 + "】个整 行数据！");
		return dataGrid;
	}

	// EXECUTE METHOD
	/*
	 * 解决：java.lang.OutOfMemoryError: Java heap space 选中被运行的类，点击菜单‘Run as ->Open Run Dialog...’， 选择(x)=Argument标签页下的vm arguments框里输入 -Xmx512m, 保存运行就ok了 //
	 */
	public void testCopiedRowInsertExcel() throws Exception {
		long beginTime = System.currentTimeMillis();
		MyAfferentParametersModel paramsModelS = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, MY_EXCEL_SHEET_NAMES);
		paramsModelS.setDescription(COPIED_INSERT_UPDATE);
		paramsModelS.setOriginalValueOfCell("上海市分公司电商运营中心自有商城运营处");
		paramsModelS.setCellValueAfterReplacement("上海市分公司电商运营中心O2O拓展处");
		copiedRowInsertExcel(paramsModelS, 1, getGeneralLastRowIndex("22"));
		logger.info("###################  删除数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		removeAllDatasFromExcelCellTab();
		logger.info("###################  删除数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		paramsModelS.setDescription(UPDATE_MULTIPLE_DATA);
		addDatasToExcelCellTab(paramsModelS);
		logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		long endTime = System.currentTimeMillis();
		long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		logger.info("当前程序执行了的分钟数为：" + excuteMinutes + "分钟！");
	}

	public void testCopyRowAndInsertRowAndUpdateRowMethod() throws Exception {
		/*
		 * 解决：java.lang.OutOfMemoryError: Java heap space 选中被运行的类，点击菜单‘Run as ->Open Run Dialog...’，选择(x)=Argument标签页下的vm arguments框里输入 -Xmx512m, 保存运行就ok了 //
		 */
		// long beginTime = System.currentTimeMillis();
		// String[] sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// int counts = 0;
		// for (String sheetName : sheetNames) {
		// MyExcelCellModel selectUsedCellModel = MY_HANDOVERDOCUMENTSH_UTIL.getDepartmentColumn(sheetName);
		// MyAfferentParametersModelHelper paramsHelperModel = getAfferentParamsModel(sheetName, selectUsedCellModel);
		// logger.info(JSON.toJSON(paramsHelperModel));
		// if (paramsHelperModel != null) {
		// logger.info("###################  更新文档中的页签【" + sheetName + "】的单元格信息的操作开始！！！！！！！ ##########################");
		// }
		// counts = counts + insertCopyRowAndThenUpdateMethod(paramsHelperModel, 1);
		// }
		// logger.info("###################  更新文档的单元格信息的操作结束，总计新增行数为：【" + counts + "】行 ##########################");
		// if (counts > 40) {
		// logger.info("###################  删除数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		// removeAllDatasFromExcelCellTab();
		// logger.info("###################  删除数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		//
		// logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		// for (String sheetName : sheetNames) {
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, null);
		// addDatasToExcelCellTab(paramsModel);
		// }
		// logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		// } else {
		// logger.info("新增行数少于40行，请产生检查修改后的文档是否正确！");
		// }
		// long endTime = System.currentTimeMillis();
		//
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		//
		// logger.info("当前程序执行了的分钟数为：" + excuteMinutes + "分钟！");
	}

	@Test
	public void testRemoveExcelCellMergedRegion() throws Exception {
		// long beginTime = System.currentTimeMillis();
		// String[] sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetNames);
		// removeExcelCellMergedRegion(paramsModel);
		// long endTime = System.currentTimeMillis();
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		// logger.info("当前程序执行了的分钟数为：" + excuteMinutes + "分钟！");
	}

	@Test
	public void testAddExcelCellMergedRegion() throws Exception {
		// long beginTime = System.currentTimeMillis();
		// String[] sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetNames);
		// paramsModel.setEditFlag("001");
		// paramsModel.setColumnIndexs(new int[] { 0, 1, 2 });
		// addExcelCellMergedRegion(paramsModel);
		// long endTime = System.currentTimeMillis();
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		// logger.info("当前程序执行了的分钟数为：" + excuteMinutes + "分钟！");
	}

	public void testRemoveExcelRows() throws Exception {
		// long beginTime = System.currentTimeMillis();
		// String[] sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// for (String sheetName : sheetNames) {
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, new MyExcelCellModel(9999, 9999, "上海市嘉定区分公司中小企业一号营销网格", sheetName));
		// logger.info(JSON.toJSON(removeExcelRow(paramsModel)));
		// }
		// logger.info("###################  删除数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		// removeAllDatasFromExcelCellTab();
		// logger.info("###################  删除数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		//
		// logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		// for (String sheetName : sheetNames) {
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, null);
		// addDatasToExcelCellTab(paramsModel);
		// }
		// logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		// long endTime = System.currentTimeMillis();
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		//
		// logger.info("当前程序执行了的分钟数为：" + excuteMinutes + "分钟！");
	}

	@Test
	public void testEditExcelCell() throws Exception {
		// long beginTime = System.currentTimeMillis();
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel();
		// String editFlag = "UPDATE_CELL_ONLY";
		// String[] sheetNames = {};
		// sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// if (editFlag.equals("001")) {
		// sheetNames = new String[] { "25", "27", "30", "31", "36", "37" };
		// paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetNames, editFlag, new int[] { 0, 1, 2 }, "description");
		// logger.info(JSON.toJSON(eidtExcelCell(paramsModel)));
		// } else if (editFlag.equals("UPDATE_CELL_ONLY")) {
		// paramsModel = new MyAfferentParametersModel();
		// paramsModel.setExcelFilePathStr(MY_EXCEL_FILE_PATH_STR);
		// paramsModel.setSheetNames(MY_EXCEL_SHEET_NAMES);
		// paramsModel.setEditFlag(editFlag);
		// logger.info(JSON.toJSON(eidtExcelCell(paramsModel)));
		// }
		// logger.info("###################  删除数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		// removeAllDatasFromExcelCellTab();
		// logger.info("###################  删除数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		//
		// logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作开始！！！！！！！ ##########################");
		// paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetNames);
		// paramsModel.setDescription(UPDATE_MULTIPLE_DATA);
		// addDatasToExcelCellTab(paramsModel);
		// logger.info("###################  依据excel中的信息更新数据表【excel_cell_tab】操作结束！！！！！！！ ##########################");
		// long endTime = System.currentTimeMillis();
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		//
		// logger.info("当前程序执行了的分钟数为：" + excuteMinutes + "分钟！");
	}
}
