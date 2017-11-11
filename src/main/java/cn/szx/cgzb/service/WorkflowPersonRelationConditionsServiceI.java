package cn.szx.cgzb.service;

import java.util.List;

import cn.szx.cgzb.model.TworkflowPersonRelationConditions;

public interface WorkflowPersonRelationConditionsServiceI {
	
	/**
	 * 
	 * @Title: save
	 * @Description: TODO
	 * @param lists
	 * @return
	 * @return: int
	 */
	public int save(List<TworkflowPersonRelationConditions> lists);
	
	/**
	 * 
	 * @Title: cleanAll
	 * @Description: TODO
	 * @return: void
	 */
	public void remove();
	
}
