package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Goods;
import com.entity.Material;
import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.service.GoodsService;

@Controller
@RequestMapping("/manageGoods")
public class ManageGoodsAction {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/view")
	public String view(){
		return "gym/manageGoods_view";
	}
	
	@RequestMapping("/list")
	
	public void list(PageRequest pageRequest,HttpServletRequest request,HttpServletResponse response){
		
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Goods> page = goodsService.search2(pageRequest,filters);
		String json = new JSON(page).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
		
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Msg save(HttpServletRequest request,Goods goods) {
		
		String base = request.getContextPath();

		if (goods.getId() == null) {
			goodsService.saveOrUpdate(goods);
		} else {
			Goods orgGoods = goodsService.get(goods.getId());
			try {
				BeanUtil.copyNotNullProperties(orgGoods, goods);
				goodsService.saveOrUpdate(orgGoods);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}
	
	@RequestMapping("/get/{id}")
	public String get(@PathVariable("id")int id,Model model){
		Goods goods = goodsService.get(id);
		model.addAttribute("goods",goods);
		return "gym/manageGoods_form";
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public Msg delete(String ids) {
		List<Goods> goods = goodsService.getByIds(ids);
		for(Goods m:goods){		
			goodsService.remove(m);
			}
		return new Msg(true,"原材料删除成功");
	}
	
}
