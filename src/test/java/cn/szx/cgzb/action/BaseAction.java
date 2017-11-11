package cn.szx.cgzb.action;

import cn.szx.cgzb.pagemodel.MyHandoverDocumentSHModelSecondVersion;
import cn.szx.cgzb.util.MyHandleExcelUtil;
import cn.szx.cgzb.util.MyHandoverDocumentSHModelHelper;
import cn.szx.cgzb.util.MyHandoverDocumentSHModelUtil;

public class BaseAction {
	public static final MyHandleExcelUtil MY_EXCEL_UTIL = MyHandleExcelUtil.getInstance();

	public static final MyHandoverDocumentSHModelUtil MY_HANDOVERDOCUMENTSH_UTIL = MyHandoverDocumentSHModelUtil.getInstance();

	public static final String MY_EXCEL_FILE_PATH_STR = "E:\\MyHandoverDocumentSH_TEMP\\交接文档（合同-专业-人员）-temp.xlsx";

	public static final String MY_EXCEL_FILE_CHANGE_PATH_STR = "E:\\MyHandoverDocumentSH_TEMP\\MySHDocumentChange.properties";

	public static final String UPDATE_MULTIPLE_DATA = "MULTIPLE";

	public static final String COPIED_INSERT_UPDATE = "COPIED";

	public static final MyHandoverDocumentSHModelSecondVersion MY_HANDOVER_DOCUMENT_MODEL = MyHandoverDocumentSHModelHelper.initTempSecondVersion();

	public static final String[] MY_EXCEL_SHEET_NAMES = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39" };
}
