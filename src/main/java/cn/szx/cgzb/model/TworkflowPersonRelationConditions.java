package cn.szx.cgzb.model;

import java.util.Date;

public class TworkflowPersonRelationConditions {
    private Integer workflowPersonRelationConditionsId;

    private Long workflowPersonRelationId;

    private String conId;

    private String logicType;

    private String conValue;

    private String usableStatus;

    private Date createDatetime;

    private String attribute1;

    private String attribute2;

    public Integer getWorkflowPersonRelationConditionsId() {
        return workflowPersonRelationConditionsId;
    }

    public void setWorkflowPersonRelationConditionsId(Integer workflowPersonRelationConditionsId) {
        this.workflowPersonRelationConditionsId = workflowPersonRelationConditionsId;
    }

    public Long getWorkflowPersonRelationId() {
        return workflowPersonRelationId;
    }

    public void setWorkflowPersonRelationId(Long workflowPersonRelationId) {
        this.workflowPersonRelationId = workflowPersonRelationId;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId == null ? null : conId.trim();
    }

    public String getLogicType() {
        return logicType;
    }

    public void setLogicType(String logicType) {
        this.logicType = logicType == null ? null : logicType.trim();
    }

    public String getConValue() {
        return conValue;
    }

    public void setConValue(String conValue) {
        this.conValue = conValue == null ? null : conValue.trim();
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
}