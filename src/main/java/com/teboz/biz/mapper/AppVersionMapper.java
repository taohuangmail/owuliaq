/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: AppVersionMapper.java
 * Author:   long.yu
 * Date:     2015年12月23日 上午11:35:31
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.mapper;

import java.util.List;

import com.autostreets.framework.common.dal.GenericMapper;
import com.teboz.biz.model.AppVersion;
import com.teboz.biz.model.AppVersionExample;
import com.teboz.biz.web.admin.bean.AppVersionQueryBean;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AppVersionMapper extends GenericMapper<AppVersion, AppVersionExample, Integer> {

    /**
     * 分页查询
     *
     * @param queryBean
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    List<AppVersion> getListByPage(AppVersionQueryBean queryBean);
}
