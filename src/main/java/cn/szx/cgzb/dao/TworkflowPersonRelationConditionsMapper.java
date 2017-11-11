package cn.szx.cgzb.dao;

import cn.szx.cgzb.model.TworkflowPersonRelationConditions;

public interface TworkflowPersonRelationConditionsMapper {
	
	int deleteByPrimaryKey(Integer workflowPersonRelationConditionsId);

	int insert(TworkflowPersonRelationConditions record);

	int insertSelective(TworkflowPersonRelationConditions record);

	TworkflowPersonRelationConditions selectByPrimaryKey(Integer workflowPersonRelationConditionsId);

	int updateByPrimaryKeySelective(TworkflowPersonRelationConditions record);

	int updateByPrimaryKey(TworkflowPersonRelationConditions record);

	int deleteAll();
}