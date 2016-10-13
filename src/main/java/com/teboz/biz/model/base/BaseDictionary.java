package com.teboz.biz.model.base;

import java.io.Serializable;
import java.util.Date;

public class BaseDictionary implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5541691320022736500L;

	private Integer id;

    private boolean deleteFlag;

    private Integer createUserId;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;

    private String groupId;

    private String groupName;

    private Integer diKey;

    private String diValue;

    private String diExplain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getDiKey() {
        return diKey;
    }

    public void setDiKey(Integer diKey) {
        this.diKey = diKey;
    }

    public String getDiValue() {
        return diValue;
    }

    public void setDiValue(String diValue) {
        this.diValue = diValue == null ? null : diValue.trim();
    }

    public String getDiExplain() {
        return diExplain;
    }

    public void setDiExplain(String diExplain) {
        this.diExplain = diExplain == null ? null : diExplain.trim();
    }
}