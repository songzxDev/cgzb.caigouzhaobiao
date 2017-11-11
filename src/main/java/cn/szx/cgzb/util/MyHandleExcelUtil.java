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

	// 懒汉式单例.在第一次调用的时候实例化
	// 私有的默认构造子
	private MyHandleExcelUtil() {

	}

	// 注意，这里没有final
	private static MyHandleExcelUtil myHandleExcelUtil = null;

	// 静态工厂方法
	public synchronized static MyHandleExcelUtil getInstance() {
		if (myHandleExcelUtil == null) {
			myHandleExcelUtil = new MyHandleExcelUtil();
		}
		return myHandleExcelUtil;
	}

	// public void getAndReadExcelFile(String srcStr) throws Exception {
	// // 构造 XSSFWorkbook 对象，strPath 传入文件路径
	// XSSFWorkbook xwb = new XSSFWorkbook(srcStr);
	// // 读取第一章表格内容
	// // XSSFSheet sheet1 = xwb.getSheetAt(0);
	// XSSFSheet sheet = xwb.getSheet("2");
	// // 定义 row、cell
	// XSSFRow row;
	// String cell;
	// // 循环输出表格中的内容
	// for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
	// row = sheet.getRow(i);
	// for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
	// // 通过 row.getCell(j).toString() 获取单元格内容，
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
			// 得到工作表Sheet
			XSSFSheet xSheet = xwb.getSheet(sheetName);
			if (xSheet != null) {
				// 循环行Row
				for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {

					XSSFRow xRow = xSheet.getRow(rowNum);
					if (xRow == null) {
						continue;
					}

					List<MyExcelCellModel> cellsListTemp = new ArrayList<MyExcelCellModel>();
					// 循环列Cell
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
						 * 判断单元格是否为合并单元格，如果是合并单元格则调用从合并单元格取内容的方法
						 */
						if (isMerge == true) {
							// 获取合并单元格内容
							String tempStr = getMergedRegionValue(xSheet, xRow.getRowNum(), xCell.getColumnIndex());
							myExcelCellModel.setMyExcelCellValue(tempStr);
							myExcelCellModel.setMyDescribe("页签【" + sheetName + "】的第" + (xCell.getColumnIndex() + 1) + "列，第" + (xRow.getRowNum() + 1) + "行是合并单元格");
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
	 * @Description: 根据用户提供的页签名称数组，获取excel文件中的所有单元格信息
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
				// 得到工作表Sheet
				XSSFSheet xSheet = (XSSFSheet) xwb.getSheet(sheetName);
				if (xSheet != null) {
					// 循环行Row
					for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {

						XSSFRow xRow = xSheet.getRow(rowNum);
						if (xRow == null) {
							continue;
						}

						List<MyExcelCellModel> cellsListTemp = new ArrayList<MyExcelCellModel>();
						// 循环列Cell
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
							 * 判断单元格是否为合并单元格，如果是合并单元格则调用从合并单元格取内容的方法
							 */
							if (isMerge == true) {
								// 获取合并单元格内容
								String tempStr = getMergedRegionValue(xSheet, xRow.getRowNum(), xCell.getColumnIndex());
								myExcelCellModel.setMyExcelCellValue(tempStr);
								myExcelCellModel.setMyDescribe("页签【" + sheetName + "】的第" + (xCell.getColumnIndex() + 1) + "列，第" + (xRow.getRowNum() + 1) + "行是合并单元格");
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
					logger.info("###############################   该excel文件中不存在页签【" + sheetName + "】，请重新确认页签名称！   ###############################");
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
	// // 循环工作表Sheet
	// for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
	// XSSFSheet xSheet = xwb.getSheetAt(numSheet);
	// if (xSheet == null) {
	// continue;
	// }
	//
	// // 循环行Row
	// for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {
	// XSSFRow xRow = xSheet.getRow(rowNum);
	// if (xRow == null) {
	// continue;
	// }
	//
	// // 循环列Cell
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
	// // xSheet.getRow(4).getCell(4).setCellValue("花果山");;
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
						// for 循环开始
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
						// for 循环结束
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
						logger.info("当前页签【" + sheetName + "】的第 " + cellModel.getColumnPosition() + " 列，第 " + cellModel.getRowPosition() + " 行信息，已更新！");
					} else {
						logger.info("所操作的当前excel文件中不存在页签【" + sheetName + "】，请重新确认页签信息！");
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
	 * @Description: 给单元格添加边框并设置字体
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
			 * 设置单元格对齐方式
			 */
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平
			break;
		case 1:
			/*
			 * 设置单元格对齐方式
			 */
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平
			break;
		case 2:
			/*
			 * 设置单元格对齐方式
			 */
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平
			break;
		default:
			break;
		}
		// style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// style.setAlignment(XSSFCellStyle.VERTICAL_BOTTOM);//垂直底部
		// style.setAlignment(XSSFCellStyle.VERTICAL_TOP);//垂直顶部
		// style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 水平居中
		/*
		 * 给单元格加边框
		 */
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		/*
		 * 设置单元格字体格式
		 */
		Font font = xwb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);// 设置字体大小
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
			 * 如果excel单元格存储的是 "excel函数" ，获取 "excel函数" 计算之后的结果
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
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *          行下标
	 * @param column
	 *          列下标
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
	 * 获取合并单元格的值
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
					logger.info("此次操作的excel文件中的：【" + sheetName + "】页签中的所有合并单元格样式被移除成功！");
				} else {
					logger.info("此次操作的excel文件中不存在此【" + sheetName + "】页签，请重新确认！");
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
	 * @Description: 合并单元格
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
										logger.info("此次操作的excel文件中的：【" + sheetName + "】页签中的第【" + (firstRow + 1) + "】行到【" + (lastRow + 1) + "】行，第【" + (firstCol + 1) + "】列到【" + (lastCol + 1) + "】列这段区域已是合并单元格，无需合并！");
									} else {
										xSheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
										logger.info("此次操作的excel文件中的：【" + sheetName + "】页签中的第【" + (firstRow + 1) + "】行到【" + (lastRow + 1) + "】行，第【" + (firstCol + 1) + "】列到【" + (lastCol + 1) + "】列这段区域合并单元格成功！");
									}
								}
							} else {
								logger.info("所传递的参数有误，存在空值，请重新确认！");
							}
						}
					} else {
						logger.info("所传递的参数有误，List集合为空，请重新确认！");
					}
				} else {
					logger.info("此次操作的excel文件中不存在此【" + sheetName + "】页签，请重新确认！");
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
	// // 调用net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow)方法复制一行包含被复制行的样式
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
	 * @Description: 在所操作的SHEET页签中，复制 A 行数据并插入到 A 行的下一行中，类似于直接在EXCEL中【插入复制单元格】的操作的JAVA方法
	 * @param excelFilePathStr
	 *          被操作的 EXCEL 文件的根路径
	 * @param sheetName
	 *          被操作的 EXCEL 文件的 SHEET 页签名称
	 * @param startRow
	 *          被复制的 EXCEL 文件的 SHEET 页签的 A 行的行下标，默认从 0 开始计位，例如 A 行为第37行，则行下标为：36
	 * @param rows
	 *          用于控制插入几行数据（所插入的行位于 A 行后面并相邻，例如：总共插入两行数据：B、C，新插入的 B 行的行下标为：37、新插入的 C 行的行下标为：38，则 rows 的值应传入 2 ，......依此类推）
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
					// 调用net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow)方法复制一行包含被复制行的样式
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
	 * @Description: 复制一个整行excel单元格，并将整行插入到表格中，在所操作的SHEET页签中，复制 A 行数据并插入到 A 行的下一行中，类似于直接在EXCEL中【插入复制单元格】的操作的JAVA方法
	 * @param excelFilePathStr
	 *          被操作的 EXCEL 文件的根路径
	 * @param sheetNames
	 *          被操作的 EXCEL 文件的 SHEET 页签名称
	 * @param cellModelList
	 * @param rows
	 *          用于控制插入几行数据（所插入的行位于 A 行后面并相邻，例如：总共插入两行数据：B、C，新插入的 B 行的行下标为：37、新插入的 C 行的行下标为：38，则 rows 的值应传入 2 ，......依此类推）
	 * @param generalLastRowIndex
	 *          generalLastRowIndex取值为文档：《交接文档（合同-专业-人员）-temp.xlsx》中的页签【22】的最后一行的下标值
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
					 * 下面这段代码写于：2016年3月31日，可能仅适用于《交接文档（合同-专业-人员）-temp.xlsx》的页签【25】、【27】、【30】、【31】、【37】的修改操作要求 因为《交接文档（合同-专业-人员）-temp.xlsx》的业务是排除上述页签，其余页签均只有一个专业类型，且数量（排除页签【11】、【12】、【36】）理论上是相同的， 故generalLastRowIndex取值为页签【22】的最后一行的下标值，如果大于这个下标值，即是包含两个专业类型的页签，日后下面代码继续完善。
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
							 * 调用net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow)方法复制一行包含被复制行的样式
							 */
							net.sf.jxls.util.Util.copyRow(xSheet, sourceRow, targetRow);
							/*
							 * 下面这段代码写于：2016年3月31日，可能仅适用于《交接文档（合同-专业-人员）-temp.xlsx》的修改操作要求
							 */
							MyExcelCellModel newCellModel = new MyExcelCellModel();
							org.springframework.beans.BeanUtils.copyProperties(cellModel, newCellModel);
							newCellModel.setRowPosition(targetRow.getRowNum());
							newCellModel.setMyExcelCellValue(newCellModel.getAttribute3());
							list.add(newCellModel);
						}

					} else {
						logger.info("所操作的EXCEL文件中不存在页签【" + sheetName + "】，请重新确认页签的信息！");
					}
				}
			} else {
				logger.info("参数集合：cellModelList为null！");
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
				 * 校验本方法所传递参数中的 removeUsedCellModel 单元格对象在当前 EXCEL 的 SHEET 页签中是否存在
				 */
				if (removeRowInSheet != null && regexUsedCell != null && getExcelXslxValue(regexUsedCell).trim().equals(removeUsedCellModel.getMyExcelCellValue().trim())) {
					logger.info("删除页签【" + sheetName + "】：的（ <" + removeUsedCellModel.getMyExcelCellValue() + "> ）整行数据操作开始！！！！！");
					if (removeRowIndex == lastRowIndex) {
						xSheet.removeRow(removeRowInSheet);
					} else {
						xSheet.shiftRows(removeRowIndex + 1, lastRowIndex, rows, true, false);
					}
					logger.info("删除页签【" + sheetName + "】：的（ <" + removeUsedCellModel.getMyExcelCellValue() + "> ）整行数据操作结束！！！！！");
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
		// // String srcStr1 = "E:\\项目文档\\个人文档\\任务\\2016年工作\\2016.01\\2016.01.05\\test1.xlsx";
		String srcStr2 = "E:\\MyHandoverDocumentSH_TEMP\\TEST_METHOD\\TEST1.xlsx";
		// String srcStr3 = "E:\\项目文档\\个人文档\\任务\\2016年工作\\2016.01\\2016.01.06\\上海最近版本各部门的本部门审批人员配置信息.xlsx";
		// // excelUtil.writeExcelXslx(srcStr3);
		// // excelUtil.readExcelXslx(srcStr3);
		// List<MyExcelCellModel> lists = excelUtil.getExcelAllCellFromSheet(srcStr3, "mydatatemp");
		// for (MyExcelCellModel myExcelCellModel : lists) {
		// if (myExcelCellModel.getMyExcelCellValue().trim().equals("我是传奇") && myExcelCellModel.getColumnPosition() == 5) {
		// // excelUtil.changeExcelRowFormatInSheet(srcStr3, "mydatatemp", myExcelCellModel.getRowPosition(), 0);
		// myExcelCellModel.setMyExcelCellValue("我是传奇");
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
		// lists.add(new MyExcelCellModel(0, 2, "杀破狼2"));
		// lists.add(new MyExcelCellModel(0, 3, "杀破狼2"));
		// lists.add(new MyExcelCellModel(0, 0, "杀破狼2"));
		// logger.info(excelUtil.updateExcelCellValueInSheet(srcStr2, "Sheet1", lists));
		// logger.info(excelUtil.removeRowsFromExcelSheet(srcStr2, "Sheet1", new MyExcelCellModel(0, 0, "1.0"), -1));
	}
}
