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
			// description ��ֵΪ��MULTIPLE�������¶��ҳǩ
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
						logger.info("nums��ʼֵ��" + nums + "        ################################");
						nums = nums + excelCellMapper.insertSelective(excelCell);
						logger.info("ִ������nums��ֵ��" + nums + "        ################################");
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
						// paramsModel.getDescription() ��ֵΪ��COPIED ����ִ��excel�ļ����븴���еĲ���
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
			 * ��������2016��1��18���ⶨ������ģ� attribute1 ����Ϊ�������ݵĴ���״̬��'001' δ����'002' �����У�'003' �Ѵ��� usable_status �߼�ɾ����־��'1' ������'0' ���߼�ɾ��
			 */
			excelCellTab.setAttribute1(model.getAttribute1());
			/*
			 * ��������2016��1��18���ⶨ������ģ� attribute2 ����Ϊ�������ݵ������ֶ�
			 */
			excelCellTab.setAttribute1(model.getAttribute2());
			excelCellTab.setUsableStatus(model.getUsableStatus());
			excelCellMapper.updateByPrimaryKeySelective(excelCellTab);
		}
		/*
		 * ����ֵ�պ������
		 */
		return null;
	}

	@Override
	public int getGeneralLastRowIndex(String sheetName) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ownedSheet", sheetName);
		int index = excelCellMapper.selectMaxRowIndexByParams(param);
		logger.info("ͨ�õ����һ�����ݵ����±�Ϊ��" + index);
		return index;
	}

}
