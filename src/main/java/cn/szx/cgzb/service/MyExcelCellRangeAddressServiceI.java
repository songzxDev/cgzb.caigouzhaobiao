package cn.szx.cgzb.service;

import java.io.Serializable;

import cn.szx.cgzb.pagemodel.DataGrid;
import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyExcelCellRangeAddressModel;

public interface MyExcelCellRangeAddressServiceI {

	public Serializable save(MyAfferentParametersModel paramsModel) throws Exception;

	public DataGrid<MyExcelCellRangeAddressModel> getMyExcelCellRangeAddressModelList(MyAfferentParametersModel paramsModel) throws Exception;

	public int removeAll() throws Exception;

}
