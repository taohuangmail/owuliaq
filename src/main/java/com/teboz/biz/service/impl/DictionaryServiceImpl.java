package com.teboz.biz.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autostreets.framework.common.dal.GenericMapper;
import com.autostreets.framework.common.dal.GenericServiceImpl;
import com.teboz.biz.mapper.DictionaryMapper;
import com.teboz.biz.model.Dictionary;
import com.teboz.biz.model.DictionaryExample;
import com.teboz.biz.service.DictionaryService;
import com.teboz.biz.web.admin.bean.DictionaryQueryBean;

@Service
public class DictionaryServiceImpl extends GenericServiceImpl<Dictionary, DictionaryExample, Integer> implements DictionaryService{

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
	protected GenericMapper<Dictionary, DictionaryExample, Integer> getGenericMapper() {
		return dictionaryMapper;
	}

	@Override
	public List<Dictionary> getDictionaryList(DictionaryQueryBean queryBean) {
		return dictionaryMapper.getDictionaryList(queryBean);
	}

	@Override
	public Integer getDictionaryCount(DictionaryQueryBean queryBean) {
		return dictionaryMapper.getDictionaryCount(queryBean);
	}

	@Override
	public List<Dictionary> getDictionaryByGroupId(String groupId) {
		List<Dictionary> dictionaryList = null;
		if(StringUtils.isNotBlank(groupId)){
			DictionaryExample de = new DictionaryExample();
			DictionaryExample.Criteria criteria = de.createCriteria();
			criteria.andGroupIdEqualTo(groupId);
			criteria.andDeleteFlagEqualTo(false);
			dictionaryList = dictionaryMapper.selectByExample(de);
		}
		return dictionaryList;
	}

}
