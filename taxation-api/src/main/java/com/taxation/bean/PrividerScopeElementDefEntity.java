package com.taxation.bean;

import java.io.Serializable;

public class PrividerScopeElementDefEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2081932753148909964L;

	private String elementCode;

    private String elementName;

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode == null ? null : elementCode.trim();
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName == null ? null : elementName.trim();
    }
}