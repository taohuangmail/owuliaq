/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: AppVersionService.java
 * Author:   long.yu
 * Date:     2015年12月23日 上午11:36:27
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.service;

import java.util.List;

import com.autostreets.framework.common.dal.GenericService;
import com.teboz.biz.model.AppVersion;
import com.teboz.biz.model.AppVersionExample;
import com.teboz.biz.web.admin.bean.AppVersionQueryBean;

/**
 * 版本号服务
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AppVersionService extends GenericService<AppVersion, AppVersionExample, Integer> {

    /**
     * 分页查询
     *
     * @param queryBean
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    List<AppVersion> getListByPage(AppVersionQueryBean queryBean);

    /**
     * 加载最新的版本号
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    AppVersion loadLastAppVersion();
}
