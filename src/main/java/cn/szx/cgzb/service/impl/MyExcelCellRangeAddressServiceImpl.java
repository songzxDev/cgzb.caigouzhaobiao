package cn.szx.cgzb.service.impl;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szx.cgzb.dao.TexcelCellRangeAddressTabMapper;
import cn.szx.cgzb.dao.TexcelCellTabMapper;
import cn.szx.cgzb.model.TexcelCellRangeAddressTab;
import cn.szx.cgzb.pagemodel.DataGrid;
import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;
import cn.szx.cgzb.pagemodel.MyExcelCellRangeAddressModel;
import cn.szx.cgzb.service.MyExcelCellRangeAddressServiceI;
import cn.szx.cgzb.util.MyChangeModelUtil;

@Service("myExcelCellRangeAddressService")
public class MyExcelCellRangeAddressServiceImpl implements MyExcelCellRangeAddressServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyExcelCellRangeAddressServiceImpl.class);

	private TexcelCellRangeAddressTabMapper excelCellRangeAddressTabMapper;

	public TexcelCellRangeAddressTabMapper getExcelCellRangeAddressTabMapper() {
		return excelCellRangeAddressTabMapper;
	}

	@Autowired
	public void setExcelCellRangeAddressTabMapper(TexcelCellRangeAddressTabMapper excelCellRangeAddressTabMapper) {
		this.excelCellRangeAddressTabMapper = excelCellRangeAddressTabMapper;
	}

	private TexcelCellTabMapper excelCellTabMapper;

	public TexcelCellTabMapper getExcelCellTabMapper() {
		return excelCellTabMapper;
	}

	@Autowired
	public void setExcelCellTabMapper(TexcelCellTabMapper excelCellTabMapper) {
		this.excelCellTabMapper = excelCellTabMapper;
	}

	@Override
	public DataGrid<MyExcelCellRangeAddressModel> getMyExcelCellRangeAddressModelList(MyAfferentParametersModel paramsModel) throws Exception {
		DataGrid<MyExcelCellRangeAddressModel> dataGrid = new DataGrid<MyExcelCellRangeAddressModel>();
		List<TexcelCellRangeAddressTab> sourceList = new ArrayList<TexcelCellRangeAddressTab>();
		List<MyExcelCellRangeAddressModel> targetList = new ArrayList<MyExcelCellRangeAddressModel>();
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsModel != null) {
			String editFlag = paramsModel.getEditFlag();
			if (editFlag.equals("001")) {
				sourceList = excelCellRangeAddressTabMapper.selectByParams(params);
				if (sourceList != null && sourceList.size() > 0) {
					targetList = MyChangeModelUtil.changeCollectionModel(sourceList, MyExcelCellRangeAddressModel.class);
					dataGrid.setRows(targetList);
					dataGrid.setAmount(sourceList.size() + 0.0);
				} else {
					logger.info("没有查询到任何数据，请重新确认！");
				}
			}
		}

		return dataGrid;
	}

	@Override
	public Serializable save(MyAfferentParametersModel paramsModel) throws Exception {
		List<MyExcelCellRangeAddressModel> list = new ArrayList<MyExcelCellRangeAddressModel>();
		if (paramsModel != null) {
			String[] sheetNames = paramsModel.getSheetNames();
			int[] columnIndexs = paramsModel.getColumnIndexs();
			if (sheetNames != null && sheetNames.length > 0 && columnIndexs != null && columnIndexs.length > 0) {
				for (String sheetName : sheetNames) {
					if (sheetName.equals("28")) {
						continue;
					}
					for (int columnIndex : columnIndexs) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("ownedSheet", sheetName);
						params.put("excelCellColumnIndex", columnIndex);
						int minRowIndex = 0;
						int maxRowIndex = 0;
						MyExcelCellRangeAddressModel cellRangeAddressModel = new MyExcelCellRangeAddressModel();
						switch (columnIndex) {
						// columnIndex=2 文档：《交接文档（合同-专业-人员）-temp.xlsx》中的【专业类型】列
						case 2:
							if (sheetName.equals("25") || sheetName.equals("27") || sheetName.equals("30") || sheetName.equals("31") || sheetName.equals("37")) {
								// 日后待完善
								maxRowIndex = excelCellTabMapper.selectMaxRowIndexByParams(params);
								if ((maxRowIndex + 1) % 2 == 0) {
									logger.info("当前页签【" + sheetName + "】中的两个专业类型各自对应的数据总数不相同，请确认此页签中的两个专业类型的数据信息是否有误！");
									continue;
								}
								/*
								 * 页签25、27、30、31、37的专业类型列中的两个专业类型A、B各自的数据数量理论上是相等的， 下面的代码是计算需要合并单元格的专业类型A和专业类型B的首行和尾行下标算法， 详情可根据实际数据测算，如有不理解的地方请自行计算，经测试下面计算代码可以满足业务需求。
								 * 
								 * 例如：2016年3月31日页签【25】中的数据总数为：861条，行下标为：0 ~ 860，则专业类型A合并单元格中的尾行下标应为430，故出现下面算法。
								 */

								int halfMaxRowIndex = (maxRowIndex) >> 1;

								/*
								 * 页签25、27、30、31、37的专业类型列中的两个专业类型A、B各自的数据数量理论上是相等的， 下面的代码是计算需要合并单元格的专业类型A和专业类型B的首行和尾行下标算法， 详情可根据实际数据测算，如有不理解的地方请自行计算，经测试下面计算代码可以满足业务需求。
								 */
								int minRowIndexA = 1;
								int maxRowIndexA = halfMaxRowIndex;
								int minRowIndexB = halfMaxRowIndex + 1;
								int maxRowIndexB = maxRowIndex;

								// 将A列合并单元格所用到的信息存储到数据库中
								MyExcelCellRangeAddressModel cellRangeAddressModelA = new MyExcelCellRangeAddressModel();
								cellRangeAddressModelA.setFirstRow(minRowIndexA);
								cellRangeAddressModelA.setLastRow(maxRowIndexA);
								cellRangeAddressModelA.setFirstCol(columnIndex);
								cellRangeAddressModelA.setLastCol(columnIndex);
								cellRangeAddressModelA.setOwnedSheet(sheetName);
								TexcelCellRangeAddressTab tabA = MyChangeModelUtil.changeModel(cellRangeAddressModelA, TexcelCellRangeAddressTab.class);
								list.add(cellRangeAddressModelA);
								excelCellRangeAddressTabMapper.insertSelective(tabA);
								// 将B列合并单元格所用到的信息存储到数据库中
								MyExcelCellRangeAddressModel cellRangeAddressModelB = new MyExcelCellRangeAddressModel();
								cellRangeAddressModelB.setFirstRow(minRowIndexB);
								cellRangeAddressModelB.setLastRow(maxRowIndexB);
								cellRangeAddressModelB.setFirstCol(columnIndex);
								cellRangeAddressModelB.setLastCol(columnIndex);
								cellRangeAddressModelB.setOwnedSheet(sheetName);
								TexcelCellRangeAddressTab tabB = MyChangeModelUtil.changeModel(cellRangeAddressModelB, TexcelCellRangeAddressTab.class);
								list.add(cellRangeAddressModelB);
								excelCellRangeAddressTabMapper.insertSelective(tabB);
								continue;
							} else {
								minRowIndex = 1;
								maxRowIndex = excelCellTabMapper.selectMaxRowIndexByParams(params);
							}
							break;
						default:
							minRowIndex = 1;
							maxRowIndex = excelCellTabMapper.selectMaxRowIndexByParams(params);
							break;
						}
						cellRangeAddressModel.setFirstRow(minRowIndex);
						cellRangeAddressModel.setLastRow(maxRowIndex);
						cellRangeAddressModel.setFirstCol(columnIndex);
						cellRangeAddressModel.setLastCol(columnIndex);
						cellRangeAddressModel.setOwnedSheet(sheetName);
						TexcelCellRangeAddressTab tab = MyChangeModelUtil.changeModel(cellRangeAddressModel, TexcelCellRangeAddressTab.class);
						list.add(cellRangeAddressModel);
						excelCellRangeAddressTabMapper.insertSelective(tab);
					}

				}
			}
		}
		return list.size();
	}

	@Override
	public int removeAll() throws Exception {
		// TODO Auto-generated method stub
		return excelCellRangeAddressTabMapper.deleteAll();
	}
}
