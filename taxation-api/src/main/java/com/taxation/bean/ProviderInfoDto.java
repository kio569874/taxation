package com.taxation.bean;

import java.io.Serializable;
import java.util.Date;

public class ProviderInfoDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5838932849128946405L;

	private String providerId;

    private String providerName;

    private String imageName;

    private String providerContext;

    private String providerScore;

    private String providerLevel;

    private String serviceId;

    private String orderAmount;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getProviderContext() {
        return providerContext;
    }

    public void setProviderContext(String providerContext) {
        this.providerContext = providerContext;
    }

    public String getProviderScore() {
        return providerScore;
    }

    public void setProviderScore(String providerScore) {
        this.providerScore = providerScore;
    }

    public String getProviderLevel() {
        return providerLevel;
    }

    public void setProviderLevel(String providerLevel) {
        this.providerLevel = providerLevel;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}