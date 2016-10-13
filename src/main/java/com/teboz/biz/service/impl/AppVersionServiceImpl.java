/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: AppVersionServiceImpl.java
 * Author:   long.yu
 * Date:     2015年12月23日 上午11:37:39
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autostreets.framework.common.dal.GenericMapper;
import com.autostreets.framework.common.dal.GenericServiceImpl;
import com.teboz.biz.mapper.AppVersionMapper;
import com.teboz.biz.model.AppVersion;
import com.teboz.biz.model.AppVersionExample;
import com.teboz.biz.service.AppVersionService;
import com.teboz.biz.web.admin.bean.AppVersionQueryBean;

/**
 * 版本号服务实现
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class AppVersionServiceImpl extends GenericServiceImpl<AppVersion, AppVersionExample, Integer> implements
        AppVersionService {

    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    protected GenericMapper<AppVersion, AppVersionExample, Integer> getGenericMapper() {
        return (GenericMapper<AppVersion, AppVersionExample, Integer>) appVersionMapper;
    }

    @Override
    public List<AppVersion> getListByPage(AppVersionQueryBean queryBean) {
        return appVersionMapper.getListByPage(queryBean);
    }

    @Override
    public AppVersion loadLastAppVersion() {
        AppVersionQueryBean queryBean = new AppVersionQueryBean();
        queryBean.setPageSize(1);
        queryBean.setOrderByClause("app_version_code DESC");
        List<AppVersion> list = appVersionMapper.getListByPage(queryBean);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
