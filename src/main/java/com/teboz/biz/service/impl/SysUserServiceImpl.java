/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: SysUserServiceImpl.java
 * Author:   long.yu
 * Date:     2015年12月21日 下午7:01:43
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
import com.teboz.biz.mapper.SysUserMapper;
import com.teboz.biz.model.SysUser;
import com.teboz.biz.model.SysUserExample;
import com.teboz.biz.service.SysUserService;
import com.teboz.biz.web.admin.bean.SysUserQueryBean;

/**
 * 后台用户服务实现
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service("sysUserService")
public class SysUserServiceImpl extends GenericServiceImpl<SysUser, SysUserExample, Integer> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected GenericMapper<SysUser, SysUserExample, Integer> getGenericMapper() {
        return this.sysUserMapper;
    }

	@Override
	public List<SysUser> getSysUserList(SysUserQueryBean queryBean) {
		return sysUserMapper.getSysUserList(queryBean);
	}

	@Override
	public Integer getSysUserCount(SysUserQueryBean queryBean) {
		return sysUserMapper.getSysUserCount(queryBean);
	}

}
