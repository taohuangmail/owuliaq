package com.teboz.biz.service;

import java.util.List;

import com.autostreets.framework.common.dal.GenericService;
import com.teboz.biz.model.Partner;
import com.teboz.biz.model.PartnerExample;
import com.teboz.biz.web.admin.bean.PartnerQueryBean;

public interface PartnerService extends GenericService<Partner, PartnerExample, Integer>{
	
	 /**
     * 分页查询
     *
     * @param queryBean
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    
    List<Partner> getPartnerList(PartnerQueryBean queryBean);
	
	Integer getPartnerCount(PartnerQueryBean queryBean);

}
