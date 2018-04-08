package com.taxation.bean;

import java.io.Serializable;

public class PrividerScopeRuleEntity  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7367913677340975465L;

	private String ruleCode;

    private String ruleProportion;

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getRuleProportion() {
        return ruleProportion;
    }

    public void setRuleProportion(String ruleProportion) {
        this.ruleProportion = ruleProportion == null ? null : ruleProportion.trim();
    }
}