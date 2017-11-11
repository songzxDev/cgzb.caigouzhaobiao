package cn.szx.cgzb.model;

import java.util.Date;

public class TexcelCellTab {
    private Long id;

    private Integer excelCellRowIndex;

    private Integer excelCellColumnIndex;

    private String excelCellValue;

    private String ownedSheet;

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

    public Integer getExcelCellRowIndex() {
        return excelCellRowIndex;
    }

    public void setExcelCellRowIndex(Integer excelCellRowIndex) {
        this.excelCellRowIndex = excelCellRowIndex;
    }

    public Integer getExcelCellColumnIndex() {
        return excelCellColumnIndex;
    }

    public void setExcelCellColumnIndex(Integer excelCellColumnIndex) {
        this.excelCellColumnIndex = excelCellColumnIndex;
    }

    public String getExcelCellValue() {
        return excelCellValue;
    }

    public void setExcelCellValue(String excelCellValue) {
        this.excelCellValue = excelCellValue == null ? null : excelCellValue.trim();
    }

    public String getOwnedSheet() {
        return ownedSheet;
    }

    public void setOwnedSheet(String ownedSheet) {
        this.ownedSheet = ownedSheet == null ? null : ownedSheet.trim();
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