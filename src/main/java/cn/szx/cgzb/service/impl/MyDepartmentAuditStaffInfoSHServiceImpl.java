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
		// �����ݱ��л�ȡ��Ϣ
		
		// if (paramsModel != null && paramsModel.getSheetName().length() > 0) {
		// paramsMap.put("bmOwnedSheet", paramsModel.getSheetName());
		// }
		List<TdepartmentAuditStaffInfoSH> sourceList = departmentAuditStaffInfoSHMapper.selectByParams(paramsMap);

		if (sourceList != null && sourceList.size() > 0) {
			// ��ͬ������ͬ���ԵĶ��󼯺ϼ�ĸ��Ʋ��������ǵ��պ���ܴ�����Ҫ�޸Ĺ���Ӵ˴�����
			List<MyDepartmentAuditStaffInfoSH> tempList = MyChangeModelUtil.changeCollectionModel(sourceList, MyDepartmentAuditStaffInfoSH.class);
			logger.info(JSON.toJSON(tempList));
			List<MyExcelCellModel> targetList = new ArrayList<MyExcelCellModel>();
			// for ѭ����ʼ
			for (MyDepartmentAuditStaffInfoSH tempModel : tempList) {
				if (tempModel != null) {
					final int tempNumber = 0;
					final int bmExcelCellRowIndex = tempModel.getBmExcelCellRowIndex();
					final int bmExcelCellColumnIndex = tempModel.getBmExcelCellColumnIndex();
					final String bmOwnedSheetName = tempModel.getBmOwnedSheet();
					/*
					 * �õ��������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ�����Ҫ����ġ������š��ĵ�Ԫ����Ϣ����ӵ�targetList��
					 */
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber, tempModel.getBmExcelCellValue(), bmOwnedSheetName));
					/*
					 * �õ��������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ�����Ҫ����ġ�ֱ�������͡�ֱ�������¼�ʺš��ĵ�Ԫ����Ϣ����ӵ�targetList��
					 */
					// ����
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 1, tempModel.getRyExcelCellValueSj(), bmOwnedSheetName));
					// ��¼�ʺ�
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 2, tempModel.getRyExcelCellValueSjl(), bmOwnedSheetName));
					/*
					 * �õ��������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ�����Ҫ����ġ����ػ�ơ��͡����ػ�Ƶ�¼�ʺš��ĵ�Ԫ����Ϣ����ӵ�targetList��
					 */
					// ����
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 3, tempModel.getRyExcelCellValueKj(), bmOwnedSheetName));
					// ��¼�ʺ�
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 4, tempModel.getRyExcelCellValueKjl(), bmOwnedSheetName));
					/*
					 * �õ��������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ�����Ҫ����ġ����Ÿ��ܾ����͡����Ÿ��ܾ����¼�ʺš��ĵ�Ԫ����Ϣ����ӵ�targetList��
					 */
					// ����
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 5, tempModel.getRyExcelCellValueFz(), bmOwnedSheetName));
					// ��¼�ʺ�
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 6, tempModel.getRyExcelCellValueFzl(), bmOwnedSheetName));
					/*
					 * �õ��������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ�����Ҫ����ġ��������ܾ����͡��������ܾ����¼�ʺš��ĵ�Ԫ����Ϣ����ӵ�targetList��
					 */
					// ����
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 7, tempModel.getRyExcelCellValueZz(), bmOwnedSheetName));
					// ��¼�ʺ�
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber + 8, tempModel.getRyExcelCellValueZzl(), bmOwnedSheetName));
				}
			}
			// for ѭ������
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
		// �����ݱ��л�ȡ��Ϣ
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (paramsModel != null && paramsModel.getSheetName().length() > 0) {
			paramsMap.put("bmOwnedSheet", paramsModel.getSheetName());
			if (paramsModel.getCellModel() != null) {
				paramsMap.put("bmExcelCellValue", paramsModel.getCellModel().getMyExcelCellValue());
			}
		}
		List<TdepartmentAuditStaffInfoSH> sourceList = departmentAuditStaffInfoSHMapper.selectByParams(paramsMap);

		if (sourceList != null && sourceList.size() > 0) {
			// ��ͬ������ͬ���ԵĶ��󼯺ϼ�ĸ��Ʋ��������ǵ��պ���ܴ�����Ҫ�޸Ĺ���Ӵ˴�����
			List<MyDepartmentAuditStaffInfoSH> tempList = MyChangeModelUtil.changeCollectionModel(sourceList, MyDepartmentAuditStaffInfoSH.class);
			logger.info(JSON.toJSON(tempList));
			List<MyExcelCellModel> targetList = new ArrayList<MyExcelCellModel>();
			// for ѭ����ʼ
			for (MyDepartmentAuditStaffInfoSH tempModel : tempList) {
				if (tempModel != null) {
					final int tempNumber = 0;
					final int bmExcelCellRowIndex = tempModel.getBmExcelCellRowIndex();
					final int bmExcelCellColumnIndex = tempModel.getBmExcelCellColumnIndex();
					final String bmOwnedSheetName = tempModel.getBmOwnedSheet();
					/*
					 * �õ��������ĵ�����ͬ-רҵ-��Ա��-XXXX��XX��XX��.xlsx���ĵ�����Ҫ����ġ������š��ĵ�Ԫ����Ϣ����ӵ�targetList��
					 */
					targetList.add(new MyExcelCellModel(bmExcelCellRowIndex, bmExcelCellColumnIndex + tempNumber, tempModel.getBmExcelCellValue(), bmOwnedSheetName));
				}
			}
			// for ѭ������
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
