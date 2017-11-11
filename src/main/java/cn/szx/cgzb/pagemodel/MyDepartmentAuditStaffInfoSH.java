package cn.szx.cgzb.pagemodel;

import java.util.Date;

public class MyDepartmentAuditStaffInfoSH {
	/**
	 * 2016年1月21日临时拟定规则：
	 * 《交接文档（合同-专业-人员）-XXXX年XX月XX日.xlsx》文档中不考虑合同类型和专业类别，
	 * 文档中每个SHEET页签中的每一行数据均以【发起部门】列中的数据为定位基准，进行更新与其相同行的其他列的数据信息，
	 * 其他事项于日后补充。
	 */
	private Long id;
	/** EXCEL文件中的SHEET名称 */
	private String bmOwnedSheet;
	/** 发起部门的列下标 */
	private Integer bmExcelCellColumnIndex;
	/** 发起部门的行下标 */
	private Integer bmExcelCellRowIndex;
	/** 发起部门的部门名称 */
	private String bmExcelCellValue;
	/** 直属经理姓名 */
	private String ryExcelCellValueSj;
	/** 直属经理登录帐号 */
	private String ryExcelCellValueSjl;
	/** 区县会计姓名 */
	private String ryExcelCellValueKj;
	/** 区县会计登录帐号 */
	private String ryExcelCellValueKjl;
	/** 部门副总经理姓名 */
	private String ryExcelCellValueFz;
	/** 部门副总经理登录帐号 */
	private String ryExcelCellValueFzl;
	/** 部门正经理姓名 */
	private String ryExcelCellValueZz;
	/** 部门正经理登录帐号 */
	private String ryExcelCellValueZzl;
	/** 该条数据是否已被处理 '001' 未处理，'002' 处理中，'003' 处理完成 */
	private String attribute1;
	/** */
	private String attribute2;
	/** 是否可用标志 '1' 是可用 '0' 是不可用 */
	private String usableStatus;
	/** */
	private Date createDatetime;
	/** */
	private Date updateDatetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBmOwnedSheet() {
		return bmOwnedSheet;
	}

	public void setBmOwnedSheet(String bmOwnedSheet) {
		this.bmOwnedSheet = bmOwnedSheet == null ? null : bmOwnedSheet.trim();
	}

	public Integer getBmExcelCellColumnIndex() {
		return bmExcelCellColumnIndex;
	}

	public void setBmExcelCellColumnIndex(Integer bmExcelCellColumnIndex) {
		this.bmExcelCellColumnIndex = bmExcelCellColumnIndex;
	}

	public Integer getBmExcelCellRowIndex() {
		return bmExcelCellRowIndex;
	}

	public void setBmExcelCellRowIndex(Integer bmExcelCellRowIndex) {
		this.bmExcelCellRowIndex = bmExcelCellRowIndex;
	}

	public String getBmExcelCellValue() {
		return bmExcelCellValue;
	}

	public void setBmExcelCellValue(String bmExcelCellValue) {
		this.bmExcelCellValue = bmExcelCellValue == null ? null : bmExcelCellValue.trim();
	}

	public String getRyExcelCellValueSj() {
		return ryExcelCellValueSj;
	}

	public void setRyExcelCellValueSj(String ryExcelCellValueSj) {
		this.ryExcelCellValueSj = ryExcelCellValueSj == null ? null : ryExcelCellValueSj.trim();
	}

	public String getRyExcelCellValueSjl() {
		return ryExcelCellValueSjl;
	}

	public void setRyExcelCellValueSjl(String ryExcelCellValueSjl) {
		this.ryExcelCellValueSjl = ryExcelCellValueSjl == null ? null : ryExcelCellValueSjl.trim();
	}

	public String getRyExcelCellValueKj() {
		return ryExcelCellValueKj;
	}

	public void setRyExcelCellValueKj(String ryExcelCellValueKj) {
		this.ryExcelCellValueKj = ryExcelCellValueKj == null ? null : ryExcelCellValueKj.trim();
	}

	public String getRyExcelCellValueKjl() {
		return ryExcelCellValueKjl;
	}

	public void setRyExcelCellValueKjl(String ryExcelCellValueKjl) {
		this.ryExcelCellValueKjl = ryExcelCellValueKjl == null ? null : ryExcelCellValueKjl.trim();
	}

	public String getRyExcelCellValueFz() {
		return ryExcelCellValueFz;
	}

	public void setRyExcelCellValueFz(String ryExcelCellValueFz) {
		this.ryExcelCellValueFz = ryExcelCellValueFz == null ? null : ryExcelCellValueFz.trim();
	}

	public String getRyExcelCellValueFzl() {
		return ryExcelCellValueFzl;
	}

	public void setRyExcelCellValueFzl(String ryExcelCellValueFzl) {
		this.ryExcelCellValueFzl = ryExcelCellValueFzl == null ? null : ryExcelCellValueFzl.trim();
	}

	public String getRyExcelCellValueZz() {
		return ryExcelCellValueZz;
	}

	public void setRyExcelCellValueZz(String ryExcelCellValueZz) {
		this.ryExcelCellValueZz = ryExcelCellValueZz == null ? null : ryExcelCellValueZz.trim();
	}

	public String getRyExcelCellValueZzl() {
		return ryExcelCellValueZzl;
	}

	public void setRyExcelCellValueZzl(String ryExcelCellValueZzl) {
		this.ryExcelCellValueZzl = ryExcelCellValueZzl == null ? null : ryExcelCellValueZzl.trim();
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1 == null ? null : attribute1.trim();
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2 == null ? null : attribute2.trim();
	}

	public String getUsableStatus() {
		return usableStatus;
	}

	public void setUsableStatus(String usableStatus) {
		this.usableStatus = usableStatus == null ? null : usableStatus.trim();
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}