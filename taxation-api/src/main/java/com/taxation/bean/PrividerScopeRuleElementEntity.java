package com.taxation.bean;

import java.io.Serializable;

public class PrividerScopeRuleElementEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 573097771534610003L;

	private String ruleCode;
	
	private String elementCode;
	
    private String scopePoint;
    
    

    public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode == null ? null : ruleCode.trim();
	}

	public String getElementCode() {
		return elementCode;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode == null ? null : elementCode.trim();
	}

	public String getScopePoint() {
        return scopePoint;
    }

    public void setScopePoint(String scopePoint) {
        this.scopePoint = scopePoint == null ? null : scopePoint.trim();
    }
}