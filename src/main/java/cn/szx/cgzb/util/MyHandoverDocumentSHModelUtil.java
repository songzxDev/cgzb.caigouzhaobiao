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
	 * @Description: ��������ʽ�ж��ַ����Ƿ�Ϊ���֣���������
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
	 * @Description: ��ȡ�������ĵ�����ͬ-רҵ-��Ա��-XXXX.xlsx���ĵ��еĸ�����������ҳǩ�еġ��������С���������
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
			excelCellModel.setMyDescribe("��" + sheetName + "��ҳǩ�еġ��������С���������");
		}
		return excelCellModel;
	}

	public static void main(String[] args) {

	}
}
