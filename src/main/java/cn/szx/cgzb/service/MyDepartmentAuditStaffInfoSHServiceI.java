package cn.szx.cgzb.service;

import cn.szx.cgzb.pagemodel.DataGrid;
import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyExcelCellModel;

public interface MyDepartmentAuditStaffInfoSHServiceI {

	public DataGrid<MyExcelCellModel> getEditUsedMyExcelCellModelList(MyAfferentParametersModel paramsModel) throws Exception;

	public DataGrid<MyExcelCellModel> getRemoveUsedMyExcelCellModelList(MyAfferentParametersModel paramsModel) throws Exception;

	public void removeAll();

	public MyExcelCellModel getMyExcelCell(MyAfferentParametersModel paramsModel) throws Exception;
	
	public MyExcelCellModel getRemoveUsedMyExcelCell(MyAfferentParametersModel paramsModel) throws Exception;

}
