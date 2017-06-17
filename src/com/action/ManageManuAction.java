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

import com.entity.Client;
import com.entity.Goods;
import com.entity.Manufacturer;
import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.service.ManageManuService;

@Controller
@RequestMapping("/manageManu")

public class ManageManuAction {
	@Autowired
	private ManageManuService manageManuService;
	
	@RequestMapping("/view")
	public String view(){
		return "manufacture/manageManu_view";
	}
	
	@RequestMapping("/list")
	public void list(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response){
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Manufacturer> page = manageManuService.search2(pageRequest, filters);
		String json = new JSON(page).buildWithFilters(3);
		ResponseUtils.renderJson(response, json);	
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Msg save(HttpServletRequest request, Manufacturer manufacturer){
		String base = request.getContextPath();
		if(manufacturer.getId() == null){
			manageManuService.saveOrUpdate(manufacturer);
		}else{
			Manufacturer orgManufacture = manageManuService.get(manufacturer.getId());
			try{
				BeanUtil.copyNotNullProperties(orgManufacture,manufacturer);
				manageManuService.saveOrUpdate(orgManufacture);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return new Msg(true);
	}
	
	
	@RequestMapping("/get/{id}")
	public String get(@PathVariable("id")int id,Model model){
		Manufacturer manufacturer = manageManuService.get(id);
		model.addAttribute("manufacturer",manufacturer);
		return "gym/manageManu_form";
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public Msg delete(String ids) {
		List<Manufacturer> manufacturer = manageManuService.getByIds(ids);
		for(Manufacturer m:manufacturer){		
			manageManuService.remove(m);
			}
		return new Msg(true,"生产商信息删除成功");
	}

}
