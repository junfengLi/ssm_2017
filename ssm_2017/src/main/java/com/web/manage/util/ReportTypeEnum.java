package com.web.manage.util;

import java.util.ArrayList;
import java.util.List;

import com.web.commons.jqgrid.UINode;

public enum ReportTypeEnum {

	SENDMENU("sendmenu", "发送采访提纲多少份"),
	INTERVIEW("interview", "采访多少人"),
	FINISH("finish", "撰写稿件多少篇"),
	BACK("back", "发送反馈多少份"),
	ONLINE("online", "上线稿件多少份"),
	SCORE("score", "打分多少人"),
	ASK("ask", "联系客户多少人次"),
	FINISHSEND("finishsend", "稿件撰写完成并发送给客户，等待确认的人"),
	SENDINTERVIEW("sendinterview", "发送采访提纲并采访完成的人"),
	ONLYSEND("onlysend", "发送采访提纲的人");
	private String key; 
	private String desc;
	ReportTypeEnum(String key, String desc){
		this.key = key;
		this.desc = desc;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static String getDescByKey(String Key) {
		for (ReportTypeEnum type : ReportTypeEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}
	
	
	public static List<UINode> getNodes(){
		List<UINode> nodes=new ArrayList<UINode>();
		for(ReportTypeEnum type:ReportTypeEnum.values()){
			nodes.add(new UINode(type.getKey(),type.getDesc()));
		}
		return nodes;
	}
}
