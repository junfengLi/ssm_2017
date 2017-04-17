/*
* @ClassName:BaseInfo.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-04-15
*/
package com.web.manage.pojo;

import com.web.commons.pojo.BasePage;

public class BaseInfo  extends BasePage{
    private String id;

    private String userid;

    private String name;

    private String mobile;

    private String mobile2;

    private String email;

    private String email2;

    private String projectname;

    private String projecthref;

    private String asker;

    private Long servicetime;

    private String note;

    private Long createtime;

    private Long updatetime;
    
    private String seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjecthref() {
        return projecthref;
    }

    public void setProjecthref(String projecthref) {
        this.projecthref = projecthref;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public Long getServicetime() {
        return servicetime;
    }

    public void setServicetime(Long servicetime) {
        this.servicetime = servicetime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
}