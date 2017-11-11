package cn.szx.cgzb.model;

import java.util.Date;

public class TdepartmentAuditStaffInfoSH {
    private Long id;

    private String bmOwnedSheet;

    private Integer bmExcelCellColumnIndex;

    private Integer bmExcelCellRowIndex;

    private String bmExcelCellValue;

    private String ryExcelCellValueSj;

    private String ryExcelCellValueSjl;

    private String ryExcelCellValueKj;

    private String ryExcelCellValueKjl;

    private String ryExcelCellValueFz;

    private String ryExcelCellValueFzl;

    private String ryExcelCellValueZz;

    private String ryExcelCellValueZzl;

    private String attribute1;

    private String attribute2;

    private String usableStatus;

    private Date createDatetime;

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