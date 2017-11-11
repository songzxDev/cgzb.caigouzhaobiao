package cn.szx.cgzb.service;

import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;

public interface MyHandoverDocumentSHServiceI {

	/**
	 * 
	 * @Title: updateDocumentSHExcelCellValueByAfferentParams
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @return: int
	 */
	public int updateMyDocumentSHExcelCellValueByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfFourthColumnOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的发起部门列的数据信息
	 * @param paramsModel
	 * @return
	 * @return: int
	 */
	public int updateValuesOfFourthColumnOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的处室经理姓名列和处室经理帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	public int updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfSeventhAndEighthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的区县会计姓名列和区县会计帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	public int updateValuesOfSeventhAndEighthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfNinthAndTenthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的部门副姓名列和部门副帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	public int updateValuesOfNinthAndTenthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfEleventhAndTwelfthColumnsOfFirstRowByAfferentParams
	 * @Description: 更新【交接文档（合同-专业-人员）XXXXX.xlsx】的部门总姓名列和部门总帐号列中的数据信息
	 * @return
	 * @return: int
	 */
	public int updateValuesOfEleventhAndTwelfthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);
}
