package cn.szx.cgzb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szx.cgzb.dao.TworkflowPersonRelationConditionsMapper;
import cn.szx.cgzb.model.TworkflowPersonRelationConditions;
import cn.szx.cgzb.service.WorkflowPersonRelationConditionsServiceI;

@Service("workflowPersonRelationConditionsService")
public class WorkflowPersonRelationConditionsServiceImpl implements WorkflowPersonRelationConditionsServiceI {

	private TworkflowPersonRelationConditionsMapper workflowPersonRelationConditionsMapper;

	public TworkflowPersonRelationConditionsMapper getWorkflowPersonRelationConditionsMapper() {
		return workflowPersonRelationConditionsMapper;
	}

	@Autowired
	public void setWorkflowPersonRelationConditionsMapper(TworkflowPersonRelationConditionsMapper workflowPersonRelationConditionsMapper) {
		this.workflowPersonRelationConditionsMapper = workflowPersonRelationConditionsMapper;
	}

	@Override
	public int save(List<TworkflowPersonRelationConditions> lists) {
		int counts = 0;
		for (TworkflowPersonRelationConditions tWorkflowPersonRelationConditions : lists) {
			int count = 0;
			count = workflowPersonRelationConditionsMapper.insertSelective(tWorkflowPersonRelationConditions);
			counts = count + counts;
		}
		return counts;
	}

	@Override
	public void remove() {
		workflowPersonRelationConditionsMapper.deleteAll();
	}

}
