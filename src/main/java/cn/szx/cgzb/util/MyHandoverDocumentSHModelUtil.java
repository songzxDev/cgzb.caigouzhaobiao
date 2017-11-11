package cn.szx.cgzb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.szx.cgzb.pagemodel.MyExcelCellModel;

public class MyHandoverDocumentSHModelUtil {

	private MyHandoverDocumentSHModelUtil() {
	}

	private static MyHandoverDocumentSHModelUtil myHandoverDocumentSHModelUtil = null;

	public synchronized static MyHandoverDocumentSHModelUtil getInstance() {
		if (myHandoverDocumentSHModelUtil == null) {
			myHandoverDocumentSHModelUtil = new MyHandoverDocumentSHModelUtil();
		}
		return myHandoverDocumentSHModelUtil;
	}

	/**
	 * 
	 * @Title: isNumeric
	 * @Description: 用正则表达式判断字符串是否为数字（含负数）
	 * @param str
	 * @return
	 * @return: boolean
	 */
	protected boolean isNumeric(String str) {
		String regEx = "^[0-9]*$";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(str);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: getDepartmentColumn
	 * @Description: 获取《交接文档（合同-专业-人员）-XXXX.xlsx》文档中的各个部门配置页签中的【需求部门列】的列坐标
	 * @param sheetName
	 * @return
	 * @return: MyExcelCellModel
	 */
	public MyExcelCellModel getDepartmentColumn(String sheetName) {
		int columnIndex = 0;
		int rowIndex = 0;
		MyExcelCellModel excelCellModel = new MyExcelCellModel();
		if (isNumeric(sheetName)) {
			switch (Integer.parseInt(sheetName.trim())) {
			case 36:
				columnIndex = 4;
				break;
			default:
				columnIndex = 3;
				break;
			}
			excelCellModel.setRowPosition(rowIndex);
			excelCellModel.setColumnPosition(columnIndex);
			excelCellModel.setOwnedSheetName(sheetName);
			excelCellModel.setMyDescribe("【" + sheetName + "】页签中的【需求部门列】的列坐标");
		}
		return excelCellModel;
	}

	public static void main(String[] args) {

	}
}
