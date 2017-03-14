/*
* @ClassName:Role.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-03-10
*/
package com.web.manage.pojo;

public class Role {
    /**  */
    private String id;

    /**  */
    private String name;

    /**  */
    private String code;

    /**  */
    private Long createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}