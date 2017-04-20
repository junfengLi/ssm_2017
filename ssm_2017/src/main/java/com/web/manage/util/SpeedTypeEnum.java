package com.web.manage.util;

import java.util.ArrayList;
import java.util.List;

import com.web.commons.jqgrid.UINode;

public enum SpeedTypeEnum {

	FSCFTG("F", "发送采访提纲"),
	CF("FC", "采访"),
	CG("FCC", "成稿"),
	FSFK("FCCF", "发送反馈"),
	DF("FCCFD", "打分");
	private String key; 
	private String desc;
	SpeedTypeEnum(String key, String desc){
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
		for (SpeedTypeEnum type : SpeedTypeEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}
	
	
	public static List<UINode> getNodes(){
		List<UINode> nodes=new ArrayList<UINode>();
		for(SpeedTypeEnum type:SpeedTypeEnum.values()){
			nodes.add(new UINode(type.getKey(),type.getDesc()));
		}
		return nodes;
	}
}
