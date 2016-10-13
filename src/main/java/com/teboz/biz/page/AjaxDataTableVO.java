package com.teboz.biz.page;

import java.util.List;

/**
 * 前台datatable Ajax请求返回公共类 <br>
 *
 * @param <T>
 *            list数据类型
 * @author tao.huang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AjaxDataTableVO<T> {

    /** 返回数据 */
    private List<T> data;
    /** 请求标识 */
    private int sEcho;
    /** 数据库中总共有多少条 */
    private int recordsTotal;
    /** 数据库中查询过滤后有多少条记录,和iTotalRecords设置为相同 */
    private int recordsFiltered;

    public AjaxDataTableVO() {
    }

    public AjaxDataTableVO(int sEcho) {
        this.sEcho = sEcho;
    }

    public AjaxDataTableVO(int sEcho, PaginationResult<List<T>> prs) {
        this.sEcho = sEcho;
        this.data = prs.getR();// 设置列表数据
        this.recordsFiltered = prs.getPagination().getTotalRows();// 设置总记录条数
        this.recordsTotal = prs.getPagination().getTotalRows();// 设置总记录条数
    }

    /**
     * @return the data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    /**
     * @return the recordsTotal
     */
    public int getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * @param recordsTotal
     *            the recordsTotal to set
     */
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    /**
     * @return the recordsFiltered
     */
    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    /**
     * @param recordsFiltered
     *            the recordsFiltered to set
     */
    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

}
