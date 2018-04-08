package com.taxation.model;

import java.io.Serializable;

import com.taxation.entity.CommonFormParam;

public class CityArea extends CommonFormParam implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1100173387943703314L;

	private String pid;

    private String pcode;

    private String pname;

    private String pshortname;

    private String plevel;

    private String parentid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPshortname() {
        return pshortname;
    }

    public void setPshortname(String pshortname) {
        this.pshortname = pshortname == null ? null : pshortname.trim();
    }

    public String getPlevel() {
        return plevel;
    }

    public void setPlevel(String plevel) {
        this.plevel = plevel == null ? null : plevel.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }
}