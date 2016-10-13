package com.teboz.biz.mapper;

import java.util.List;

import com.autostreets.framework.common.dal.GenericMapper;
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
public interface SysUserMapper extends GenericMapper<SysUser, SysUserExample, Integer> {

	List<SysUser> getSysUserList(SysUserQueryBean queryBean);
	
	Integer getSysUserCount(SysUserQueryBean queryBean);
}
