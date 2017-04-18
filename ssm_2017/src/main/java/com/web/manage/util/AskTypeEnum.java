package com.web.manage.util;

public enum AskTypeEnum {

	Mobile("mobile", "电话采访"),
	Email("email", "邮件采访");
	private String key; 
	private String desc;
	AskTypeEnum(String key, String desc){
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
		for (AskTypeEnum type : AskTypeEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}
	
}
