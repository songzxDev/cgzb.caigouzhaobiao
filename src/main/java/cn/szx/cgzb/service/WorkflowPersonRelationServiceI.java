package cn.szx.cgzb.service;

import java.util.List;
import java.util.Map;

import cn.szx.cgzb.model.TworkflowPersonRelation;
import cn.szx.cgzb.pagemodel.MyDataGrid;

public interface WorkflowPersonRelationServiceI {

	/**
	 * 
	 * @Title: getWorkflowPersonRelation
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * @return: List<TworkflowPersonRelation>
	 */
	public List<TworkflowPersonRelation> getWorkflowPersonRelation(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @Title: getWorkflowPersonRelationByConditions
	 * @Description: TODO
	 * @param params
	 * @return
	 * @throws Exception
	 * @return: MyDataGrid
	 */
	public MyDataGrid getWorkflowPersonRelationByConditions(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @Title: getWorkflowPersonRelationByConditionsSecondVersion
	 * @Description: TODO
	 * @param params
	 * @return
	 * @throws Exception
	 * @return: MyDataGrid
	 */
	public MyDataGrid getWorkflowPersonRelationByConditionsSecondVersion(Map<String, Object> params) throws Exception;

}
