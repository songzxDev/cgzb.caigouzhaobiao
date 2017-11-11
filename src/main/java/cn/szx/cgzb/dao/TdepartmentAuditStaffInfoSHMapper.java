package cn.szx.cgzb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import cn.szx.cgzb.model.TdepartmentAuditStaffInfoSH;

public interface TdepartmentAuditStaffInfoSHMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TdepartmentAuditStaffInfoSH record);

	int insertSelective(TdepartmentAuditStaffInfoSH record);

	TdepartmentAuditStaffInfoSH selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TdepartmentAuditStaffInfoSH record);

	int updateByPrimaryKey(TdepartmentAuditStaffInfoSH record);

	List<TdepartmentAuditStaffInfoSH> selectAll();

	List<TdepartmentAuditStaffInfoSH> selectByParams(Map<String, Object> params);

	@Delete("delete from department_audit_staff_info_sh")
	int deleteAllData();

}