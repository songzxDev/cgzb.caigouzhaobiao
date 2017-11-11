package cn.szx.cgzb.pagemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MyAfferentParametersModel
 * @Description: TODO
 * @author: ������
 * @date: 2016��3��25�� ����1:46:08
 */
public class MyAfferentParametersModel {

	/** ��������excel�ļ��ľ���·�� */
	private String excelFilePathStr;

	/** ��������sheetҳǩ */
	private String sheetName;

	private String[] sheetNames;

	/** �������ĵ�ǰsheetҳǩ�еĵ�Ԫ�� */
	private MyExcelCellModel cellModel;

	/** ��ִ�и��²����ĵ�ǰsheetҳǩ�еĵ�Ԫ�� */
	private MyExcelCellModel updatedCellModel;

	/** �������ĵ�ǰsheetҳǩ�еĲ��ֵ�Ԫ��ļ��� */
	private List<MyExcelCellModel> cellModelList = new ArrayList<MyExcelCellModel>();

	/** �������ĵ�ǰ��Ԫ��δ�޸�ǰ��ֵ */
	private String originalValueOfCell;

	/** �������ĵ�ǰ��Ԫ���滻���ֵ */
	private String cellValueAfterReplacement;

	/** �������ĵ�ǰ�������еĵ�Ԫ��δ�޸�ǰ��ֵ */
	private String originalNameValueOfCell;

	/** �������ĵ�ǰ�������еĵ�Ԫ���滻���ֵ */
	private String nameCellValueAfterReplacement;

	/** �������ĵ�ǰ�ʺ����еĵ�Ԫ��δ�޸�ǰ��ֵ */
	private String originalAccountsValueOfCell;

	/** �������ĵ�ǰ�ʺ����еĵ�Ԫ���滻���ֵ */
	private String accountsCellValueAfterReplacement;

	/** �Ƿ��������е����� */
	private boolean whetherHandleAdjacentColumn = true;

	/**
	 * �����еı�ʶ "001" ����ֻ�����ĵ��������ĵ�����ͬ-רҵ-��Ա��-temp.xlsx����ǰ�����У�����ID�����ź�ͬ���͡�רҵ�����������е���Ϣ��ע��ҳǩ36����4����
	 */
	private String editFlag;

	private int[] columnIndexs = {};
	/**
	 * description ��ֵΪ��MULTIPLE�������¶��ҳǩ description ��ֵΪ��COPIED ����ִ��excel�ļ����븴���еĲ���
	 */
	private String description;

	public MyAfferentParametersModel(String excelFilePathStr, String sheetName, MyExcelCellModel cellModel, String cellValueAfterReplacement) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetName = sheetName;
		this.cellModel = cellModel;
		this.cellValueAfterReplacement = cellValueAfterReplacement;
	}

	public MyAfferentParametersModel(String excelFilePathStr, String sheetName, MyExcelCellModel cellModel, String originalValueOfCell, List<MyExcelCellModel> cellModelList) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetName = sheetName;
		this.cellModel = cellModel;
		this.originalValueOfCell = originalValueOfCell;
		this.cellModelList = cellModelList;
	}

	public MyAfferentParametersModel(String excelFilePathStr, String sheetName, MyExcelCellModel cellModel) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetName = sheetName;
		this.cellModel = cellModel;
	}

	public MyAfferentParametersModel(String excelFilePathStr, String sheetName, MyExcelCellModel cellModel, String originalValueOfCell, String cellValueAfterReplacement) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetName = sheetName;
		this.cellModel = cellModel;
		this.originalValueOfCell = originalValueOfCell;
		this.cellValueAfterReplacement = cellValueAfterReplacement;
	}

	public MyAfferentParametersModel(String originalNameValueOfCell, String originalAccountsValueOfCell) {
		super();
		this.originalNameValueOfCell = getRegExStrFromOriginalValueOfCell(originalNameValueOfCell);
		this.originalAccountsValueOfCell = getRegExStrFromOriginalValueOfCell(originalAccountsValueOfCell);
	}

	public MyAfferentParametersModel(String excelFilePathStr, String sheetName, MyExcelCellModel cellModel, String originalValueOfCell, String cellValueAfterReplacement, String originalNameValueOfCell, String nameCellValueAfterReplacement, String originalAccountsValueOfCell, String accountsCellValueAfterReplacement) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetName = sheetName;
		this.cellModel = cellModel;
		this.originalValueOfCell = originalValueOfCell;
		this.cellValueAfterReplacement = cellValueAfterReplacement;
		this.originalNameValueOfCell = getRegExStrFromOriginalValueOfCell(originalNameValueOfCell);
		this.nameCellValueAfterReplacement = nameCellValueAfterReplacement;
		this.originalAccountsValueOfCell = getRegExStrFromOriginalValueOfCell(originalAccountsValueOfCell);
		this.accountsCellValueAfterReplacement = accountsCellValueAfterReplacement;
	}

	public MyAfferentParametersModel(String excelFilePathStr, String sheetName, MyExcelCellModel cellModel, String originalNameValueOfCell, String nameCellValueAfterReplacement, String originalAccountsValueOfCell, String accountsCellValueAfterReplacement) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetName = sheetName;
		this.cellModel = cellModel;
		this.originalNameValueOfCell = originalNameValueOfCell;
		this.nameCellValueAfterReplacement = nameCellValueAfterReplacement;
		this.originalAccountsValueOfCell = originalAccountsValueOfCell;
		this.accountsCellValueAfterReplacement = accountsCellValueAfterReplacement;
	}

	public MyAfferentParametersModel(String excelFilePathStr, String[] sheetNames) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetNames = sheetNames;
	}

	public MyAfferentParametersModel(String excelFilePathStr, String[] sheetNames, String editFlag, int[] columnIndexs) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetNames = sheetNames;
		this.editFlag = editFlag;
		this.columnIndexs = columnIndexs;
	}

	public MyAfferentParametersModel(String excelFilePathStr, String[] sheetNames, String editFlag, int[] columnIndexs, String description) {
		super();
		this.excelFilePathStr = excelFilePathStr;
		this.sheetNames = sheetNames;
		this.editFlag = editFlag;
		this.columnIndexs = columnIndexs;
		this.description = description;
	}

	public MyAfferentParametersModel() {
		super();
	}

	public String getExcelFilePathStr() {
		return excelFilePathStr;
	}

	public void setExcelFilePathStr(String excelFilePathStr) {
		this.excelFilePathStr = excelFilePathStr;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public MyExcelCellModel getCellModel() {
		return cellModel;
	}

	public void setCellModel(MyExcelCellModel cellModel) {
		this.cellModel = cellModel;
	}

	public String getCellValueAfterReplacement() {
		return cellValueAfterReplacement;
	}

	public void setCellValueAfterReplacement(String cellValueAfterReplacement) {
		this.cellValueAfterReplacement = cellValueAfterReplacement;
	}

	public String getOriginalValueOfCell() {
		return originalValueOfCell;
	}

	public void setOriginalValueOfCell(String originalValueOfCell) {
		this.originalValueOfCell = originalValueOfCell;
	}

	public String getOriginalNameValueOfCell() {
		return originalNameValueOfCell;
	}

	public void setOriginalNameValueOfCell(String originalNameValueOfCell) {
		this.originalNameValueOfCell = originalNameValueOfCell;
	}

	public String getNameCellValueAfterReplacement() {
		return nameCellValueAfterReplacement;
	}

	public void setNameCellValueAfterReplacement(String nameCellValueAfterReplacement) {
		this.nameCellValueAfterReplacement = nameCellValueAfterReplacement;
	}

	public String getOriginalAccountsValueOfCell() {
		return originalAccountsValueOfCell;
	}

	public void setOriginalAccountsValueOfCell(String originalAccountsValueOfCell) {
		this.originalAccountsValueOfCell = originalAccountsValueOfCell;
	}

	public String getAccountsCellValueAfterReplacement() {
		return accountsCellValueAfterReplacement;
	}

	public void setAccountsCellValueAfterReplacement(String accountsCellValueAfterReplacement) {
		this.accountsCellValueAfterReplacement = accountsCellValueAfterReplacement;
	}

	public List<MyExcelCellModel> getCellModelList() {
		return cellModelList;
	}

	public void setCellModelList(List<MyExcelCellModel> cellModelList) {
		this.cellModelList = cellModelList;
	}

	public boolean isWhetherHandleAdjacentColumn() {
		return whetherHandleAdjacentColumn;
	}

	public void setWhetherHandleAdjacentColumn(boolean whetherHandleAdjacentColumn) {
		this.whetherHandleAdjacentColumn = whetherHandleAdjacentColumn;
	}

	public MyExcelCellModel getUpdatedCellModel() {
		return updatedCellModel;
	}

	public void setUpdatedCellModel(MyExcelCellModel updatedCellModel) {
		this.updatedCellModel = updatedCellModel;
	}

	public String[] getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(String[] sheetNames) {
		this.sheetNames = sheetNames;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public int[] getColumnIndexs() {
		return columnIndexs;
	}

	public void setColumnIndexs(int[] columnIndexs) {
		this.columnIndexs = columnIndexs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegExStrFromOriginalValueOfCell(String cellValueAfterReplacement) {
		StringBuffer sb = new StringBuffer(cellValueAfterReplacement);
		sb.insert(0, ".*");
		sb.append(".*");
		return sb.toString();
	}

}
