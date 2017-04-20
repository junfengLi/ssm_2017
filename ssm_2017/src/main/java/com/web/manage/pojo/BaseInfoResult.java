/*
* @ClassName:BaseInfo.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-04-15
*/
package com.web.manage.pojo;

import com.web.commons.pojo.BasePage;

public class BaseInfoResult  extends BasePage{
	private Long sendmenutime1;
    private Long interviewtime1;
    private Long finshnewstime1;
    private Long backtime1;
    private Long onlinetime1;
    
    private Long onlinetime2;
    private Long sendmenutime2;
    private Long interviewtime2;
    private Long finshnewstime2;
    private Long backtime2;
    
    private String ismark;
    
    private String speed;

	public Long getSendmenutime1() {
		return sendmenutime1;
	}

	public void setSendmenutime1(Long sendmenutime1) {
		this.sendmenutime1 = sendmenutime1;
	}

	public Long getInterviewtime1() {
		return interviewtime1;
	}

	public void setInterviewtime1(Long interviewtime1) {
		this.interviewtime1 = interviewtime1;
	}

	public Long getFinshnewstime1() {
		return finshnewstime1;
	}

	public void setFinshnewstime1(Long finshnewstime1) {
		this.finshnewstime1 = finshnewstime1;
	}

	public Long getBacktime1() {
		return backtime1;
	}

	public void setBacktime1(Long backtime1) {
		this.backtime1 = backtime1;
	}

	public Long getSendmenutime2() {
		return sendmenutime2;
	}

	public void setSendmenutime2(Long sendmenutime2) {
		this.sendmenutime2 = sendmenutime2;
	}

	public Long getInterviewtime2() {
		return interviewtime2;
	}

	public void setInterviewtime2(Long interviewtime2) {
		this.interviewtime2 = interviewtime2;
	}

	public Long getFinshnewstime2() {
		return finshnewstime2;
	}

	public void setFinshnewstime2(Long finshnewstime2) {
		this.finshnewstime2 = finshnewstime2;
	}

	public Long getBacktime2() {
		return backtime2;
	}

	public void setBacktime2(Long backtime2) {
		this.backtime2 = backtime2;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Long getOnlinetime1() {
		return onlinetime1;
	}

	public void setOnlinetime1(Long onlinetime1) {
		this.onlinetime1 = onlinetime1;
	}

	public Long getOnlinetime2() {
		return onlinetime2;
	}

	public void setOnlinetime2(Long onlinetime2) {
		this.onlinetime2 = onlinetime2;
	}

	public String getIsmark() {
		return ismark;
	}

	public void setIsmark(String ismark) {
		this.ismark = ismark;
	}
	
    
}