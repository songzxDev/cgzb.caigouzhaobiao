package cn.szx.cgzb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import cn.szx.cgzb.model.TexcelCellRangeAddressTab;

public interface TexcelCellRangeAddressTabMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TexcelCellRangeAddressTab record);

	int insertSelective(TexcelCellRangeAddressTab record);

	TexcelCellRangeAddressTab selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TexcelCellRangeAddressTab record);

	int updateByPrimaryKey(TexcelCellRangeAddressTab record);

	List<TexcelCellRangeAddressTab> selectByParams(Map<String, Object> params);

	@Delete("delete from excel_cell_range_address_tab")
	int deleteAll();

}