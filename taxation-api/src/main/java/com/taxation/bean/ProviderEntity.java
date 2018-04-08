package com.taxation.bean;

import java.io.Serializable;
import java.util.Date;

public class ProviderEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5838932849128946405L;

	private String providerId;

    private String providerAcount;

    private String providerPassword;

    private String providerPhone;

    private String providerName;

    private String providerIdNo;

    private String imageName;

    private String providerContext;

    private String providerScore;

    private String providerLevel;

    private String vocationalQualifications;

    private String vocationalYears;

    private String qualifications;

    private String jobTitle;

    private String serviceArea;

    private String professionalField;

    private String provideServiceType;

    private String providerType;

    private String whetherCompany;

    private String companyName;

    private String companyAddress;

    private String companyType;

    private String companyCode;

    private String companyImage;

    private String companyMobile;

    private String legalPersoon;

    private String legalPhone;

    private String legalIdNo;

    private String legalFrontalImage;

    private String legalOppositeImage;

    private String providerStatus;

    private String approvalStatus;

    private String approvalRemark;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Integer loginNum;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    public String getProviderAcount() {
        return providerAcount;
    }

    public void setProviderAcount(String providerAcount) {
        this.providerAcount = providerAcount == null ? null : providerAcount.trim();
    }

    public String getProviderPassword() {
        return providerPassword;
    }

    public void setProviderPassword(String providerPassword) {
        this.providerPassword = providerPassword == null ? null : providerPassword.trim();
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone == null ? null : providerPhone.trim();
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : providerName.trim();
    }

    public String getProviderIdNo() {
        return providerIdNo;
    }

    public void setProviderIdNo(String providerIdNo) {
        this.providerIdNo = providerIdNo == null ? null : providerIdNo.trim();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

    public String getProviderContext() {
        return providerContext;
    }

    public void setProviderContext(String providerContext) {
        this.providerContext = providerContext == null ? null : providerContext.trim();
    }

    public String getProviderScore() {
        return providerScore;
    }

    public void setProviderScore(String providerScore) {
        this.providerScore = providerScore == null ? null : providerScore.trim();
    }

    public String getProviderLevel() {
        return providerLevel;
    }

    public void setProviderLevel(String providerLevel) {
        this.providerLevel = providerLevel == null ? null : providerLevel.trim();
    }

    public String getVocationalQualifications() {
        return vocationalQualifications;
    }

    public void setVocationalQualifications(String vocationalQualifications) {
        this.vocationalQualifications = vocationalQualifications == null ? null : vocationalQualifications.trim();
    }

    public String getVocationalYears() {
        return vocationalYears;
    }

    public void setVocationalYears(String vocationalYears) {
        this.vocationalYears = vocationalYears == null ? null : vocationalYears.trim();
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications == null ? null : qualifications.trim();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? null : jobTitle.trim();
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea == null ? null : serviceArea.trim();
    }

    public String getProfessionalField() {
        return professionalField;
    }

    public void setProfessionalField(String professionalField) {
        this.professionalField = professionalField == null ? null : professionalField.trim();
    }

    public String getProvideServiceType() {
        return provideServiceType;
    }

    public void setProvideServiceType(String provideServiceType) {
        this.provideServiceType = provideServiceType == null ? null : provideServiceType.trim();
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType == null ? null : providerType.trim();
    }

    public String getWhetherCompany() {
        return whetherCompany;
    }

    public void setWhetherCompany(String whetherCompany) {
        this.whetherCompany = whetherCompany == null ? null : whetherCompany.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage == null ? null : companyImage.trim();
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile == null ? null : companyMobile.trim();
    }

    public String getLegalPersoon() {
        return legalPersoon;
    }

    public void setLegalPersoon(String legalPersoon) {
        this.legalPersoon = legalPersoon == null ? null : legalPersoon.trim();
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone == null ? null : legalPhone.trim();
    }

    public String getLegalIdNo() {
        return legalIdNo;
    }

    public void setLegalIdNo(String legalIdNo) {
        this.legalIdNo = legalIdNo == null ? null : legalIdNo.trim();
    }

    public String getLegalFrontalImage() {
        return legalFrontalImage;
    }

    public void setLegalFrontalImage(String legalFrontalImage) {
        this.legalFrontalImage = legalFrontalImage == null ? null : legalFrontalImage.trim();
    }

    public String getLegalOppositeImage() {
        return legalOppositeImage;
    }

    public void setLegalOppositeImage(String legalOppositeImage) {
        this.legalOppositeImage = legalOppositeImage == null ? null : legalOppositeImage.trim();
    }

    public String getProviderStatus() {
        return providerStatus;
    }

    public void setProviderStatus(String providerStatus) {
        this.providerStatus = providerStatus == null ? null : providerStatus.trim();
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus == null ? null : approvalStatus.trim();
    }

    public String getApprovalRemark() {
        return approvalRemark;
    }

    public void setApprovalRemark(String approvalRemark) {
        this.approvalRemark = approvalRemark == null ? null : approvalRemark.trim();
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }
}