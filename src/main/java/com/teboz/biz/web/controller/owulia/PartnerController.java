package com.teboz.biz.web.controller.owulia;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teboz.biz.model.Dictionary;
import com.teboz.biz.model.Partner;
import com.teboz.biz.page.AjaxDataTableVO;
import com.teboz.biz.page.PaginationResult;
import com.teboz.biz.page.RetMsg;
import com.teboz.biz.service.DictionaryService;
import com.teboz.biz.service.PartnerService;
import com.teboz.biz.web.admin.bean.PartnerQueryBean;
import com.teboz.biz.web.controller.admin.AdminBaseController;

@Controller
@RequestMapping("/admin/auth")
public class PartnerController extends AdminBaseController{
    @Autowired
    private PartnerService partnerService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    public static final String JOB_GROUP_ID = "job"; 

    @RequestMapping(value = "/partner/partnerList")
    public String partnerList() {
        return "partner/partnerList";
    }

    @RequestMapping("/partner/infoAjaxList.json")
    @ResponseBody
    public AjaxDataTableVO<Partner> userPayAjaxList(PartnerQueryBean queryBean, HttpServletRequest request) {
    	queryBean.getPagination().setTotalRows(partnerService.getPartnerCount(queryBean));
		//queryBean.setOrderByClause("seq_num desc");
		List< Partner> list=  partnerService.getPartnerList(queryBean);	
        PaginationResult<List< Partner>> result = new PaginationResult<List<Partner>>(list, queryBean.getPagination());
        return new AjaxDataTableVO< Partner>(queryBean.getsEcho(), result);
    }

    @RequestMapping(value = "/partner/addPartner")
    public String addPartner(Model model) {
    	List<Dictionary> jobList = dictionaryService.getDictionaryByGroupId(JOB_GROUP_ID);
    	model.addAttribute("jobList", jobList);
        return "partner/editPartner";
    }

    @RequestMapping(value = "/partner/editPartner")
    public String editPartner(Integer id, Model model) {
        Partner partner = partnerService.selectByPrimaryKey(id);
        model.addAttribute("partner", partner);
        List<Dictionary> jobList = dictionaryService.getDictionaryByGroupId(JOB_GROUP_ID);
    	model.addAttribute("jobList", jobList);
        return "partner/editPartner";
    }

    @RequestMapping(value = "/partner/savePartner")
    public String  savePartner(Partner partner) {
        Date now = new Date();
        Integer id = partner.getId();
        partner.setUpdateTime(now);
        partner.setUpdateUserId(1);
        if (id == null) {
            partner.setDeleteFlag(false);
            partner.setCreateTime(now);
            partner.setCreateUserId(1);
            partner.setPassword(DigestUtils.md5Hex(partner.getPassword()));
            partnerService.insertSelective(partner);
        } else {
        	Partner oldPartner = partnerService.selectByPrimaryKey(partner.getId());
        	if (!oldPartner.getPassword().equals(partner.getPassword())) {
                partner.setPassword(DigestUtils.md5Hex(partner.getPassword()));
			}
            partnerService.updateByPrimaryKeySelective(partner);
        }
        return "redirect:/admin/auth/partner/partnerList";
    }

    @RequestMapping(value = "/partner/delPartner")
    @ResponseBody
    public RetMsg delPartner(Integer id) {
        Partner record = new Partner();
        record.setId(id);
        record.setDeleteFlag(true);
        partnerService.updateByPrimaryKeySelective(record);
        return RetMsg.createRetMsg("true", "删除成功");
    }
}
