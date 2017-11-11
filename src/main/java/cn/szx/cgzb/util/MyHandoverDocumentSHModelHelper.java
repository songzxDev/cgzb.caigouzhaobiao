package cn.szx.cgzb.util;

import cn.szx.cgzb.pagemodel.MyExcelCellModel;
import cn.szx.cgzb.pagemodel.MyHandoverDocumentSHModel;
import cn.szx.cgzb.pagemodel.MyHandoverDocumentSHModelSecondVersion;

public class MyHandoverDocumentSHModelHelper {

	/**
	 * 
	 * @Title: initTemp
	 * @Description: TODO
	 * @return
	 * @return: MyHandoverDocumentSHModel
	 */
	public static final MyHandoverDocumentSHModel initTemp() {
		MyHandoverDocumentSHModelSecondVersion modelTemp = new MyHandoverDocumentSHModelSecondVersion();
		String[] excelFileSheetNames = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39" };
		modelTemp.setExcelFileSheetNames(excelFileSheetNames);
		modelTemp.setFirstRowFirstColumn(new MyExcelCellModel(0, 0, "ID"));
		modelTemp.setFirstRowSecondColumn(new MyExcelCellModel(0, 1, "集团合同类型"));
		modelTemp.setFirstRowThirdColumn(new MyExcelCellModel(0, 2, "专业类型"));
		modelTemp.setFirstRowFourthColumn(new MyExcelCellModel(0, 3, "发起部门"));
		modelTemp.setFirstRowFifthColumn(new MyExcelCellModel(0, 4, "直属经理"));
		modelTemp.setFirstRowSixthColumn(new MyExcelCellModel(0, 5, "直属经理登录帐号"));
		modelTemp.setFirstRowSeventhColumn(new MyExcelCellModel(0, 6, "区县会计"));
		modelTemp.setFirstRowEighthColumn(new MyExcelCellModel(0, 7, "区县会计登录帐号"));
		modelTemp.setFirstRowNinthColumn(new MyExcelCellModel(0, 8, "部门副"));
		modelTemp.setFirstRowTenthColumn(new MyExcelCellModel(0, 9, "部门副登录帐号"));
		modelTemp.setFirstRowEleventhColumn(new MyExcelCellModel(0, 10, "部门总"));
		modelTemp.setFirstRowTwelfthColumn(new MyExcelCellModel(0, 11, "部门总登录帐号"));
		return modelTemp;
	}

	/**
	 * 
	 * @Title: initTemp
	 * @Description: TODO
	 * @return
	 * @return: MyHandoverDocumentSHModel
	 */
	public static final MyHandoverDocumentSHModelSecondVersion initTempSecondVersion() {
		MyHandoverDocumentSHModelSecondVersion modelTemp = new MyHandoverDocumentSHModelSecondVersion();
		String[] excelFileSheetNames = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39" };
		modelTemp.setExcelFileSheetNames(excelFileSheetNames);
		modelTemp.setFirstRowFirstColumn(new MyExcelCellModel(0, 0, "ID"));
		modelTemp.setFirstRowSecondColumn(new MyExcelCellModel(0, 1, "集团合同类型"));
		modelTemp.setFirstRowThirdColumn(new MyExcelCellModel(0, 2, "专业类型"));
		modelTemp.setFirstRowFourthColumn(new MyExcelCellModel(0, 3, "发起部门"));
		modelTemp.setFirstRowFifthColumn(new MyExcelCellModel(0, 4, "直属经理"));
		modelTemp.setFirstRowSixthColumn(new MyExcelCellModel(0, 5, "直属经理登录帐号"));
		modelTemp.setFirstRowSeventhColumn(new MyExcelCellModel(0, 6, "区县会计"));
		modelTemp.setFirstRowEighthColumn(new MyExcelCellModel(0, 7, "区县会计登录帐号"));
		modelTemp.setFirstRowNinthColumn(new MyExcelCellModel(0, 8, "部门副"));
		modelTemp.setFirstRowTenthColumn(new MyExcelCellModel(0, 9, "部门副登录帐号"));
		modelTemp.setFirstRowEleventhColumn(new MyExcelCellModel(0, 10, "部门总"));
		modelTemp.setFirstRowTwelfthColumn(new MyExcelCellModel(0, 11, "部门总登录帐号"));
		return modelTemp;
	}

}
