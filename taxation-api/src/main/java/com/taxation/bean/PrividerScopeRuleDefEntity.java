package com.taxation.bean;

import java.io.Serializable;

public class PrividerScopeRuleDefEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7043255667845788434L;

	private String ruleCode;

    private String ruleName;

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }
}