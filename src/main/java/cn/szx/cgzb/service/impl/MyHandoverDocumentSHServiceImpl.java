package cn.szx.cgzb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyExcelCellModel;
import cn.szx.cgzb.service.MyHandoverDocumentSHServiceI;
import cn.szx.cgzb.util.MyHandleExcelUtil;

import com.alibaba.fastjson.JSON;

@Service("myHandoverDocumentSHService")
public class MyHandoverDocumentSHServiceImpl implements MyHandoverDocumentSHServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyHandoverDocumentSHServiceImpl.class);

	private MyHandleExcelUtil excelUtil = MyHandleExcelUtil.getInstance();

	/**
	 * 
	 * @Title: updateAllValuesOfCurrentAndNextColumnByAfferentParams
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @return: int
	 */
	private int updateAllValuesOfCurrentAndNextColumnByAfferentParams(MyAfferentParametersModel paramsModel) {
		List<MyExcelCellModel> targetList = new ArrayList<MyExcelCellModel>();

		MyExcelCellModel cellModel = paramsModel.getCellModel();

		logger.info(JSON.toJSON(cellModel));
		int nextColumnIndex = 0;
		if (cellModel != null) {
			final int initColumnPosition = cellModel.getColumnPosition();

			/*
			 * 取当前sheet第X列的的所有单元格信息，即【交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx】文档中的XXXX列的所有信息
			 */
			List<MyExcelCellModel> listTemp1 = excelUtil.getExcelAllCellFromSheetOneColumn(paramsModel.getExcelFilePathStr(), paramsModel.getSheetName(), cellModel);
			logger.info(JSON.toJSON(listTemp1));

			logger.info(JSON.toJSON(listTemp1));
			if (listTemp1 != null && listTemp1.size() > 0) {

				for (MyExcelCellModel tempModel : listTemp1) {
					if (tempModel != null && cellModel != null && tempModel.getColumnPosition() == cellModel.getColumnPosition()) {
						String oldRegexStr = ".*" + paramsModel.getOriginalNameValueOfCell() + ".*";
						String newStr = paramsModel.getNameCellValueAfterReplacement();
						if (tempModel.getMyExcelCellValue().trim().matches(oldRegexStr) == true) {
							String strTemp = tempModel.getMyExcelCellValue().replace(paramsModel.getOriginalNameValueOfCell(), newStr);
							tempModel.setMyExcelCellValue(strTemp);
							targetList.add(tempModel);

						}
					}
				}
			}

			if (paramsModel.isWhetherHandleAdjacentColumn()) {
				/*
				 * 取当前sheet第X+1列的的所有单元格信息，即【交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx】文档中的对应XXXX的帐号列的所有信息
				 */
				nextColumnIndex = cellModel.getColumnPosition() + 1;
				cellModel.setColumnPosition(nextColumnIndex);
				List<MyExcelCellModel> listTemp2 = excelUtil.getExcelAllCellFromSheetOneColumn(paramsModel.getExcelFilePathStr(), paramsModel.getSheetName(), paramsModel.getCellModel());
				logger.info(JSON.toJSON(listTemp2));
				if (listTemp2 != null && listTemp2.size() > 0) {

					for (MyExcelCellModel tempModel2 : listTemp2) {
						if (tempModel2 != null && cellModel != null && tempModel2.getColumnPosition() == cellModel.getColumnPosition()) {
							String oldRegexStr2 = ".*" + paramsModel.getOriginalAccountsValueOfCell() + ".*";
							String newStr2 = paramsModel.getAccountsCellValueAfterReplacement();
							if (tempModel2.getMyExcelCellValue().trim().matches(oldRegexStr2) == true) {
								String strTemp2 = tempModel2.getMyExcelCellValue().replace(paramsModel.getOriginalAccountsValueOfCell(), newStr2);
								tempModel2.setMyExcelCellValue(strTemp2);
								targetList.add(tempModel2);
							}
						}
					}

					cellModel.setColumnPosition(initColumnPosition);
				}
				if (targetList != null && targetList.size() > 0) {
					excelUtil.updateExcelCellValueInSheet(paramsModel.getExcelFilePathStr(), paramsModel.getSheetName(), targetList);
				}
			}
		}
		return cellModel.getColumnPosition() + nextColumnIndex;
	}

	/**
	 * 
	 * @Title: updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的处室经理姓名列和处室经理帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	@Override
	public int updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel) {
		if (paramsModel != null) {
			return updateAllValuesOfCurrentAndNextColumnByAfferentParams(paramsModel);
		}
		return 0;
	}

	/**
	 * 
	 * @Title: updateValuesOfSeventhAndEighthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的区县会计姓名列和区县会计帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	@Override
	public int updateValuesOfSeventhAndEighthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel) {
		if (paramsModel != null) {
			return updateAllValuesOfCurrentAndNextColumnByAfferentParams(paramsModel);
		}
		return 0;
	}

	/**
	 * 
	 * @Title: updateValuesOfNinthAndTenthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的部门副姓名列和部门副帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	@Override
	public int updateValuesOfNinthAndTenthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel) {
		if (paramsModel != null) {
			return updateAllValuesOfCurrentAndNextColumnByAfferentParams(paramsModel);
		}
		return 0;
	}

	/**
	 * 
	 * @Title: updateValuesOfEleventhAndTwelfthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的部门总姓名列和部门总帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	@Override
	public int updateValuesOfEleventhAndTwelfthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel) {
		if (paramsModel != null) {
			return updateAllValuesOfCurrentAndNextColumnByAfferentParams(paramsModel);
		}
		return 0;
	}

	/**
	 * 
	 * @Title: updateValuesOfFourthColumnOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的发起部门列的数据信息
	 * @param paramsModel
	 * @return
	 * @return: int
	 */
	@Override
	public int updateValuesOfFourthColumnOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel) {
		if (paramsModel != null) {
			/*
			 * 因为文档中发起部门列没有对应该列的帐号信息，故无需更新相邻列 whetherHandleAdjacentColumn 状态值设置为 false
			 */
			paramsModel.setWhetherHandleAdjacentColumn(false);
			return updateAllValuesOfCurrentAndNextColumnByAfferentParams(paramsModel);
		}
		return 0;
	}

	/**
	 * 
	 * @Title: updateMyDocumentSHExcelCellValueByAfferentParams
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @see cn.szx.cgzb.service.MyHandoverDocumentSHServiceI#updateMyDocumentSHExcelCellValueByAfferentParams(cn.szx.cgzb.pagemodel.MyAfferentParametersModel)
	 */
	@Override
	public int updateMyDocumentSHExcelCellValueByAfferentParams(MyAfferentParametersModel paramsModel) {
		int number = 0;
		if (paramsModel != null) {
			MyExcelCellModel updatedModel = paramsModel.getUpdatedCellModel();
			if (updatedModel != null) {
				excelUtil.updateExcelCellValueInSheet(paramsModel.getExcelFilePathStr(), paramsModel.getSheetName(), updatedModel);
				number = 1;
			}
		}
		return number;
	}
}
