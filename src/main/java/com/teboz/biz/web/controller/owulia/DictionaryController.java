package com.teboz.biz.web.controller.owulia;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teboz.biz.model.Dictionary;
import com.teboz.biz.page.AjaxDataTableVO;
import com.teboz.biz.page.PaginationResult;
import com.teboz.biz.page.RetMsg;
import com.teboz.biz.service.DictionaryService;
import com.teboz.biz.web.admin.bean.DictionaryQueryBean;
import com.teboz.biz.web.controller.admin.AdminBaseController;

@Controller
@RequestMapping("/admin/auth")
public class DictionaryController extends AdminBaseController{
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/dictionary/dictionaryList")
    public String dictionaryList() {
        return "dictionary/dictionaryList";
    }

    @RequestMapping("/dictionary/infoAjaxList.json")
    @ResponseBody
    public AjaxDataTableVO<Dictionary> userPayAjaxList(DictionaryQueryBean queryBean, HttpServletRequest request) {
    	queryBean.getPagination().setTotalRows(dictionaryService.getDictionaryCount(queryBean));
		//queryBean.setOrderByClause("seq_num desc");
		List< Dictionary> list=  dictionaryService.getDictionaryList(queryBean);	
        PaginationResult<List< Dictionary>> result = new PaginationResult<List<Dictionary>>(list, queryBean.getPagination());
        return new AjaxDataTableVO< Dictionary>(queryBean.getsEcho(), result);
    }

    @RequestMapping(value = "/dictionary/addDictionary")
    public String addDictionary() {
        return "dictionary/editDictionary";
    }

    @RequestMapping(value = "/dictionary/editDictionary")
    public String editDictionary(Integer id, Model model) {
        Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
        model.addAttribute("dictionary", dictionary);
        return "dictionary/editDictionary";
    }

    @RequestMapping(value = "/dictionary/saveDictionary")
    public String  saveDictionary(Dictionary dictionary) {
        Date now = new Date();
        Integer id = dictionary.getId();
        dictionary.setUpdateTime(now);
        dictionary.setUpdateUserId(1);
        if (id == null) {
            dictionary.setDeleteFlag(false);
            dictionary.setCreateTime(now);
            dictionary.setCreateUserId(1);
            dictionaryService.insertSelective(dictionary);
        } else {
            dictionaryService.updateByPrimaryKeySelective(dictionary);
        }
        return "redirect:/admin/auth/dictionary/dictionaryList";
    }

    @RequestMapping(value = "/dictionary/delDictionary")
    @ResponseBody
    public RetMsg delDictionary(Integer id) {
        Dictionary record = new Dictionary();
        record.setId(id);
        record.setDeleteFlag(true);
        dictionaryService.updateByPrimaryKeySelective(record);
        return RetMsg.createRetMsg("true", "删除成功");
    }
}
