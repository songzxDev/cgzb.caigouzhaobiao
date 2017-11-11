package cn.szx.cgzb.pagemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MyAfferentParametersModel
 * @Description: TODO
 * @author: 宋桢熙
 * @date: 2016年3月25日 下午1:46:08
 */
public class MyAfferentParametersModel {

	/** 被操作的excel文件的绝对路径 */
	private String excelFilePathStr;

	/** 被操作的sheet页签 */
	private String sheetName;

	private String[] sheetNames;

	/** 被操作的当前sheet页签中的单元格 */
	private MyExcelCellModel cellModel;

	/** 被执行更新操作的当前sheet页签中的单元格 */
	private MyExcelCellModel updatedCellModel;

	/** 被操作的当前sheet页签中的部分单元格的集合 */
	private List<MyExcelCellModel> cellModelList = new ArrayList<MyExcelCellModel>();

	/** 所操作的当前单元格未修改前的值 */
	private String originalValueOfCell;

	/** 所操作的当前单元格被替换后的值 */
	private String cellValueAfterReplacement;

	/** 所操作的当前姓名列中的单元格未修改前的值 */
	private String originalNameValueOfCell;

	/** 所操作的当前姓名列中的单元格被替换后的值 */
	private String nameCellValueAfterReplacement;

	/** 所操作的当前帐号列中的单元格未修改前的值 */
	private String originalAccountsValueOfCell;

	/** 所操作的当前帐号列中的单元格被替换后的值 */
	private String accountsCellValueAfterReplacement;

	/** 是否处理相邻列的数据 */
	private boolean whetherHandleAdjacentColumn = true;

	/**
	 * 更新列的标识 "001" 代表只更新文档《交接文档（合同-专业-人员）-temp.xlsx》的前三个列，即：ID、集团合同类型、专业类型这三个列的信息，注：页签36则是4个列
	 */
	private String editFlag;

	private int[] columnIndexs = {};
	/**
	 * description 的值为：MULTIPLE代表会更新多个页签 description 的值为：COPIED 代表执行excel文件插入复制行的操作
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
