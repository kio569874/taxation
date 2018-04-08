package com.taxation.bean;

import java.io.Serializable;

/**
 * Created by Jerry on 2017-6-26.
 */
public class ProviderDto implements Serializable {
    private String providerId;
    private String providerContext;
    private String imageName;
    private String totalPrice;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderContext() {
        return providerContext;
    }

    public void setProviderContext(String providerContext) {
        this.providerContext = providerContext;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
