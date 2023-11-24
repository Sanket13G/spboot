
package com.cwms.entities;

public class UpdateExternalUserRights {
	private String userId;
	private String processId;
	private String approve;
	private String update;
	private String read;
	private String deleteRight;
	private String create;
	public UpdateExternalUserRights(String userId, String processId, String approve, String update, String read,
			String deleteRight, String create) {
		super();
		this.userId = userId;
		this.processId = processId;
		this.approve = approve;
		this.update = update;
		this.read = read;
		this.deleteRight = deleteRight;
		this.create = create;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getDeleteRight() {
		return deleteRight;
	}
	public void setDeleteRight(String deleteRight) {
		this.deleteRight = deleteRight;
	}
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	@Override
	public String toString() {
		return "UpdateUserRights [userId=" + userId + ", processId=" + processId + ", approve=" + approve + ", update="
				+ update + ", read=" + read + ", deleteRight=" + deleteRight + ", create=" + create + "]";
	}
	
}
