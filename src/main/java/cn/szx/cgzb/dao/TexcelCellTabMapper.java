package cn.szx.cgzb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import cn.szx.cgzb.model.TexcelCellTab;

public interface TexcelCellTabMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TexcelCellTab record);

	int insertSelective(TexcelCellTab record);

	TexcelCellTab selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TexcelCellTab record);

	int updateByPrimaryKey(TexcelCellTab record);

	@Delete("delete from excel_cell_tab")
	int deleteAll();

	@Select("select * from excel_cell_tab where EXCEL_CELL_VALUE = #{excelCellValue,jdbcType=VARCHAR}")
	TexcelCellTab selectByExcelCellValue(String excelCellValue);

	List<TexcelCellTab> selectByParams(Map<String, Object> params);

	double selectCountByParams(Map<String, Object> params);
	
	int selectMaxRowIndexByParams(Map<String, Object> params);
	
}