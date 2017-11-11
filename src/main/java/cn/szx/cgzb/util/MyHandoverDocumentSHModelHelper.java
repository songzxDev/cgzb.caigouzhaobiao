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
		modelTemp.setFirstRowSecondColumn(new MyExcelCellModel(0, 1, "���ź�ͬ����"));
		modelTemp.setFirstRowThirdColumn(new MyExcelCellModel(0, 2, "רҵ����"));
		modelTemp.setFirstRowFourthColumn(new MyExcelCellModel(0, 3, "������"));
		modelTemp.setFirstRowFifthColumn(new MyExcelCellModel(0, 4, "ֱ������"));
		modelTemp.setFirstRowSixthColumn(new MyExcelCellModel(0, 5, "ֱ�������¼�ʺ�"));
		modelTemp.setFirstRowSeventhColumn(new MyExcelCellModel(0, 6, "���ػ��"));
		modelTemp.setFirstRowEighthColumn(new MyExcelCellModel(0, 7, "���ػ�Ƶ�¼�ʺ�"));
		modelTemp.setFirstRowNinthColumn(new MyExcelCellModel(0, 8, "���Ÿ�"));
		modelTemp.setFirstRowTenthColumn(new MyExcelCellModel(0, 9, "���Ÿ���¼�ʺ�"));
		modelTemp.setFirstRowEleventhColumn(new MyExcelCellModel(0, 10, "������"));
		modelTemp.setFirstRowTwelfthColumn(new MyExcelCellModel(0, 11, "�����ܵ�¼�ʺ�"));
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
		modelTemp.setFirstRowSecondColumn(new MyExcelCellModel(0, 1, "���ź�ͬ����"));
		modelTemp.setFirstRowThirdColumn(new MyExcelCellModel(0, 2, "רҵ����"));
		modelTemp.setFirstRowFourthColumn(new MyExcelCellModel(0, 3, "������"));
		modelTemp.setFirstRowFifthColumn(new MyExcelCellModel(0, 4, "ֱ������"));
		modelTemp.setFirstRowSixthColumn(new MyExcelCellModel(0, 5, "ֱ�������¼�ʺ�"));
		modelTemp.setFirstRowSeventhColumn(new MyExcelCellModel(0, 6, "���ػ��"));
		modelTemp.setFirstRowEighthColumn(new MyExcelCellModel(0, 7, "���ػ�Ƶ�¼�ʺ�"));
		modelTemp.setFirstRowNinthColumn(new MyExcelCellModel(0, 8, "���Ÿ�"));
		modelTemp.setFirstRowTenthColumn(new MyExcelCellModel(0, 9, "���Ÿ���¼�ʺ�"));
		modelTemp.setFirstRowEleventhColumn(new MyExcelCellModel(0, 10, "������"));
		modelTemp.setFirstRowTwelfthColumn(new MyExcelCellModel(0, 11, "�����ܵ�¼�ʺ�"));
		return modelTemp;
	}

}
