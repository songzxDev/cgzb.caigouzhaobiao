package cn.szx.cgzb.dao;

import java.util.List;
import java.util.Map;

import cn.szx.cgzb.model.TworkflowPersonRelation;

public interface TworkflowPersonRelationMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TworkflowPersonRelation record);

	int insertSelective(TworkflowPersonRelation record);

	TworkflowPersonRelation selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TworkflowPersonRelation record);

	int updateByPrimaryKey(TworkflowPersonRelation record);

	List<TworkflowPersonRelation> selectByParams(Map<String, Object> params);

	double selectCountByParams(Map<String, Object> params);
	
	List<TworkflowPersonRelation> selectByParamsSecondVersion(Map<String, Object> params);

	double selectCountByParamsSecondVersion(Map<String, Object> params);
	
}