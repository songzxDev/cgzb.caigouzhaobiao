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
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���ķ������е�������Ϣ
	 * @param paramsModel
	 * @return
	 * @return: int
	 */
	public int updateValuesOfFourthColumnOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���Ĵ��Ҿ��������кʹ��Ҿ����ʺ����е�������Ϣ
	 * @return
	 * @return: int
	 */
	public int updateValuesOfFifthAndSixthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfSeventhAndEighthColumnsOfFirstRowByAfferentParams
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx�������ػ�������к����ػ���ʺ����е�������Ϣ
	 * @return
	 * @return: int
	 */
	public int updateValuesOfSeventhAndEighthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfNinthAndTenthColumnsOfFirstRowByAfferentParams
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���Ĳ��Ÿ������кͲ��Ÿ��ʺ����е�������Ϣ
	 * @return
	 * @return: int
	 */
	public int updateValuesOfNinthAndTenthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);

	/**
	 * 
	 * @Title: updateValuesOfEleventhAndTwelfthColumnsOfFirstRowByAfferentParams
	 * @Description: ���¡������ĵ�����ͬ-רҵ-��Ա��XXXXX.xlsx���Ĳ����������кͲ������ʺ����е�������Ϣ
	 * @return
	 * @return: int
	 */
	public int updateValuesOfEleventhAndTwelfthColumnsOfFirstRowByAfferentParams(MyAfferentParametersModel paramsModel);
}
