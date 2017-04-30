/*
* @ClassName:Times.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-04-30
*/
package com.web.manage.pojo;

import com.web.commons.pojo.BasePage;

public class Times extends BasePage {
    private String id;

    private String userid;

    private Long date;
    private Long date1;
    private Long date2;

    private Integer times;

    private String note;

    private Long createtime;

    private Long updatetime;

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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDate1() {
		return date1;
	}

	public void setDate1(Long date1) {
		this.date1 = date1;
	}

	public Long getDate2() {
		return date2;
	}

	public void setDate2(Long date2) {
		this.date2 = date2;
	}

	public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
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
}