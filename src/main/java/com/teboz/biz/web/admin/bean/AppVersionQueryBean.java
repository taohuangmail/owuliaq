/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: AppVersionQueryBean.java
 * Author:   long.yu
 * Date:     2015年12月23日 上午11:42:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.web.admin.bean;

import com.teboz.biz.page.QueryBean;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AppVersionQueryBean extends QueryBean {

    private Integer appVersionCode;

    private Boolean forced;


    /**
     * @return the appVersionCode
     */
    public Integer getAppVersionCode() {
        return appVersionCode;
    }

    /**
     * @param appVersionCode
     *            the appVersionCode to set
     */
    public void setAppVersionCode(Integer appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    /**
     * @return the forced
     */
    public Boolean getForced() {
        return forced;
    }

    /**
     * @param forced
     *            the forced to set
     */
    public void setForced(Boolean forced) {
        this.forced = forced;
    }

}
