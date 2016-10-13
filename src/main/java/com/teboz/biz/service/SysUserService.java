/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: SysUserService.java
 * Author:   long.yu
 * Date:     2015年12月21日 下午7:00:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.service;

import java.util.List;

import com.autostreets.framework.common.dal.GenericService;
import com.teboz.biz.model.SysUser;
import com.teboz.biz.model.SysUserExample;
import com.teboz.biz.web.admin.bean.SysUserQueryBean;

/**
 * 后台用户
 *
 * @author tao.huang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface SysUserService extends GenericService<SysUser, SysUserExample, Integer> {
	
	List<SysUser> getSysUserList(SysUserQueryBean queryBean);
	
	Integer getSysUserCount(SysUserQueryBean queryBean);

}
