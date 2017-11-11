package cn.szx.cgzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szx.cgzb.dao.TworkflowPersonRelationMapper;
import cn.szx.cgzb.model.TworkflowPersonRelation;
import cn.szx.cgzb.pagemodel.MyDataGrid;
import cn.szx.cgzb.service.WorkflowPersonRelationServiceI;

@Service("workflowPersonRelationService")
public class WorkflowPersonRelationServiceImpl implements WorkflowPersonRelationServiceI {

	private TworkflowPersonRelationMapper workflowPersonRelationMapper;

	public TworkflowPersonRelationMapper getWorkflowPersonRelationMapper() {
		return workflowPersonRelationMapper;
	}

	@Autowired
	public void setWorkflowPersonRelationMapper(TworkflowPersonRelationMapper workflowPersonRelationMapper) {
		this.workflowPersonRelationMapper = workflowPersonRelationMapper;
	}

	/**
	 * 
	 * @Title: getWorkflowPersonRelation
	 * @Description: TODO
	 * @param parmas
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.WorkflowPersonRelationI#getWorkflowPersonRelation(java.util.Map)
	 */
	@Override
	public List<TworkflowPersonRelation> getWorkflowPersonRelation(Map<String, Object> params) throws Exception {
		return workflowPersonRelationMapper.selectByParams(params);
	}

	/**
	 * 
	 * @Title: getWorkflowPersonRelationByConditions
	 * @Description: TODO
	 * @param params
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.WorkflowPersonRelationServiceI#getWorkflowPersonRelationByConditions(java.util.Map)
	 */
	@Override
	public MyDataGrid getWorkflowPersonRelationByConditions(Map<String, Object> params) throws Exception {
		MyDataGrid dataGrid = new MyDataGrid();
		dataGrid.setDataGridList(getWorkflowPersonRelation(params));
		dataGrid.setDataGridsCount(workflowPersonRelationMapper.selectCountByParams(params));
		return dataGrid;
	}

	/**
	 * 
	 * @Title: getWorkflowPersonRelationByConditionsSecondVersion
	 * @Description: TODO
	 * @param params
	 * @return
	 * @throws Exception
	 * @see cn.szx.cgzb.service.WorkflowPersonRelationServiceI#getWorkflowPersonRelationByConditionsSecondVersion(java.util.Map)
	 */
	@Override
	public MyDataGrid getWorkflowPersonRelationByConditionsSecondVersion(Map<String, Object> params) throws Exception {
		MyDataGrid dataGrid = new MyDataGrid();
		dataGrid.setDataGridList(workflowPersonRelationMapper.selectByParamsSecondVersion(params));
		dataGrid.setDataGridsCount(workflowPersonRelationMapper.selectCountByParamsSecondVersion(params));
		return dataGrid;
	}

}
