package cn.szx.cgzb.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.szx.cgzb.dao.TexcelCellTabMapper;
import cn.szx.cgzb.model.TexcelCellTab;
import cn.szx.cgzb.pagemodel.DataGrid;
import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyExcelCellModel;
import cn.szx.cgzb.service.MyExcelCellServiceI;
import cn.szx.cgzb.util.MyHandleExcelUtil;

@Service("myExcelCellService")
public class MyExcelCellServiceImpl implements MyExcelCellServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyExcelCellServiceImpl.class);

	private TexcelCellTabMapper excelCellMapper;

	public TexcelCellTabMapper getExcelCellMapper() {
		return excelCellMapper;
	}

	@Autowired
	public void setExcelCellMapper(TexcelCellTabMapper excelCellMapper) {
		this.excelCellMapper = excelCellMapper;
	}

	private static final MyHandleExcelUtil MY_EXCEL_UTIL = MyHandleExcelUtil.getInstance();

	/**
	 * 
	 * @Title: save
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.MyExcelCellServiceI#save(cn.szx.cgzb.pagemodel.MyAfferentParametersModel)
	 */
	@Override
	public Serializable save(MyAfferentParametersModel paramsModel) throws Exception {
		int nums = 0;
		if (paramsModel != null) {
			List<MyExcelCellModel> tempList = new ArrayList<MyExcelCellModel>();
			// description 的值为：MULTIPLE代表会更新多个页签
			if (paramsModel.getDescription().equals("MULTIPLE")) {
				tempList = MY_EXCEL_UTIL.getExcelAllCell(paramsModel.getExcelFilePathStr(), paramsModel.getSheetNames());
			} else {
				tempList = MY_EXCEL_UTIL.getExcelAllCellFromSheet(paramsModel.getExcelFilePathStr(), paramsModel.getSheetName());
			}

			if (tempList != null && tempList.size() > 0) {
				for (MyExcelCellModel tempModel : tempList) {

					TexcelCellTab excelCell = new TexcelCellTab();
					if (tempModel != null) {
						excelCell.setExcelCellRowIndex(tempModel.getRowPosition());
						excelCell.setExcelCellColumnIndex(tempModel.getColumnPosition());
						excelCell.setExcelCellValue(tempModel.getMyExcelCellValue());
						excelCell.setOwnedSheet(tempModel.getOwnedSheetName());
						excelCell.setAttribute2(tempModel.getMyDescribe());
						logger.info("nums初始值：" + nums + "        ################################");
						nums = nums + excelCellMapper.insertSelective(excelCell);
						logger.info("执行语句后nums的值：" + nums + "        ################################");
					}

				}
			}
		}
		return nums;
	}

	@Override
	public int removeAll() throws Exception {
		return excelCellMapper.deleteAll();
	}

	@Override
	public MyExcelCellModel getMyExcelCellModel(MyAfferentParametersModel paramsModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: getMyExcelCellModelList
	 * @Description: TODO
	 * @param paramsModel
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.MyExcelCellServiceI#getMyExcelCellModelList(cn.szx.cgzb.pagemodel.MyAfferentParametersModel)
	 */
	@Override
	public DataGrid<MyExcelCellModel> getMyExcelCellModelList(MyAfferentParametersModel paramsModel) throws Exception {
		DataGrid<MyExcelCellModel> dataGrid = new DataGrid<MyExcelCellModel>();
		if (paramsModel != null) {
			logger.info("##############################   " + JSON.toJSON(paramsModel) + "   ##############################");
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("ownedSheets", paramsModel.getSheetNames());
			if (paramsModel.getColumnIndexs() != null && paramsModel.getColumnIndexs().length > 0) {
				paramsMap.put("excelCellColumnIndexs", paramsModel.getColumnIndexs());
			}
			paramsMap.put("ownedSheet", paramsModel.getSheetName());
			paramsMap.put("excelCellValue", paramsModel.getOriginalValueOfCell());

			List<TexcelCellTab> tempList = excelCellMapper.selectByParams(paramsMap);
			List<MyExcelCellModel> targetList = new ArrayList<MyExcelCellModel>();
			if (tempList != null && tempList.size() > 0) {
				for (TexcelCellTab excelCellTab : tempList) {
					if (excelCellTab != null) {
						MyExcelCellModel tempModel = new MyExcelCellModel();
						tempModel.setColumnPosition(excelCellTab.getExcelCellColumnIndex());
						tempModel.setMyDescribe(excelCellTab.getAttribute2());
						tempModel.setRowPosition(excelCellTab.getExcelCellRowIndex());
						tempModel.setOwnedSheetName(excelCellTab.getOwnedSheet());
						tempModel.setMyExcelCellValue(excelCellTab.getExcelCellValue());
						tempModel.setId(excelCellTab.getId());
						// paramsModel.getDescription() 的值为：COPIED 代表执行excel文件插入复制行的操作
						if (paramsModel.getDescription().equals("COPIED")) {
							tempModel.setAttribute3(paramsModel.getCellValueAfterReplacement());
						}
						targetList.add(tempModel);
					}
				}
				dataGrid.setAmount(tempList.size() + 0.00);
			}
			dataGrid.setRows(targetList);
		}
		return dataGrid;
	}

	@Override
	public Serializable saveSecondVersion(MyAfferentParametersModel paramsModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: TODO
	 * @param model
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.MyExcelCellServiceI#edit(cn.szx.cgzb.pagemodel.MyExcelCellModel)
	 */
	@Override
	public MyExcelCellModel edit(MyExcelCellModel model) throws Exception {
		if (model != null) {
			TexcelCellTab excelCellTab = excelCellMapper.selectByPrimaryKey(model.getId());
			/*
			 * 其他属性2016年1月18日拟定无需更改； attribute1 设置为该条数据的处理状态，'001' 未处理，'002' 处理中，'003' 已处理； usable_status 逻辑删除标志，'1' 正常，'0' 被逻辑删除
			 */
			excelCellTab.setAttribute1(model.getAttribute1());
			/*
			 * 其他属性2016年1月18日拟定无需更改； attribute2 设置为该条数据的描述字段
			 */
			excelCellTab.setAttribute1(model.getAttribute2());
			excelCellTab.setUsableStatus(model.getUsableStatus());
			excelCellMapper.updateByPrimaryKeySelective(excelCellTab);
		}
		/*
		 * 返回值日后待完善
		 */
		return null;
	}

	@Override
	public int getGeneralLastRowIndex(String sheetName) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ownedSheet", sheetName);
		int index = excelCellMapper.selectMaxRowIndexByParams(param);
		logger.info("通用的最后一行数据的行下标为：" + index);
		return index;
	}

}
