package com.example.wanandroid.bean;

import io.realm.RealmObject;

/**
 * @author TodoBean
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public class TodoBean extends RealmObject {

    /**
     * completeDate : null
     * completeDateStr :
     * content : 详情（选填）
     * date : 1565452800000
     * dateStr : 2019-08-11
     * id : 15698
     * priority : 0
     * status : 0     0未完成，1已完成
     * title : 待做1
     * type : 0      0没有分类，其他分类大于0
     * userId : 3710
     */

    private long completeDate;
    private String completeDateStr;
    private String content;
    private long date;
    private String dateStr;
    private int id;
    private int priority;
    private int status;
    private String title;
    private int type;
    private int userId;

    public long getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(long completeDate) {
        this.completeDate = completeDate;
    }

    public String getCompleteDateStr() {
        return completeDateStr;
    }

    public void setCompleteDateStr(String completeDateStr) {
        this.completeDateStr = completeDateStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
