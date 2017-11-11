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
			 * ȡ��ǰsheet��X�еĵ����е�Ԫ����Ϣ�����������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ��е�XXXX�е�������Ϣ
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
				 * ȡ��ǰsheet��X+1�еĵ����е�Ԫ����Ϣ�����������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ��еĶ�ӦXXXX���ʺ��е�������Ϣ
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
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���Ĵ��Ҿ��������кʹ��Ҿ����ʺ����е�������Ϣ
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
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx�������ػ�������к����ػ���ʺ����е�������Ϣ
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
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���Ĳ��Ÿ������кͲ��Ÿ��ʺ����е�������Ϣ
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
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���Ĳ����������кͲ������ʺ����е�������Ϣ
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
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���ķ������е�������Ϣ
	 * @param paramsModel
	 * @return
	 * @return: int
	 */
	@Override
	public int updateValuesOfFourthColumnOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel) {
		if (paramsModel != null) {
			/*
			 * ��Ϊ�ĵ��з�������û�ж�Ӧ���е��ʺ���Ϣ����������������� whetherHandleAdjacentColumn ״ֵ̬����Ϊ false
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
