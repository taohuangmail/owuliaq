package com.teboz.biz.mapper;

import java.util.List;

import com.autostreets.framework.common.dal.GenericMapper;
import com.teboz.biz.model.Dictionary;
import com.teboz.biz.model.DictionaryExample;
import com.teboz.biz.web.admin.bean.DictionaryQueryBean;

public interface DictionaryMapper extends GenericMapper<Dictionary, DictionaryExample, Integer>{
	
	 /**
     * 分页查询
     *
     * @param queryBean
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    
    List<Dictionary> getDictionaryList(DictionaryQueryBean queryBean);
	
	Integer getDictionaryCount(DictionaryQueryBean queryBean);

}
