package com.taxation.bean;

import java.io.Serializable;

public class ServiceDefEntity  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8793477293112871761L;

	private String serviceCode;

    private String serviceName;

    private String serviceDesc;

    private String minScope;

    private String maxScope;

    private String serviceType;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc == null ? null : serviceDesc.trim();
    }

    public String getMinScope() {
        return minScope;
    }

    public void setMinScope(String minScope) {
        this.minScope = minScope == null ? null : minScope.trim();
    }

    public String getMaxScope() {
        return maxScope;
    }

    public void setMaxScope(String maxScope) {
        this.maxScope = maxScope == null ? null : maxScope.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }
}