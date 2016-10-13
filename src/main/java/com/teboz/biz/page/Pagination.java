package com.teboz.biz.page;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author 于龙
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Pagination implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7554108855455655618L;

    /**
     * 默认每页显示条数
     */
    public static final int PAGESIZE = 10;

    /**
     * 每页显示条数
     */
    private int pageSize = 10;

    /**
     * 总行数
     */
    private int totalRows = -1;

    /**
     * 当前页
     */
    private int currentPage = 1;

    public Pagination() {
        this(PAGESIZE, 1);
    }

    /**
     * 构造方法
     * 
     * @param pagesize 每页显示条数
     * @param currentPage 当前页
     */
    public Pagination(int pagesize, int currentPage) {
        this.pageSize = pagesize;
        this.currentPage = currentPage;
    }

    /**
     * 获取当前页第一行的索引
     * 
     * @return 当前页第一行的索引
     */
    public int getFirstRowIndex() {
        return (getCurrentPage() - 1) * getPageSize();
    }

    /**
     * 获取每页显示条数
     * 
     * @return 每页显示条数
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 获取总条数
     * 
     * @return 总条数
     */
    public int getTotalRows() {
        return this.totalRows;
    }

    /**
     * 获取当前页
     * 
     * @return 当前页
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置总行数
     * 
     * @param totalRows 总行数
     * 
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        if (this.totalRows != -1 && getCurrentPage() > getTotalPage()) {
            setCurrentPage(1);
        }
    }

    /**
     * 设置每页显示条数
     * 
     * @param pagesize 每页显示条数
     * 
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置当前页
     * 
     * @param currentPage 当前页
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获取总页数
     * 
     * @return 总页数
     */
    public int getTotalPage() {
        if (pageSize == 0) {
            return 0;
        } else {
            if (getTotalRows() > 0) {
                return (totalRows / pageSize) + (totalRows % pageSize == 0 ? 0 : 1);
            } else {
                return 0;
            }
        }
    }
    
    public int getTotalRecord(){
        return this.totalRows;
    }

}
