package cn.szx.cgzb.model;

import java.util.Date;

public class TworkflowPersonRelation {
    private Long id;

    private String workflowId;

    private String workflowName;

    private String workflowStepId;

    private String workflowStepName;

    private String condition;

    private String filterType;

    private String partyCode;

    private String partyName;

    private String sysPartyCode;

    private String usableStatus;

    private Date createDate;

    private String createIp;

    private String createUserId;

    private Date modifyDate;

    private String modifyIp;

    private String modifyUserId;

    private String orderCode;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private String attribute5;

    private String sourceStepId;

    private String sourceStepName;

    private String conditionN;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName == null ? null : workflowName.trim();
    }

    public String getWorkflowStepId() {
        return workflowStepId;
    }

    public void setWorkflowStepId(String workflowStepId) {
        this.workflowStepId = workflowStepId == null ? null : workflowStepId.trim();
    }

    public String getWorkflowStepName() {
        return workflowStepName;
    }

    public void setWorkflowStepName(String workflowStepName) {
        this.workflowStepName = workflowStepName == null ? null : workflowStepName.trim();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType == null ? null : filterType.trim();
    }

    public String getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode == null ? null : partyCode.trim();
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName == null ? null : partyName.trim();
    }

    public String getSysPartyCode() {
        return sysPartyCode;
    }

    public void setSysPartyCode(String sysPartyCode) {
        this.sysPartyCode = sysPartyCode == null ? null : sysPartyCode.trim();
    }

    public String getUsableStatus() {
        return usableStatus;
    }

    public void setUsableStatus(String usableStatus) {
        this.usableStatus = usableStatus == null ? null : usableStatus.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyIp() {
        return modifyIp;
    }

    public void setModifyIp(String modifyIp) {
        this.modifyIp = modifyIp == null ? null : modifyIp.trim();
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
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

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3 == null ? null : attribute3.trim();
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4 == null ? null : attribute4.trim();
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5 == null ? null : attribute5.trim();
    }

    public String getSourceStepId() {
        return sourceStepId;
    }

    public void setSourceStepId(String sourceStepId) {
        this.sourceStepId = sourceStepId == null ? null : sourceStepId.trim();
    }

    public String getSourceStepName() {
        return sourceStepName;
    }

    public void setSourceStepName(String sourceStepName) {
        this.sourceStepName = sourceStepName == null ? null : sourceStepName.trim();
    }

    public String getConditionN() {
        return conditionN;
    }

    public void setConditionN(String conditionN) {
        this.conditionN = conditionN == null ? null : conditionN.trim();
    }
}