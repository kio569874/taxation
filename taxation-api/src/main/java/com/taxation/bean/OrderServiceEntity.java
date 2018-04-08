package com.taxation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderServiceEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5484802184416480971L;

	private String orderId;

	private String serviceId;

	private BigDecimal price;

	private String context;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId == null ? null : serviceId.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context == null ? null : context.trim();
	}
}