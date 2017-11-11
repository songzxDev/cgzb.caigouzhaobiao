package cn.szx.cgzb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szx.cgzb.dao.TdepartmentAuditStaffInfoSHMapper;
import cn.szx.cgzb.model.TdepartmentAuditStaffInfoSH;
import cn.szx.cgzb.pagemodel.DataGrid;
import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyDepartmentAuditStaffInfoSH;
import cn.szx.cgzb.pagemodel.MyExcelCellModel;
import cn.szx.cgzb.service.MyDepartmentAuditStaffInfoSHServiceI;
import cn.szx.cgzb.util.MyChangeModelUtil;

import com.alibaba.fastjson.JSON;

@Service("myDepartmentAuditStaffInfoSHService")
public class MyDepartmentAuditStaffInfoSHServiceImpl implements MyDepartmentAuditStaffInfoSHServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyDepartmentAuditStaffInfoSHServiceImpl.class);

	private TdepartmentAuditStaffInfoSHMapper departmentAuditStaffInfoSHMapper;

	public TdepartmentAuditStaffInfoSHMapper getDepartmentAuditStaffInfoSHMapper() {
		return departmentAuditStaffInfoSHMapper;
	}

	@Autowired
	public void setDepartmentAuditStaffInfoSHMapper(TdepartmentAuditStaffInfoSHMapper departmentAuditStaffInfoSHMapper) {
		this.departmentAuditStaffInfoSHMapper = departmentAuditStaffInfoSHMapper;
	}

	/**
	 * 
	 * @Title: getEditUsedMyExcelCellModelList
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.MyDepartmentAuditStaffInfoSHServiceI#getEditUsedMyExcelCellModelList(cn.szx.cgzb.pagemodel.MyAfferentParametersModel)
	 */
	@Override
	public DataGrid<MyExcelCellModel> getEditUsedMyExcelCellModelList(MyAfferentParametersModel paramsModel) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		logger.info(JSON.toJSON(paramsModel));
		DataGrid<MyExcelCellModel> dataGrid = new DataGrid<MyExcelCellModel>();
		// 从数据表中获取信息
		
		// if (paramsModel != null && paramsModel.getSheetName().length() > 0) {
		// paramsMap.put("bmOwnedSheet", paramsModel.getSheetName());
		// }
		List<TdepartmentAuditStaffInfoSH> sourceList = departmentAuditStaffInfoSHMapper.selectByParams(paramsMap);

		if (sourceList != null && sourceList.size() > 0) {
			// 不同对象相同属性的对象集合间的复制操作，考虑到日后可能代码需要修改故添加此处代码
			List<MyDepartmentAuditStaffInfoSH> tempList = MyChangeModelUtil.changeCollectionModel(sourceList, MyDepartmentAuditStaffInfoSH.class);
			logger.info(JSON.toJSON(tempList));
			List<MyExcelCellModel> targetList = new ArrayList<MyExcelCellModel>();
			// for 循环开始
			for (MyDepartmentAuditStaffInfoSH tempModel : tempList) {
				if (tempModel != null) {
					final int tempNumber = 0;
					final int bmExcelCellRowIndex = tempModel.getBmExcelCellRowIndex();
					final int bmExcelCellColumnIndex = tempModel.getBmExcelCellColumnIndex();
					final String bmOwnedSheetName = tempModel.getBmOwnedSheet();
					/*
					 * 得到《交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx》文档中需要变更的【发起部门】的单元格信息并添加到targetList中
					 */
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber, tempModel.getBmExcelCellValue(), bmOwnedSheetName));
					/*
					 * 得到《交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx》文档中需要变更的【直属经理】和【直属经理登录帐号】的单元格信息并添加到targetList中
					 */
					// 姓名
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 1, tempModel.getRyExcelCellValueSj(), bmOwnedSheetName));
					// 登录帐号
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 2, tempModel.getRyExcelCellValueSjl(), bmOwnedSheetName));
					/*
					 * 得到《交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx》文档中需要变更的【区县会计】和【区县会计登录帐号】的单元格信息并添加到targetList中
					 */
					// 姓名
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 3, tempModel.getRyExcelCellValueKj(), bmOwnedSheetName));
					// 登录帐号
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 4, tempModel.getRyExcelCellValueKjl(), bmOwnedSheetName));
					/*
					 * 得到《交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx》文档中需要变更的【部门副总经理】和【部门副总经理登录帐号】的单元格信息并添加到targetList中
					 */
					// 姓名
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 5, tempModel.getRyExcelCellValueFz(), bmOwnedSheetName));
					// 登录帐号
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 6, tempModel.getRyExcelCellValueFzl(), bmOwnedSheetName));
					/*
					 * 得到《交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx》文档中需要变更的【部门正总经理】和【部门正总经理登录帐号】的单元格信息并添加到targetList中
					 */
					// 姓名
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 7, tempModel.getRyExcelCellValueZz(), bmOwnedSheetName));
					// 登录帐号
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 8, tempModel.getRyExcelCellValueZzl(), bmOwnedSheetName));
				}
			}
			// for 循环结束
			dataGrid.setRows(targetList);
			dataGrid.setAmount(targetList.size() + 0.0);
		}

		return dataGrid;
	}

	/**
	 * 
	 * @Title: removeAll
	 * @Description: TODO
	 * @see cn.szx.cgzb.service.MyDepartmentAuditStaffInfoSHServiceI#removeAll()
	 */
	@Override
	public void removeAll() {

		departmentAuditStaffInfoSHMapper.deleteAllData();

	}

	public static void main(String[] args) {
		int i = 0;
		List<Integer> list = new ArrayList<Integer>();
		list.add(++i);
		list.add(++i);
		list.add(++i);
		list.add(++i);
		list.add(++i);
		list.add(++i);
		list.add(++i);
		logger.info(JSON.toJSON(list));
	}

	/**
	 * 
	 * @Title: getRemoveUsedMyExcelCellModelList
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.MyDepartmentAuditStaffInfoSHServiceI#getRemoveUsedMyExcelCellModelList(cn.szx.cgzb.pagemodel.MyAfferentParametersModel)
	 */
	@Override
	public DataGrid<MyExcelCellModel> getRemoveUsedMyExcelCellModelList(MyAfferentParametersModel paramsModel) throws Exception {
		DataGrid<MyExcelCellModel> dataGrid = new DataGrid<MyExcelCellModel>();
		// 从数据表中获取信息
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (paramsModel != null && paramsModel.getSheetName().length() > 0) {
			paramsMap.put("bmOwnedSheet", paramsModel.getSheetName());
			if (paramsModel.getCellModel() != null) {
				paramsMap.put("bmExcelCellValue", paramsModel.getCellModel().getMyExcelCellValue());
			}
		}
		List<TdepartmentAuditStaffInfoSH> sourceList = departmentAuditStaffInfoSHMapper.selectByParams(paramsMap);

		if (sourceList != null && sourceList.size() > 0) {
			// 不同对象相同属性的对象集合间的复制操作，考虑到日后可能代码需要修改故添加此处代码
			List<MyDepartmentAuditStaffInfoSH> tempList = MyChangeModelUtil.changeCollectionModel(sourceList, MyDepartmentAuditStaffInfoSH.class);
			logger.info(JSON.toJSON(tempList));
			List<MyExcelCellModel> targetList = new ArrayList<MyExcelCellModel>();
			// for 循环开始
			for (MyDepartmentAuditStaffInfoSH tempModel : tempList) {
				if (tempModel != null) {
					final int tempNumber = 0;
					final int bmExcelCellRowIndex = tempModel.getBmExcelCellRowIndex();
					final int bmExcelCellColumnIndex = tempModel.getBmExcelCellColumnIndex();
					final String bmOwnedSheetName = tempModel.getBmOwnedSheet();
					/*
					 * 得到《交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx》文档中需要变更的【发起部门】的单元格信息并添加到targetList中
					 */
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber, tempModel.getBmExcelCellValue(), bmOwnedSheetName));
				}
			}
			// for 循环结束
			dataGrid.setRows(targetList);
			dataGrid.setAmount(targetList.size() + 0.0);
		}
		return dataGrid;
	}

	@Override
	public MyExcelCellModel getMyExcelCell(MyAfferentParametersModel paramsModel) throws Exception {

		return null;
	}

	@Override
	public MyExcelCellModel getRemoveUsedMyExcelCell(MyAfferentParametersModel paramsModel) throws Exception {
		return null;
	}
}
