package cn.szx.cgzb.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.szx.cgzb.pagemodel.MyExcelCellModel;
import cn.szx.cgzb.pagemodel.MyExcelCellRangeAddressModel;

import com.alibaba.fastjson.JSON;

public class MyHandleExcelUtil implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyHandleExcelUtil.class);

	// ����ʽ����.�ڵ�һ�ε��õ�ʱ��ʵ����
	// ˽�е�Ĭ�Ϲ�����
	private MyHandleExcelUtil() {

	}

	// ע�⣬����û��final
	private static MyHandleExcelUtil myHandleExcelUtil = null;

	// ��̬��������
	public synchronized static MyHandleExcelUtil getInstance() {
		if (myHandleExcelUtil == null) {
			myHandleExcelUtil = new MyHandleExcelUtil();
		}
		return myHandleExcelUtil;
	}

	// public void getAndReadExcelFile(String srcStr) throws Exception {
	// // ���� XSSFWorkbook ����strPath �����ļ�·��
	// XSSFWorkbook xwb = new XSSFWorkbook(srcStr);
	// // ��ȡ��һ�±������
	// // XSSFSheet sheet1 = xwb.getSheetAt(0);
	// XSSFSheet sheet = xwb.getSheet("2");
	// // ���� row��cell
	// XSSFRow row;
	// String cell;
	// // ѭ���������е�����
	// for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
	// row = sheet.getRow(i);
	// for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
	// // ͨ�� row.getCell(j).toString() ��ȡ��Ԫ�����ݣ�
	// cell = row.getCell(j).toString();
	// System.out.print(cell + "\t");
	// }
	// System.out.println("");
	// }
	// xwb.close();
	// }

	/**
	 * 
	 * @Title: getExcelAllCellFromSheet
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetName
	 * @return
	 * @return: List<MyExcelCellModel>
	 */
	public List<MyExcelCellModel> getExcelAllCellFromSheet(String excelFilePathStr, String sheetName) {
		List<MyExcelCellModel> cellsList = new ArrayList<MyExcelCellModel>();
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(excelFilePathStr);
			// �õ�������Sheet
			XSSFSheet xSheet = xwb.getSheet(sheetName);
			if (xSheet != null) {
				// ѭ����Row
				for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {

					XSSFRow xRow = xSheet.getRow(rowNum);
					if (xRow == null) {
						continue;
					}

					List<MyExcelCellModel> cellsListTemp = new ArrayList<MyExcelCellModel>();
					// ѭ����Cell
					for (int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++) {
						XSSFCell xCell = xRow.getCell(cellNum);

						if (xCell == null) {
							continue;
						}
						boolean isMerge = isMergedRegion(xSheet, rowNum, xCell.getColumnIndex());
						MyExcelCellModel myExcelCellModel = new MyExcelCellModel();

						myExcelCellModel.setRowPosition(rowNum);
						myExcelCellModel.setColumnPosition(cellNum);
						myExcelCellModel.setOwnedSheetName(sheetName);
						/*
						 * �жϵ�Ԫ���Ƿ�Ϊ�ϲ���Ԫ������Ǻϲ���Ԫ������ôӺϲ���Ԫ��ȡ���ݵķ���
						 */
						if (isMerge == true) {
							// ��ȡ�ϲ���Ԫ������
							String tempStr = getMergedRegionValue(xSheet, xRow.getRowNum(), xCell.getColumnIndex());
							myExcelCellModel.setMyExcelCellValue(tempStr);
							myExcelCellModel.setMyDescribe("ҳǩ��" + sheetName + "���ĵ�" + (xCell.getColumnIndex() + 1) + "�У���" + (xRow.getRowNum() + 1) + "���Ǻϲ���Ԫ��");
						} else {
							myExcelCellModel.setMyExcelCellValue(getExcelXslxValue(xCell));
						}
						cellsListTemp.add(myExcelCellModel);
					}

					for (MyExcelCellModel cellModelTemp : cellsListTemp) {
						cellsList.add(cellModelTemp);
					}

				}
			}
			xwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cellsList;
	}

	/**
	 * 
	 * @Title: getExcelAllCell
	 * @Description: �����û��ṩ��ҳǩ�������飬��ȡexcel�ļ��е����е�Ԫ����Ϣ
	 * @param excelFilePathStr
	 * @param sheetName
	 * @return
	 * @return: List<MyExcelCellModel>
	 */
	public List<MyExcelCellModel> getExcelAllCell(String excelFilePathStr, String[] sheetNames) throws Exception {
		List<MyExcelCellModel> cellsList = new ArrayList<MyExcelCellModel>();
		FileInputStream file = new FileInputStream(excelFilePathStr);
		Workbook xwb = new XSSFWorkbook(new BufferedInputStream(file));
		if (sheetNames != null && sheetNames.length > 0) {
			for (String sheetName : sheetNames) {
				// �õ�������Sheet
				XSSFSheet xSheet = (XSSFSheet) xwb.getSheet(sheetName);
				if (xSheet != null) {
					// ѭ����Row
					for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {

						XSSFRow xRow = xSheet.getRow(rowNum);
						if (xRow == null) {
							continue;
						}

						List<MyExcelCellModel> cellsListTemp = new ArrayList<MyExcelCellModel>();
						// ѭ����Cell
						for (int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++) {
							XSSFCell xCell = xRow.getCell(cellNum);

							if (xCell == null) {
								continue;
							}
							boolean isMerge = isMergedRegion(xSheet, rowNum, xCell.getColumnIndex());
							MyExcelCellModel myExcelCellModel = new MyExcelCellModel();
							myExcelCellModel.setRowPosition(rowNum);
							myExcelCellModel.setColumnPosition(cellNum);
							myExcelCellModel.setOwnedSheetName(sheetName);
							/*
							 * �жϵ�Ԫ���Ƿ�Ϊ�ϲ���Ԫ������Ǻϲ���Ԫ������ôӺϲ���Ԫ��ȡ���ݵķ���
							 */
							if (isMerge == true) {
								// ��ȡ�ϲ���Ԫ������
								String tempStr = getMergedRegionValue(xSheet, xRow.getRowNum(), xCell.getColumnIndex());
								myExcelCellModel.setMyExcelCellValue(tempStr);
								myExcelCellModel.setMyDescribe("ҳǩ��" + sheetName + "���ĵ�" + (xCell.getColumnIndex() + 1) + "�У���" + (xRow.getRowNum() + 1) + "���Ǻϲ���Ԫ��");
							} else {
								myExcelCellModel.setMyExcelCellValue(getExcelXslxValue(xCell));
							}
							cellsListTemp.add(myExcelCellModel);
						}
						for (MyExcelCellModel cellModelTemp : cellsListTemp) {
							cellsList.add(cellModelTemp);
						}
					}
				} else {
					logger.info("###############################   ��excel�ļ��в�����ҳǩ��" + sheetName + "����������ȷ��ҳǩ���ƣ�   ###############################");
				}
			}
		}
		xwb.close();
		return cellsList;
	}

	/**
	 * 
	 * @Title: getExcelAllCellFromSheetOneColumn
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetName
	 * @param paramsModel
	 * @return
	 * @return: List<MyExcelCellModel>
	 */
	public List<MyExcelCellModel> getExcelAllCellFromSheetOneColumn(String excelFilePathStr, String sheetName, MyExcelCellModel paramsModel) {
		List<MyExcelCellModel> targetList = new ArrayList<MyExcelCellModel>();
		List<MyExcelCellModel> allTempList = getExcelAllCellFromSheet(excelFilePathStr, sheetName);
		if (allTempList != null && allTempList.size() > 0) {
			for (MyExcelCellModel myExcelCellModel : allTempList) {
				if (myExcelCellModel != null && paramsModel != null && paramsModel.getColumnPosition() == myExcelCellModel.getColumnPosition()) {
					targetList.add(myExcelCellModel);
				}
			}
		}
		logger.info(JSON.toJSON(targetList));
		return targetList;
	}

	// public void readExcelXslx(String excelFilePathStr) {
	// try {
	// XSSFWorkbook xwb = new XSSFWorkbook(excelFilePathStr);
	// // ѭ��������Sheet
	// for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
	// XSSFSheet xSheet = xwb.getSheetAt(numSheet);
	// if (xSheet == null) {
	// continue;
	// }
	//
	// // ѭ����Row
	// for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {
	// XSSFRow xRow = xSheet.getRow(rowNum);
	// if (xRow == null) {
	// continue;
	// }
	//
	// // ѭ����Cell
	// for (int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++) {
	// XSSFCell xCell = xRow.getCell(cellNum);
	// if (xCell == null) {
	// continue;
	// }
	// System.out.print("        " + getExcelXslxValue(xCell));
	// }
	// System.out.println();
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	// public void writeExcelXslx(String excelFilePathStr) {
	// try {
	//
	// XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
	//
	// XSSFSheet xSheet = xwb.getSheetAt(0);
	// // xSheet.getRow(4).getCell(4).setCellValue("����ɽ");;
	// XSSFRow xRow = xSheet.createRow(0);
	// XSSFCell xCell = xRow.createCell(0);
	//
	// xCell.setCellValue("asdfasd");
	//
	// FileOutputStream out = new FileOutputStream(excelFilePathStr);
	// xwb.write(out);
	// out.close();
	// xwb.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	/**
	 * 
	 * @Title: updateExcelCellValueInSheet
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetName
	 * @param cellModel
	 * @return
	 * @return: Serializable
	 */
	public Serializable updateExcelCellValueInSheet(String excelFilePathStr, String sheetName, MyExcelCellModel cellModel) {
		try {
			FileInputStream file = new FileInputStream(excelFilePathStr);
			Workbook xwb = new XSSFWorkbook(new BufferedInputStream(file));
			if (xwb != null) {
				XSSFSheet xSheet = (XSSFSheet) xwb.getSheet(sheetName);
				if (xSheet != null && cellModel != null) {
					if (cellModel.getOwnedSheetName().equals(sheetName)) {
						XSSFRow xRow = xSheet.getRow(cellModel.getRowPosition());
						XSSFCell xCell = xRow.getCell(cellModel.getColumnPosition());

						xCell.setCellValue(cellModel.getMyExcelCellValue());
						FileOutputStream out = new FileOutputStream(excelFilePathStr);
						xwb.write(out);
						out.close();
					}
				}
			}

			xwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellModel;
	}

	/**
	 * 
	 * @Title: updateExcelCellValueInSheet
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetName
	 * @param cellModelList
	 * @return
	 * @return: Serializable
	 */
	public Serializable updateExcelCellValueInSheet(String excelFilePathStr, String sheetName, List<MyExcelCellModel> cellModelList) {

		try {
			FileInputStream file = new FileInputStream(excelFilePathStr);
			Workbook xwb = new XSSFWorkbook(new BufferedInputStream(file));
			if (xwb != null) {
				XSSFSheet xSheet = (XSSFSheet) xwb.getSheet(sheetName);
				if (xSheet != null) {
					if (cellModelList != null && cellModelList.size() > 0) {
						// for ѭ����ʼ
						for (MyExcelCellModel cellModel : cellModelList) {
							if (cellModel != null) {
								logger.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + cellModel.getOwnedSheetName() + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								if (cellModel.getOwnedSheetName().equals(sheetName)) {
									XSSFRow xRow = xSheet.getRow(cellModel.getRowPosition());
									XSSFCell xCell = xRow.getCell(cellModel.getColumnPosition());
									xCell.setCellValue(cellModel.getMyExcelCellValue().trim());
								}
							}
						}
						// for ѭ������
						FileOutputStream out = new FileOutputStream(excelFilePathStr);
						xwb.write(out);
						out.close();
					}
				}
			}
			xwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellModelList.size();
	}

	/**
	 * 
	 * @Title: updateExcelCellValue
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param cellModelList
	 * @param specialSheetNames
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable updateExcelCellValue(String excelFilePathStr, List<MyExcelCellModel> cellModelList, String[] specialSheetNames) throws Exception {
		FileInputStream file = new FileInputStream(excelFilePathStr);
		Workbook xwb = new XSSFWorkbook(new BufferedInputStream(file));
		FileOutputStream out = new FileOutputStream(excelFilePathStr);
		if (xwb != null) {
			if (cellModelList != null && cellModelList.size() > 0) {
				for (MyExcelCellModel cellModel : cellModelList) {
					if (cellModel == null) {
						continue;
					}
					CellStyle style = getMyExcelCellStyle(xwb, cellModel.getColumnPosition());
					String sheetName = cellModel.getOwnedSheetName();
					XSSFSheet xSheet = (XSSFSheet) xwb.getSheet(sheetName);
					if (xSheet != null) {
						XSSFRow xRow = xSheet.getRow(cellModel.getRowPosition());
						XSSFCell xCell = xRow.getCell(cellModel.getColumnPosition());
						xCell.setCellValue(cellModel.getMyExcelCellValue().trim());
						xCell.setCellStyle(style);
						logger.info("��ǰҳǩ��" + sheetName + "���ĵ� " + cellModel.getColumnPosition() + " �У��� " + cellModel.getRowPosition() + " ����Ϣ���Ѹ��£�");
					} else {
						logger.info("�������ĵ�ǰexcel�ļ��в�����ҳǩ��" + sheetName + "����������ȷ��ҳǩ��Ϣ��");
					}
				}
			}
		}
		xwb.write(out);
		out.close();
		xwb.close();
		return cellModelList.size();
	}

	/**
	 * 
	 * @Title: getMyExcelCellStyle
	 * @Description: ����Ԫ����ӱ߿���������
	 * @param xwb
	 * @return
	 * @throws Exception
	 * @return: CellStyle
	 */
	private CellStyle getMyExcelCellStyle(Workbook xwb, int columnIndex) throws Exception {

		CellStyle style = xwb.createCellStyle();
		switch (columnIndex) {
		case 0:
			/*
			 * ���õ�Ԫ����뷽ʽ
			 */
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// ��ֱ
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ˮƽ
			break;
		case 1:
			/*
			 * ���õ�Ԫ����뷽ʽ
			 */
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// ��ֱ
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ˮƽ
			break;
		case 2:
			/*
			 * ���õ�Ԫ����뷽ʽ
			 */
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// ��ֱ
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ˮƽ
			break;
		default:
			break;
		}
		// style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);// ��ֱ����
		// style.setAlignment(XSSFCellStyle.VERTICAL_BOTTOM);//��ֱ�ײ�
		// style.setAlignment(XSSFCellStyle.VERTICAL_TOP);//��ֱ����
		// style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // ˮƽ����
		/*
		 * ����Ԫ��ӱ߿�
		 */
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);// �ϱ߿�
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN); // �±߿�
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);// ��߿�
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);// �ұ߿�
		/*
		 * ���õ�Ԫ�������ʽ
		 */
		Font font = xwb.createFont();
		font.setFontName("����");
		font.setFontHeightInPoints((short) 10);// ���������С
		style.setFont(font);
		return style;
	}

	/**
	 * 
	 * @Title: getExcelXslxValue
	 * @Description: TODO
	 * @param xCell
	 * @return
	 * @return: String
	 */
	protected String getExcelXslxValue(XSSFCell xCell) {
		if (xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xCell.getBooleanCellValue());
		} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xCell.getNumericCellValue());
		} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
			/*
			 * ���excel��Ԫ��洢���� "excel����" ����ȡ "excel����" ����֮��Ľ��
			 */
			try {
				return String.valueOf(xCell.getNumericCellValue());
			} catch (IllegalStateException e) {
				return String.valueOf(xCell.getRichStringCellValue());
			}
		} else {
			return String.valueOf(xCell.getStringCellValue());
		}

	}

	/**
	 * �ж�ָ���ĵ�Ԫ���Ƿ��Ǻϲ���Ԫ��
	 * 
	 * @param sheet
	 * @param row
	 *          ���±�
	 * @param column
	 *          ���±�
	 * @return
	 */
	private boolean isMergedRegion(XSSFSheet xSheet, int rowIndex, int columnIndex) {
		int sheetMergeCount = xSheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = xSheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (rowIndex >= firstRow && rowIndex <= lastRow) {
				if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * ��ȡ�ϲ���Ԫ���ֵ
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public String getMergedRegionValue(XSSFSheet xSheet, int rowIndex, int columnIndex) {
		if (xSheet != null) {
			int sheetMergeCount = xSheet.getNumMergedRegions();

			for (int i = 0; i < sheetMergeCount; i++) {
				CellRangeAddress ca = xSheet.getMergedRegion(i);
				if (ca == null)
					continue;
				int firstColumn = ca.getFirstColumn();
				int lastColumn = ca.getLastColumn();
				int firstRow = ca.getFirstRow();
				int lastRow = ca.getLastRow();

				if (rowIndex >= firstRow && rowIndex <= lastRow) {

					if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
						XSSFRow xRow = xSheet.getRow(firstRow);
						XSSFCell xCell = xRow.getCell(firstColumn);
						return getExcelXslxValue(xCell);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: removeMergedRegion
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetNames
	 * @param index
	 * @throws Exception
	 * @return: void
	 */
	public void removeMergedRegion(String excelFilePathStr, String[] sheetNames, int index) throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
		FileOutputStream out = new FileOutputStream(excelFilePathStr);
		if (xwb != null) {
			for (String sheetName : sheetNames) {
				XSSFSheet xSheet = xwb.getSheet(sheetName);
				if (xSheet != null) {
					xSheet.removeMergedRegion(index);
					logger.info("�˴β�����excel�ļ��еģ���" + sheetName + "��ҳǩ�е����кϲ���Ԫ����ʽ���Ƴ��ɹ���");
				} else {
					logger.info("�˴β�����excel�ļ��в����ڴˡ�" + sheetName + "��ҳǩ��������ȷ�ϣ�");
				}
			}
		}
		xwb.write(out);
		out.close();
		xwb.close();
	}

	/**
	 * 
	 * @Title: addMergedRegion
	 * @Description: �ϲ���Ԫ��
	 * @param excelFilePathStr
	 * @param sheetNames
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 * @throws Exception
	 * @return: void
	 */
	public void addMergedRegion(String excelFilePathStr, String[] sheetNames, List<MyExcelCellRangeAddressModel> cellRangeAddressList) throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
		FileOutputStream out = new FileOutputStream(excelFilePathStr);
		if (xwb != null) {
			for (String sheetName : sheetNames) {
				XSSFSheet xSheet = xwb.getSheet(sheetName);
				if (xSheet != null) {
					if (cellRangeAddressList != null && cellRangeAddressList.size() > 0) {
						for (MyExcelCellRangeAddressModel cellRangeAddress : cellRangeAddressList) {
							if (cellRangeAddress != null) {
								if (cellRangeAddress.getOwnedSheet().equals(sheetName)) {
									int firstRow = cellRangeAddress.getFirstRow();
									int lastRow = cellRangeAddress.getLastRow();
									int firstCol = cellRangeAddress.getFirstCol();
									int lastCol = cellRangeAddress.getLastCol();
									if (isMergedRegion(xSheet, firstRow, lastCol)) {
										logger.info("�˴β�����excel�ļ��еģ���" + sheetName + "��ҳǩ�еĵڡ�" + (firstRow + 1) + "���е���" + (lastRow + 1) + "���У��ڡ�" + (firstCol + 1) + "���е���" + (lastCol + 1) + "��������������Ǻϲ���Ԫ������ϲ���");
									} else {
										xSheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
										logger.info("�˴β�����excel�ļ��еģ���" + sheetName + "��ҳǩ�еĵڡ�" + (firstRow + 1) + "���е���" + (lastRow + 1) + "���У��ڡ�" + (firstCol + 1) + "���е���" + (lastCol + 1) + "�����������ϲ���Ԫ��ɹ���");
									}
								}
							} else {
								logger.info("�����ݵĲ������󣬴��ڿ�ֵ��������ȷ�ϣ�");
							}
						}
					} else {
						logger.info("�����ݵĲ�������List����Ϊ�գ�������ȷ�ϣ�");
					}
				} else {
					logger.info("�˴β�����excel�ļ��в����ڴˡ�" + sheetName + "��ҳǩ��������ȷ�ϣ�");
				}
			}
		}
		xwb.write(out);
		out.close();
		xwb.close();
	}

	// public void insertRow(XSSFWorkbook wb, XSSFSheet sheet, int startRow, int rows) {
	// /*
	// *
	// */
	// sheet.shiftRows(startRow + 1, sheet.getLastRowNum(), rows, true, false);
	// // Parameters:
	// // startRow - the row to start shifting
	// // endRow - the row to end shifting
	// // n - the number of rows to shift
	// // copyRowHeight - whether to copy the row height during the shift
	// // resetOriginalRowHeight - whether to set the original row's height to the default
	//
	// for (int i = 0; i < rows; i++) {
	//
	// XSSFRow sourceRow = null;
	// XSSFRow targetRow = null;
	//
	// sourceRow = sheet.getRow(startRow);
	// targetRow = sheet.createRow(++startRow);
	//
	// net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow);
	// }
	//
	// }

	/**
	 * 
	 * @Title: insertRow
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetName
	 * @param startRow
	 * @param rows
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	// public Serializable insertRow(String excelFilePathStr, String sheetName, int startRow, int rows) throws Exception {
	// XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
	// if (xwb != null) {
	// XSSFSheet xSheet = xwb.getSheet(sheetName);
	// if (xSheet != null) {
	// xSheet.shiftRows(startRow + 1, xSheet.getLastRowNum(), rows, true, false);
	// // Parameters:
	// // startRow - the row to start shifting
	// // endRow - the row to end shifting
	// // n - the number of rows to shift
	// // copyRowHeight - whether to copy the row height during the shift
	// // resetOriginalRowHeight - whether to set the original row's height to the default
	//
	// for (int i = 0; i < rows; i++) {
	//
	// XSSFRow sourceRow = null;
	// XSSFRow targetRow = null;
	//
	// sourceRow = xSheet.getRow(startRow);
	// targetRow = xSheet.createRow(++startRow);
	//
	// net.sf.jxls.util.Util.copyRow(xSheet, sourceRow, targetRow);
	// FileOutputStream out = new FileOutputStream(excelFilePathStr);
	// xwb.write(out);
	// out.close();
	// }
	// }
	// }
	// xwb.close();
	// return "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE";
	// }

	// /**
	// *
	// * @Title: insertRowsToExcelSheet
	// * @Description: TODO
	// * @param excelFilePathStr
	// * @param sheetName
	// * @param startRow
	// * @param rows
	// * @return
	// * @throws Exception
	// * @return: Serializable
	// */
	// public Serializable insertRowsToExcelSheet(String excelFilePathStr, String sheetName, int startRow, int rows) throws Exception {
	// XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
	// if (xwb != null) {
	// XSSFSheet xSheet = xwb.getSheet(sheetName);
	// if (xSheet != null) {
	// xSheet.shiftRows(startRow + 1, xSheet.getLastRowNum(), rows, true, false);
	// for (int i = 0; i < rows; i++) {
	// XSSFRow sourceRow = null;
	// XSSFRow targetRow = null;
	// sourceRow = xSheet.getRow(startRow);
	// targetRow = xSheet.createRow(++startRow);
	// // ����net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow)��������һ�а����������е���ʽ
	// net.sf.jxls.util.Util.copyRow(xSheet, sourceRow, targetRow);
	// FileOutputStream out = new FileOutputStream(excelFilePathStr);
	// xwb.write(out);
	// out.close();
	// }
	// xwb.close();
	// return rows;
	// }
	// }
	// xwb.close();
	// return 0;
	// }

	/**
	 * 
	 * @Title: insertCopyRowsToExcelSheet
	 * @Description: ����������SHEETҳǩ�У����� A �����ݲ����뵽 A �е���һ���У�������ֱ����EXCEL�С����븴�Ƶ�Ԫ�񡿵Ĳ�����JAVA����
	 * @param excelFilePathStr
	 *          �������� EXCEL �ļ��ĸ�·��
	 * @param sheetName
	 *          �������� EXCEL �ļ��� SHEET ҳǩ����
	 * @param startRow
	 *          �����Ƶ� EXCEL �ļ��� SHEET ҳǩ�� A �е����±꣬Ĭ�ϴ� 0 ��ʼ��λ������ A ��Ϊ��37�У������±�Ϊ��36
	 * @param rows
	 *          ���ڿ��Ʋ��뼸�����ݣ����������λ�� A �к��沢���ڣ����磺�ܹ������������ݣ�B��C���²���� B �е����±�Ϊ��37���²���� C �е����±�Ϊ��38���� rows ��ֵӦ���� 2 ��......�������ƣ�
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable insertCopyRowsToExcelSheet(String excelFilePathStr, String sheetName, int startRow, int rows) throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
		if (xwb != null) {
			XSSFSheet xSheet = xwb.getSheet(sheetName);
			if (xSheet != null) {
				xSheet.shiftRows(startRow + 1, xSheet.getLastRowNum(), rows, true, false);
				for (int i = 0; i < rows; i++) {
					XSSFRow sourceRow = null;
					XSSFRow targetRow = null;
					sourceRow = xSheet.getRow(startRow);
					targetRow = xSheet.createRow(++startRow);
					// ����net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow)��������һ�а����������е���ʽ
					net.sf.jxls.util.Util.copyRow(xSheet, sourceRow, targetRow);
				}
				FileOutputStream out = new FileOutputStream(excelFilePathStr);
				xwb.write(out);
				out.close();
			}

		}
		xwb.close();
		return rows;
	}

	/**
	 * 
	 * @Title: copiedRowInsertExcel
	 * @Description: ����һ������excel��Ԫ�񣬲������в��뵽����У�����������SHEETҳǩ�У����� A �����ݲ����뵽 A �е���һ���У�������ֱ����EXCEL�С����븴�Ƶ�Ԫ�񡿵Ĳ�����JAVA����
	 * @param excelFilePathStr
	 *          �������� EXCEL �ļ��ĸ�·��
	 * @param sheetNames
	 *          �������� EXCEL �ļ��� SHEET ҳǩ����
	 * @param cellModelList
	 * @param rows
	 *          ���ڿ��Ʋ��뼸�����ݣ����������λ�� A �к��沢���ڣ����磺�ܹ������������ݣ�B��C���²���� B �е����±�Ϊ��37���²���� C �е����±�Ϊ��38���� rows ��ֵӦ���� 2 ��......�������ƣ�
	 * @param generalLastRowIndex
	 *          generalLastRowIndexȡֵΪ�ĵ����������ĵ�����ͬ-רҵ-��Ա��-temp.xlsx���е�ҳǩ��22�������һ�е��±�ֵ
	 * @return
	 * @throws Exception
	 * @return: List<MyExcelCellModel>
	 */
	public List<MyExcelCellModel> copiedRowInsertExcel(String excelFilePathStr, String[] sheetNames, List<MyExcelCellModel> cellModelList, int rows, int generalLastRowIndex) throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
		FileOutputStream out = new FileOutputStream(excelFilePathStr);
		List<MyExcelCellModel> list = new ArrayList<MyExcelCellModel>();
		logger.info(JSON.toJSON(cellModelList));
		if (xwb != null) {
			if (cellModelList != null && cellModelList.size() > 0) {
				for (MyExcelCellModel cellModel : cellModelList) {
					if (cellModel == null) {
						continue;
					}
					String sheetName = cellModel.getOwnedSheetName();
					int startRow = cellModel.getRowPosition();
					/*
					 * ������δ���д�ڣ�2016��3��31�գ����ܽ������ڡ������ĵ�����ͬ-רҵ-��Ա��-temp.xlsx����ҳǩ��25������27������30������31������37�����޸Ĳ���Ҫ�� ��Ϊ�������ĵ�����ͬ-רҵ-��Ա��-temp.xlsx����ҵ�����ų�����ҳǩ������ҳǩ��ֻ��һ��רҵ���ͣ����������ų�ҳǩ��11������12������36��������������ͬ�ģ� ��generalLastRowIndexȡֵΪҳǩ��22�������һ�е��±�ֵ�������������±�ֵ�����ǰ�������רҵ���͵�ҳǩ���պ��������������ơ�
					 */
					if (generalLastRowIndex > 0 && startRow > generalLastRowIndex) {
						startRow++;
					}
					XSSFSheet xSheet = xwb.getSheet(sheetName);
					if (xSheet != null) {
						xSheet.shiftRows(startRow + 1, xSheet.getLastRowNum(), rows, true, false);
						for (int i = 0; i < rows; i++) {
							XSSFRow sourceRow = null;
							XSSFRow targetRow = null;
							sourceRow = xSheet.getRow(startRow);
							targetRow = xSheet.createRow(++startRow);
							/*
							 * ����net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow)��������һ�а����������е���ʽ
							 */
							net.sf.jxls.util.Util.copyRow(xSheet, sourceRow, targetRow);
							/*
							 * ������δ���д�ڣ�2016��3��31�գ����ܽ������ڡ������ĵ�����ͬ-רҵ-��Ա��-temp.xlsx�����޸Ĳ���Ҫ��
							 */
							MyExcelCellModel newCellModel = new MyExcelCellModel();
							org.springframework.beans.BeanUtils.copyProperties(cellModel, newCellModel);
							newCellModel.setRowPosition(targetRow.getRowNum());
							newCellModel.setMyExcelCellValue(newCellModel.getAttribute3());
							list.add(newCellModel);
						}

					} else {
						logger.info("��������EXCEL�ļ��в�����ҳǩ��" + sheetName + "����������ȷ��ҳǩ����Ϣ��");
					}
				}
			} else {
				logger.info("�������ϣ�cellModelListΪnull��");
			}
		}
		xwb.write(out);
		out.close();
		xwb.close();
		return list;
	}

	/**
	 * 
	 * @Title: removeRowsFromExcelSheet
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetName
	 * @param removeRowIndex
	 * @param rows
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable removeRowsFromExcelSheet(String excelFilePathStr, String sheetName, int removeRowIndex, int rows) throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
		if (xwb != null) {
			XSSFSheet xSheet = xwb.getSheet(sheetName);
			if (xSheet != null) {
				int lastRowIndex = xSheet.getLastRowNum();
				if (removeRowIndex == lastRowIndex) {
					XSSFRow removeRow = xSheet.getRow(removeRowIndex);
					if (removeRow != null) {
						xSheet.removeRow(removeRow);
					}
				} else {
					xSheet.shiftRows(removeRowIndex + 1, lastRowIndex, rows, true, false);
				}
				FileOutputStream out = new FileOutputStream(excelFilePathStr);
				xwb.write(out);
				out.close();
			}
		}
		xwb.close();
		return rows;
	}

	/**
	 * 
	 * @Title: removeRowsFromExcelSheet
	 * @Description: TODO
	 * @param excelFilePathStr
	 * @param sheetName
	 * @param removeUsedCellModel
	 * @param rows
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable removeRowsFromExcelSheet(String excelFilePathStr, String sheetName, MyExcelCellModel removeUsedCellModel, int rows) throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFilePathStr));
		if (xwb != null) {
			XSSFSheet xSheet = xwb.getSheet(sheetName);
			if (xSheet != null && removeUsedCellModel != null && removeUsedCellModel.getOwnedSheetName().trim().equals(sheetName)) {
				int lastRowIndex = xSheet.getLastRowNum();
				int removeRowIndex = removeUsedCellModel.getRowPosition();
				XSSFRow removeRowInSheet = xSheet.getRow(removeRowIndex);
				XSSFCell regexUsedCell = removeRowInSheet.getCell(removeUsedCellModel.getColumnPosition());
				/*
				 * У�鱾���������ݲ����е� removeUsedCellModel ��Ԫ������ڵ�ǰ EXCEL �� SHEET ҳǩ���Ƿ����
				 */
				if (removeRowInSheet != null && regexUsedCell != null && getExcelXslxValue(regexUsedCell).trim().equals(removeUsedCellModel.getMyExcelCellValue().trim())) {
					logger.info("ɾ��ҳǩ��" + sheetName + "�����ģ� <" + removeUsedCellModel.getMyExcelCellValue() + "> ���������ݲ�����ʼ����������");
					if (removeRowIndex == lastRowIndex) {
						xSheet.removeRow(removeRowInSheet);
					} else {
						xSheet.shiftRows(removeRowIndex + 1, lastRowIndex, rows, true, false);
					}
					logger.info("ɾ��ҳǩ��" + sheetName + "�����ģ� <" + removeUsedCellModel.getMyExcelCellValue() + "> ���������ݲ�����������������");
				}
			}
			FileOutputStream out = new FileOutputStream(excelFilePathStr);
			xwb.write(out);
			out.close();
		}
		xwb.close();
		return rows;
	}

	public static void main(String[] args) throws Exception {
		// String regEx = "[\u4e00-\u9fa5]";
		MyHandleExcelUtil excelUtil = getInstance();
		// // String srcStr1 = "E:\\��Ŀ�ĵ�\\�����ĵ�\\����\\2016�깤��\\2016.01\\2016.01.05\\test1.xlsx";
		String srcStr2 = "E:\\MyHandoverDocumentSH_TEMP\\TEST_METHOD\\TEST1.xlsx";
		// String srcStr3 = "E:\\��Ŀ�ĵ�\\�����ĵ�\\����\\2016�깤��\\2016.01\\2016.01.06\\�Ϻ�����汾�����ŵı�����������Ա������Ϣ.xlsx";
		// // excelUtil.writeExcelXslx(srcStr3);
		// // excelUtil.readExcelXslx(srcStr3);
		// List<MyExcelCellModel> lists = excelUtil.getExcelAllCellFromSheet(srcStr3, "mydatatemp");
		// for (MyExcelCellModel myExcelCellModel : lists) {
		// if (myExcelCellModel.getMyExcelCellValue().trim().equals("���Ǵ���") && myExcelCellModel.getColumnPosition() == 5) {
		// // excelUtil.changeExcelRowFormatInSheet(srcStr3, "mydatatemp", myExcelCellModel.getRowPosition(), 0);
		// myExcelCellModel.setMyExcelCellValue("���Ǵ���");
		// excelUtil.updateExcelCellValueInSheet(srcStr3, "mydatatemp", myExcelCellModel);
		// }
		// }
		// logger.info(JSON.toJSONString(lists));

		// MyHandoverDocumentSHModel modelTemp = MyHandoverDocumentSHModelHelper.initTemp();

		// List<MyExcelCellModel> lists = excelUtil.getExcelAllCellFromSheetOneColumn(srcStr2, "31", new MyExcelCellModel(4));
		// logger.info(JSON.toJSONString(lists));
		// String str = "aaa";
		// String str1 = ".*" + str + ".*";
		// System.out.println(str1);

		// List<MyExcelCellModel> lists = new ArrayList<MyExcelCellModel>();
		// lists.add(new MyExcelCellModel(0, 0, "1"));
		// lists.add(new MyExcelCellModel(1, 0, "1"));
		// lists.add(new MyExcelCellModel(0, 2, "ɱ����2"));
		// lists.add(new MyExcelCellModel(0, 3, "ɱ����2"));
		// lists.add(new MyExcelCellModel(0, 0, "ɱ����2"));
		// logger.info(excelUtil.updateExcelCellValueInSheet(srcStr2, "Sheet1", lists));
		// logger.info(excelUtil.removeRowsFromExcelSheet(srcStr2, "Sheet1", new MyExcelCellModel(0, 0, "1.0"), -1));
	}
}
