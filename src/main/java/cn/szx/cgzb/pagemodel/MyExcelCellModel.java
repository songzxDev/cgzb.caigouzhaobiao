package cn.szx.cgzb.pagemodel;

import java.io.Serializable;

public class MyExcelCellModel implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	/** 一个单元格所在excel的sheet中的行坐标 */
	private int rowPosition = 0;

	/** 一个单元格所在excel的sheet中的列坐标 */
	private int columnPosition = 0;

	/** 一个单元格的内容 */
	private String myExcelCellValue = "";

	/** 所属SHEET页签的名称 */
	private String ownedSheetName;

	private String myDescribe;

	private Long id;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String usableStatus;

	public MyExcelCellModel(int rowPosition, int columnPosition, String myExcelCellValue) {
		super();
		this.rowPosition = rowPosition;
		this.columnPosition = columnPosition;
		this.myExcelCellValue = myExcelCellValue;
	}

	public MyExcelCellModel(int rowPosition, int columnPosition, String myExcelCellValue, String ownedSheetName) {
		super();
		this.rowPosition = rowPosition;
		this.columnPosition = columnPosition;
		this.myExcelCellValue = myExcelCellValue;
		this.ownedSheetName = ownedSheetName;
	}

	public MyExcelCellModel(Long id, String attribute1, String usableStatus) {
		super();
		this.id = id;
		this.attribute1 = attribute1;
		this.usableStatus = usableStatus;
	}

	public MyExcelCellModel() {
		super();
	}

	public MyExcelCellModel(int columnPosition) {
		super();
		this.columnPosition = columnPosition;
	}

	public int getRowPosition() {
		return rowPosition;
	}

	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}

	public int getColumnPosition() {
		return columnPosition;
	}

	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
	}

	public String getMyExcelCellValue() {
		return myExcelCellValue;
	}

	public void setMyExcelCellValue(String myExcelCellValue) {
		this.myExcelCellValue = myExcelCellValue;
	}

	public String getOwnedSheetName() {
		return ownedSheetName;
	}

	public void setOwnedSheetName(String ownedSheetName) {
		this.ownedSheetName = ownedSheetName;
	}

	public String getMyDescribe() {
		return myDescribe;
	}

	public void setMyDescribe(String myDescribe) {
		this.myDescribe = myDescribe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getUsableStatus() {
		return usableStatus;
	}

	public void setUsableStatus(String usableStatus) {
		this.usableStatus = usableStatus;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

}
