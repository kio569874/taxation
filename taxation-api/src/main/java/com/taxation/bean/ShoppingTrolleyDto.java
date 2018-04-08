package com.taxation.bean;

import java.io.Serializable;

/**
 * Created by Jerry on 2017-7-5.
 */
public class ShoppingTrolleyDto implements Serializable {
    private String id;
    private String context;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
