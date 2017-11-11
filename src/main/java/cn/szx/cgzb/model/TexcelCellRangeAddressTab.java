package cn.szx.cgzb.model;

import java.util.Date;

public class TexcelCellRangeAddressTab {
    private Integer id;

    private Integer firstRow;

    private Integer lastRow;

    private Integer firstCol;

    private Integer lastCol;

    private String ownedSheet;

    private String attribute1;

    private String attribute2;

    private String usableStatus;

    private Date createDatetime;

    private Date updateDatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
        this.firstRow = firstRow;
    }

    public Integer getLastRow() {
        return lastRow;
    }

    public void setLastRow(Integer lastRow) {
        this.lastRow = lastRow;
    }

    public Integer getFirstCol() {
        return firstCol;
    }

    public void setFirstCol(Integer firstCol) {
        this.firstCol = firstCol;
    }

    public Integer getLastCol() {
        return lastCol;
    }

    public void setLastCol(Integer lastCol) {
        this.lastCol = lastCol;
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