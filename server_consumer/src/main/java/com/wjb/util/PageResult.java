package com.wjb.util;

import java.util.List;

/**
 * Created by wjb on 2017/6/13.
 */
public class PageResult {
    private String[] obj;
    private List<Object> list;

    private StringBuffer buffer;

    //总页数
    private int totalPageCount=1;
    //页面大小，即每页显示记录数
    private int pageSize=0;
    //记录总数
    private int recordCount=0;
    //当前页号
    private int currPageNo=1;

    public int getCurrPageNo() {
        if(totalPageCount==0)
            return 0;
        return currPageNo;
    }
    public void setCurrPageNo(int currPageNo) {
        if(this.currPageNo>0)
            this.currPageNo = currPageNo;
    }
    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        if(pageSize>0)
            this.pageSize = pageSize;
    }
    public int getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(int recordCount) {
        if(recordCount>0){
            this.recordCount = recordCount;
            this.setTotalPageCountByRs();
        }
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(StringBuffer buffer) {
        this.buffer = buffer;
    }

    public String[] getObj() {
        return obj;
    }

    public void setObj(String[] obj) {
        this.obj = obj;
    }

    //设置总页数
    private void setTotalPageCountByRs(){
        if(this.recordCount%this.pageSize==0)
            this.totalPageCount=this.recordCount/this.pageSize;
        else if(this.recordCount%this.pageSize>0)
            this.totalPageCount=this.recordCount/this.pageSize+1;
        else
            this.totalPageCount=0;
    }



}
