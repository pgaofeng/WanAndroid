package com.example.wanandroid.bean;

/**
 * @author gaofengpeng
 * @date 2019/8/12
 * @description :
 */
public class HeaderBean {

    private String name;
    private String link;
    private boolean isHeader;
    private String headerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}
