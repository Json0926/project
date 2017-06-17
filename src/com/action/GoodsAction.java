package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Goods;
import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.service.GoodsService;

@Controller("GoodsAction")
//@RequestMapping("/cliGoods")
public class GoodsAction {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("cliGoods/view")
	public String view(){
		return "gym/cliGoods_view";
	}
	
	@RequestMapping("proGoods/view")
	public String product_view(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		return "gym/proGoods_view";
	}
	
	@RequestMapping("proGoods/list")
	
	public void list(PageRequest pageRequest,HttpServletRequest request,HttpServletResponse response){
		
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Goods> page = goodsService.search2(pageRequest,filters);
		String json = new JSON(page).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
		
	}
	
@RequestMapping("cliGoods/list")
	
	public void list2(PageRequest pageRequest,HttpServletRequest request,HttpServletResponse response){
		
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Goods> page = goodsService.search2(pageRequest,filters);
		String json = new JSON(page).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
		
	}
	
	
	@RequestMapping("save")
	@ResponseBody
	public Msg save(HttpServletRequest request,Goods goods) {
		
		
		System.out.println("save");
		String base = request.getContextPath();

		if (goods.getId() == null) {
			goodsService.saveOrUpdate(goods);
		} else {
			Goods orgGoods = goodsService.get(goods.getId());
			try {
				BeanUtil.copyNotNullProperties(orgGoods, goods);
//				orgMember.getCard().setBeginTime(member.getCard().getBeginTime());
//				orgMember.getCard().setEndTime(member.getCard().getEndTime());
				goodsService.saveOrUpdate(orgGoods);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}
	
	@RequestMapping("get/{id}")
	public String get(@PathVariable("id")int id,Model model){
		Goods goods = goodsService.get(id);
		model.addAttribute("goods",goods);
		return "gym/cliGoods_form";
	}

}
