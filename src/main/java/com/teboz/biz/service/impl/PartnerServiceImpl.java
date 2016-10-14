package com.teboz.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autostreets.framework.common.dal.GenericMapper;
import com.autostreets.framework.common.dal.GenericServiceImpl;
import com.teboz.biz.mapper.PartnerMapper;
import com.teboz.biz.model.Partner;
import com.teboz.biz.model.PartnerExample;
import com.teboz.biz.service.PartnerService;
import com.teboz.biz.web.admin.bean.PartnerQueryBean;

@Service
public class PartnerServiceImpl extends GenericServiceImpl<Partner, PartnerExample, Integer> implements PartnerService{

	@Autowired
	private PartnerMapper partnerMapper;
	
	@Override
	protected GenericMapper<Partner, PartnerExample, Integer> getGenericMapper() {
		return partnerMapper;
	}

	@Override
	public List<Partner> getPartnerList(PartnerQueryBean queryBean) {
		return partnerMapper.getPartnerList(queryBean);
	}

	@Override
	public Integer getPartnerCount(PartnerQueryBean queryBean) {
		return partnerMapper.getPartnerCount(queryBean);
	}

}
