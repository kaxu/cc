package com.cc.student.events.models;

public class ClassInfoChangeModel {
    private String type;
    private String action;
    private Long classInfoId;
    private String correlationId;


    public ClassInfoChangeModel(String type, String action, Long classInfoId, String correlationId) {
        super();
        this.type   = type;
        this.action = action;
        this.classInfoId = classInfoId;
        this.correlationId = correlationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public Long getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(Long classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


}