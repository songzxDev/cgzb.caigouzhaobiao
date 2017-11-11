package cn.szx.cgzb.service;

import java.io.Serializable;

import cn.szx.cgzb.pagemodel.DataGrid;
import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyExcelCellModel;

public interface MyExcelCellServiceI {

	/**
	 * 
	 * @Title: save
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable save(MyAfferentParametersModel paramsModel) throws Exception;

	/**
	 * 
	 * @Title: save
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: Serializable
	 */
	public Serializable saveSecondVersion(MyAfferentParametersModel paramsModel) throws Exception;

	/**
	 * 
	 * @Title: removeAll
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int removeAll() throws Exception;

	/**
	 * 
	 * @Title: getMyExcelCellModel
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: MyExcelCellModel
	 */
	public MyExcelCellModel getMyExcelCellModel(MyAfferentParametersModel paramsModel) throws Exception;

	/**
	 * 
	 * @Title: getMyExcelCellModelList
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @return: DataGrid<MyExcelCellModel>
	 */
	public DataGrid<MyExcelCellModel> getMyExcelCellModelList(MyAfferentParametersModel paramsModel) throws Exception;

	/**
	 * 
	 * @Title: edit
	 * @Description: TODO
	 * @param model
	 * @return
	 * @throws Exception
	 * @return: MyExcelCellModel
	 */
	public MyExcelCellModel edit(MyExcelCellModel model) throws Exception;
	
	/**
	 * 
	 * @Title: getGeneralLastRowIndex
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int getGeneralLastRowIndex(String sheetName) throws Exception;

}
