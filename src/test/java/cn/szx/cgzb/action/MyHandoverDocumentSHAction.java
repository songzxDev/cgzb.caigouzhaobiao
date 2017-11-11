// �����貿�𵽷������ϣ����ڱ������в��ԣ�����д�Ĳ����Ϲ淶���պ�����

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
				 * �õ�Ҫ������EXCEL�ļ���ĳ��SHEETҳǩ�е�ĳһ���������� A_DATA
				 */
				DataGrid<MyExcelCellModel> myCopyUsedDataGrid = myExcelCellService.getMyExcelCellModelList(copyUsedParamsModel);
				if (myCopyUsedDataGrid != null && myCopyUsedDataGrid.getAmount() > 0) {
					double nums = myCopyUsedDataGrid.getAmount();
					List<MyExcelCellModel> tempList = myCopyUsedDataGrid.getRows();
					for (int i = 0; i < nums; i++) {
						MyExcelCellModel myCopyExcelCellModel = tempList.get(i);
						if (myCopyExcelCellModel != null) {
							/*
							 * �������Ƶ�ADATA���뵽 A �е���һ�� B ��
							 */
							MY_EXCEL_UTIL.insertCopyRowsToExcelSheet(copyUsedParamsModel.getExcelFilePathStr(), copyUsedParamsModel.getSheetName(), myCopyExcelCellModel.getRowPosition() + i, insertRowNums);
							// ����ǰSHEETҳǩ�е� B�еĵ����У��ĵ��еġ������š��У��ĵ�Ԫ����Ϣ���棬���ڸ��� B �еĲ�������ʱ��ʹ��
							MyExcelCellModel myInsertExcelCellModel = new MyExcelCellModel(myCopyExcelCellModel.getRowPosition() + i + 1, myCopyExcelCellModel.getColumnPosition(), updateUsedParamsModel.getCellValueAfterReplacement(), updateUsedParamsModel.getSheetName());
							myInsertExcelCellModelList.add(myInsertExcelCellModel);
						}
					}
					// for ѭ������
					logger.info(JSON.toJSON(myInsertExcelCellModelList));
				}
				/*
				 * ���� B �еĲ�������,2016��1��18���ⶨֻ���µ�ǰSHEETҳǩ�е� B�еĵ����У��ĵ��еġ������š��У��ĵ�Ԫ����Ϣ
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
		 * editFlag�������еı�ʶ "001" ����ֻ�����ĵ��������ĵ�����ͬ-רҵ-��Ա��-temp.xlsx����ǰ�����У�����ID�����ź�ͬ���͡�רҵ�����������е���Ϣ��ע��ҳǩ36����4����
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
		logger.info("����ִ����ϣ��ܹ������ˣ���" + dataGrid.getAmount() + 0 + "�� ����Ԫ��");
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
		 * ��������еı��� index �� counts ��2016��3��25��д��̶���ֵ���պ������
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
		logger.info("������ݱ�excel_cell_range_address_tab�����ݣ�" + myExcelCellRangeAddressService.removeAll() + "����");
		logger.info("�������ݱ�excel_cell_range_address_tab�����ݣ�" + myExcelCellRangeAddressService.save(paramsModel) + "����");
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
		logger.info("����ִ����ϣ��ܹ�ɾ���ˣ���" + dataGrid.getAmount() + 0 + "������ �����ݣ�");
		return dataGrid;
	}

	// EXECUTE METHOD
	/*
	 * �����java.lang.OutOfMemoryError: Java heap space ѡ�б����е��࣬����˵���Run as ->Open Run Dialog...���� ѡ��(x)=Argument��ǩҳ�µ�vm arguments�������� -Xmx512m, �������о�ok�� //
	 */
	public void testCopiedRowInsertExcel() throws Exception {
		long beginTime = System.currentTimeMillis();
		MyAfferentParametersModel paramsModelS = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, MY_EXCEL_SHEET_NAMES);
		paramsModelS.setDescription(COPIED_INSERT_UPDATE);
		paramsModelS.setOriginalValueOfCell("�Ϻ��зֹ�˾������Ӫ���������̳���Ӫ��");
		paramsModelS.setCellValueAfterReplacement("�Ϻ��зֹ�˾������Ӫ����O2O��չ��");
		copiedRowInsertExcel(paramsModelS, 1, getGeneralLastRowIndex("22"));
		logger.info("###################  ɾ�����ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		removeAllDatasFromExcelCellTab();
		logger.info("###################  ɾ�����ݱ�excel_cell_tab������������������������ ##########################");
		logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		paramsModelS.setDescription(UPDATE_MULTIPLE_DATA);
		addDatasToExcelCellTab(paramsModelS);
		logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab������������������������ ##########################");
		long endTime = System.currentTimeMillis();
		long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		logger.info("��ǰ����ִ���˵ķ�����Ϊ��" + excuteMinutes + "���ӣ�");
	}

	public void testCopyRowAndInsertRowAndUpdateRowMethod() throws Exception {
		/*
		 * �����java.lang.OutOfMemoryError: Java heap space ѡ�б����е��࣬����˵���Run as ->Open Run Dialog...����ѡ��(x)=Argument��ǩҳ�µ�vm arguments�������� -Xmx512m, �������о�ok�� //
		 */
		// long beginTime = System.currentTimeMillis();
		// String[] sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// int counts = 0;
		// for (String sheetName : sheetNames) {
		// MyExcelCellModel selectUsedCellModel = MY_HANDOVERDOCUMENTSH_UTIL.getDepartmentColumn(sheetName);
		// MyAfferentParametersModelHelper paramsHelperModel = getAfferentParamsModel(sheetName, selectUsedCellModel);
		// logger.info(JSON.toJSON(paramsHelperModel));
		// if (paramsHelperModel != null) {
		// logger.info("###################  �����ĵ��е�ҳǩ��" + sheetName + "���ĵ�Ԫ����Ϣ�Ĳ�����ʼ�������������� ##########################");
		// }
		// counts = counts + insertCopyRowAndThenUpdateMethod(paramsHelperModel, 1);
		// }
		// logger.info("###################  �����ĵ��ĵ�Ԫ����Ϣ�Ĳ����������ܼ���������Ϊ����" + counts + "���� ##########################");
		// if (counts > 40) {
		// logger.info("###################  ɾ�����ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		// removeAllDatasFromExcelCellTab();
		// logger.info("###################  ɾ�����ݱ�excel_cell_tab������������������������ ##########################");
		//
		// logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		// for (String sheetName : sheetNames) {
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, null);
		// addDatasToExcelCellTab(paramsModel);
		// }
		// logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab������������������������ ##########################");
		// } else {
		// logger.info("������������40�У����������޸ĺ���ĵ��Ƿ���ȷ��");
		// }
		// long endTime = System.currentTimeMillis();
		//
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		//
		// logger.info("��ǰ����ִ���˵ķ�����Ϊ��" + excuteMinutes + "���ӣ�");
	}

	@Test
	public void testRemoveExcelCellMergedRegion() throws Exception {
		// long beginTime = System.currentTimeMillis();
		// String[] sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetNames);
		// removeExcelCellMergedRegion(paramsModel);
		// long endTime = System.currentTimeMillis();
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		// logger.info("��ǰ����ִ���˵ķ�����Ϊ��" + excuteMinutes + "���ӣ�");
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
		// logger.info("��ǰ����ִ���˵ķ�����Ϊ��" + excuteMinutes + "���ӣ�");
	}

	public void testRemoveExcelRows() throws Exception {
		// long beginTime = System.currentTimeMillis();
		// String[] sheetNames = MY_HANDOVER_DOCUMENT_MODEL.getExcelFileSheetNames();
		// for (String sheetName : sheetNames) {
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, new MyExcelCellModel(9999, 9999, "�Ϻ��мζ����ֹ�˾��С��ҵһ��Ӫ������", sheetName));
		// logger.info(JSON.toJSON(removeExcelRow(paramsModel)));
		// }
		// logger.info("###################  ɾ�����ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		// removeAllDatasFromExcelCellTab();
		// logger.info("###################  ɾ�����ݱ�excel_cell_tab������������������������ ##########################");
		//
		// logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		// for (String sheetName : sheetNames) {
		// MyAfferentParametersModel paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetName, null);
		// addDatasToExcelCellTab(paramsModel);
		// }
		// logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab������������������������ ##########################");
		// long endTime = System.currentTimeMillis();
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		//
		// logger.info("��ǰ����ִ���˵ķ�����Ϊ��" + excuteMinutes + "���ӣ�");
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
		// logger.info("###################  ɾ�����ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		// removeAllDatasFromExcelCellTab();
		// logger.info("###################  ɾ�����ݱ�excel_cell_tab������������������������ ##########################");
		//
		// logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab��������ʼ�������������� ##########################");
		// paramsModel = new MyAfferentParametersModel(MY_EXCEL_FILE_PATH_STR, sheetNames);
		// paramsModel.setDescription(UPDATE_MULTIPLE_DATA);
		// addDatasToExcelCellTab(paramsModel);
		// logger.info("###################  ����excel�е���Ϣ�������ݱ�excel_cell_tab������������������������ ##########################");
		// long endTime = System.currentTimeMillis();
		// long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		//
		// logger.info("��ǰ����ִ���˵ķ�����Ϊ��" + excuteMinutes + "���ӣ�");
	}
}
