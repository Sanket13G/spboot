package com.cwms;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class EmailRequest {
	 @Id
	 @Column(name = "Mail_Trans_Id",nullable = false, length=10)
	 private String mailTransId;
	 
	 @Column(name = "Company_Id", nullable = false, columnDefinition = "varchar(6) default ''")
	 private String companyId;

	 @Column(name = "Branch_Id", nullable = false, columnDefinition = "varchar(6) default ''")
	 private String branchId;
	 
	 @Column(name = "from_email")
	 private String fromEmail;

	 @Column(name = "recipient_email")
	 private String to;
	 
	 @Column(name = "CC_Mail", nullable = false, columnDefinition = "text")
	 private String ccMail;

	 @Column(name = "Body_Mail", nullable = false, columnDefinition = "text")
	 private String bodyMail;


	 @Column(name = "attachment_path")
	 private String attachmentPath;

	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "File_Created_Date", nullable = false)
	 private Date fileCreatedDate;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "Sent_Date", nullable = false)
	 private Date sentDate;
	 
	 @Column
	 private String subject;
	// @Column
	// private String message;
	 
	 @Column(name = "Mail_Status", nullable = false, columnDefinition = "varchar(20) default ''")
	    private String mailStatus;
	 
	 @Column(name = "Sent_Status") 
	 private String status;

	 public EmailRequest() {
	  super();
	 }

	 public EmailRequest(String mailTransId, String companyId, String branchId, String fromEmail, String to,
	   String ccMail, String bodyMail, String attachmentPath, Date fileCreatedDate, Date sentDate, String subject,
	   String mailStatus, String status) {
	  super();
	  this.mailTransId = mailTransId;
	  this.companyId = companyId;
	  this.branchId = branchId;
	  this.fromEmail = fromEmail;
	  this.to = to;
	  this.ccMail = ccMail;
	  this.bodyMail = bodyMail;
	  this.attachmentPath = attachmentPath;
	  this.fileCreatedDate = fileCreatedDate;
	  this.sentDate = sentDate;
	  this.subject = subject;
	  this.mailStatus = mailStatus;
	  this.status = status;
	 }

	 public String getMailTransId() {
	  return mailTransId;
	 }

	 public void setMailTransId(String mailTransId) {
	  this.mailTransId = mailTransId;
	 }

	 public String getCompanyId() {
	  return companyId;
	 }

	 public void setCompanyId(String companyId) {
	  this.companyId = companyId;
	 }

	 public String getBranchId() {
	  return branchId;
	 }

	 public void setBranchId(String branchId) {
	  this.branchId = branchId;
	 }

	 public String getFromEmail() {
	  return fromEmail;
	 }

	 public void setFromEmail(String fromEmail) {
	  this.fromEmail = fromEmail;
	 }

	 public String getTo() {
	  return to;
	 }

	 public void setTo(String to) {
	  this.to = to;
	 }

	 public String getCcMail() {
	  return ccMail;
	 }

	 public void setCcMail(String ccMail) {
	  this.ccMail = ccMail;
	 }

	 public String getBodyMail() {
	  return bodyMail;
	 }

	 public void setBodyMail(String bodyMail) {
	  this.bodyMail = bodyMail;
	 }

	 public String getAttachmentPath() {
	  return attachmentPath;
	 }

	 public void setAttachmentPath(String attachmentPath) {
	  this.attachmentPath = attachmentPath;
	 }

	 public Date getFileCreatedDate() {
	  return fileCreatedDate;
	 }

	 public void setFileCreatedDate(Date fileCreatedDate) {
	  this.fileCreatedDate = fileCreatedDate;
	 }

	 public Date getSentDate() {
	  return sentDate;
	 }

	 public void setSentDate(Date sentDate) {
	  this.sentDate = sentDate;
	 }

	 public String getSubject() {
	  return subject;
	 }

	 public void setSubject(String subject) {
	  this.subject = subject;
	 }

	 public String getMailStatus() {
	  return mailStatus;
	 }

	 public void setMailStatus(String mailStatus) {
	  this.mailStatus = mailStatus;
	 }

	 public String getStatus() {
	  return status;
	 }

	 public void setStatus(String status) {
	  this.status = status;
	 }

}
