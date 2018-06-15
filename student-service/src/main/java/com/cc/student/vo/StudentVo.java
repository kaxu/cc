package com.cc.student.vo;

import java.util.Date;

/**
 * Created by aaron on 2018/6/15.
 */
public class StudentVo {

    private Long id;

    private String name;

    private Date createTime;

    private int version;

    private Long classInfoId;

    private String className;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(Long classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
