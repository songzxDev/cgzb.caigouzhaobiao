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
					logger.info("û�в�ѯ���κ����ݣ�������ȷ�ϣ�");
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
						// columnIndex=2 �ĵ����������ĵ�����ͬ-רҵ-��Ա��-temp.xlsx���еġ�רҵ���͡���
						case 2:
							if (sheetName.equals("25") || sheetName.equals("27") || sheetName.equals("30") || sheetName.equals("31") || sheetName.equals("37")) {
								// �պ������
								maxRowIndex = excelCellTabMapper.selectMaxRowIndexByParams(params);
								if ((maxRowIndex + 1) % 2 == 0) {
									logger.info("��ǰҳǩ��" + sheetName + "���е�����רҵ���͸��Զ�Ӧ��������������ͬ����ȷ�ϴ�ҳǩ�е�����רҵ���͵�������Ϣ�Ƿ�����");
									continue;
								}
								/*
								 * ҳǩ25��27��30��31��37��רҵ�������е�����רҵ����A��B���Ե�������������������ȵģ� ����Ĵ����Ǽ�����Ҫ�ϲ���Ԫ���רҵ����A��רҵ����B�����к�β���±��㷨�� ����ɸ���ʵ�����ݲ��㣬���в����ĵط������м��㣬�����������������������ҵ������
								 * 
								 * ���磺2016��3��31��ҳǩ��25���е���������Ϊ��861�������±�Ϊ��0 ~ 860����רҵ����A�ϲ���Ԫ���е�β���±�ӦΪ430���ʳ��������㷨��
								 */

								int halfMaxRowIndex = (maxRowIndex) >> 1;

								/*
								 * ҳǩ25��27��30��31��37��רҵ�������е�����רҵ����A��B���Ե�������������������ȵģ� ����Ĵ����Ǽ�����Ҫ�ϲ���Ԫ���רҵ����A��רҵ����B�����к�β���±��㷨�� ����ɸ���ʵ�����ݲ��㣬���в����ĵط������м��㣬�����������������������ҵ������
								 */
								int minRowIndexA = 1;
								int maxRowIndexA = halfMaxRowIndex;
								int minRowIndexB = halfMaxRowIndex + 1;
								int maxRowIndexB = maxRowIndex;

								// ��A�кϲ���Ԫ�����õ�����Ϣ�洢�����ݿ���
								MyExcelCellRangeAddressModel cellRangeAddressModelA = new MyExcelCellRangeAddressModel();
								cellRangeAddressModelA.setFirstRow(minRowIndexA);
								cellRangeAddressModelA.setLastRow(maxRowIndexA);
								cellRangeAddressModelA.setFirstCol(columnIndex);
								cellRangeAddressModelA.setLastCol(columnIndex);
								cellRangeAddressModelA.setOwnedSheet(sheetName);
								TexcelCellRangeAddressTab tabA = MyChangeModelUtil.changeModel(cellRangeAddressModelA, TexcelCellRangeAddressTab.class);
								list.add(cellRangeAddressModelA);
								excelCellRangeAddressTabMapper.insertSelective(tabA);
								// ��B�кϲ���Ԫ�����õ�����Ϣ�洢�����ݿ���
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
