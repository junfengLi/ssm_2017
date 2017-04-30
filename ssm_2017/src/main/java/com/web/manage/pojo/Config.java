/*
* @ClassName:Config.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-04-29
*/
package com.web.manage.pojo;

import com.web.commons.pojo.BasePage;

public class Config extends BasePage{
    private String id;

    private String userid;

    private String dayconfig;

    private String weekconfig;

    private String monthconfig;

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

    public String getDayconfig() {
        return dayconfig;
    }

    public void setDayconfig(String dayconfig) {
        this.dayconfig = dayconfig;
    }

    public String getWeekconfig() {
        return weekconfig;
    }

    public void setWeekconfig(String weekconfig) {
        this.weekconfig = weekconfig;
    }

    public String getMonthconfig() {
        return monthconfig;
    }

    public void setMonthconfig(String monthconfig) {
        this.monthconfig = monthconfig;
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