package com.teboz.biz.page;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author 于龙
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class QueryBean {

    /** 请求标识 */
    private int sEcho;
    /** 单排序列的下标 */
    private int iSortCol_0;
    /** 排序序列数目 */
    private int iSortingCols;
    /** 单排序的方法 */
    private String sSortDir_0;
    /** 排序 */
    private String orderby;
    /** 每页开始数标(old) */
    private int iDisplayStart;
    /** 每页显示条数(old) */
    private int iDisplayLength;
    /** 每页开始数标 */
    private int start;
    /** 每页显示条数 */
    private int length;
    /** 当前请求页 */
    private int currentPage;
    
    private String orderByClause;


    /**
     * 每页显示条数
     */
    private int pageSize = 10;

    /** pagination */
    private Pagination pagination = new Pagination();

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getiSortCol_0() {
        return iSortCol_0;
    }

    public void setiSortCol_0(int iSortCol_0) {
        this.iSortCol_0 = iSortCol_0;
    }

    public String getsSortDir_0() {
        return sSortDir_0;
    }

    public void setsSortDir_0(String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }

    /**
     * 
     * 功能描述: <br>
     * 得到当前的请求的页码
     * 
     * @return 返回页码数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int getCurrentPage() {
        if (this.getLength() >= 1 && this.getStart() >= 0) {
            return (this.getStart() / this.getLength()) + 1;
        } else if (this.getiDisplayLength() >= 1 && this.getiDisplayStart() >= 0) {
            return (this.getiDisplayStart() / this.getiDisplayLength()) + 1;
        } else {
            currentPage = 1;
            return currentPage;
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getiSortingCols() {
        return iSortingCols;
    }

    public void setiSortingCols(int iSortingCols) {
        this.iSortingCols = iSortingCols;
    }

    public Pagination getPagination() {
        if (length > 0) {
            pagination.setPageSize(length);
        } else if (iDisplayLength > 0) {
            pagination.setPageSize(getiDisplayLength());
        } else {
            pagination.setPageSize(pageSize);
        }

        pagination.setCurrentPage(getCurrentPage());
        return pagination;
    }

    /**
     * @param pagination
     *            the pagination to set
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

}
