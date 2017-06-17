package com.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Lockers;
import com.entity.Supply;
import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.service.ManageSupplierService;



@Controller
@RequestMapping("/manageSupplier")
public class ManageSupplierAction {
	
	
	@Autowired
	private ManageSupplierService manageSupplierService;
	
	@RequestMapping("view")
	public String view() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		return "gym/manageSupplier_view";
	}
	
	@RequestMapping("list")
	public void list(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		// 获得查询条件
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Supply> page = manageSupplierService.search2(pageRequest, filters);
		String json = new JSON(page).buildWithFilters(3);
		ResponseUtils.renderJson(response, json);		
	}
	
	

	
	
/*	@RequestMapping("save")
	@ResponseBody
	public Msg save(Lockers lockers) {
		if(lockers.getMember().getId()==null){
			lockers.setMember(null);
		}
		if (lockers.getId() == null) {
			lockersService.saveOrUpdate(lockers);
		} else {
			Lockers orgLockers = lockersService.get(lockers.getId());
			try {
				BeanUtil.copyNotNullProperties(orgLockers, lockers);
//				orgLockers.setBeginTime(lockers.getBeginTime());
//				orgLockers.setEndTime(lockers.getEndTime());
				lockersService.saveOrUpdate(orgLockers);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}*/
	@RequestMapping("get/{id}")
	public String get(@PathVariable("id")int id, Model model) {
		Supply supply = manageSupplierService.get(id);
		model.addAttribute("supply", supply);
		return "gym/lockers_form";
	}
	
	@ResponseBody
	@RequestMapping("del")
	public Msg delete(String ids) {
		manageSupplierService.remove(ids);
		return new Msg(true);
	}
	
	@ResponseBody
	@RequestMapping("clear/{id}")
	public Msg clear(@PathVariable("id")int id, HttpServletRequest request,PageRequest pageRequest,HttpServletResponse response) {
		manageSupplierService.clear(id);
//		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
//		Page<Supply> page = manageSupplierService.search2(pageRequest, filters);
//		Page<Supply> page2 = new Page<Supply>();
//		List<Supply> list2 = new ArrayList<Supply>();
//		for (Supply supply : page) {
//			if (supply.getId()!=id) {
//				list2.add(supply);
//			}
//		}
//		page2.setResult(list2);
//		String json = new JSON(page).buildWithFilters(3);
//		ResponseUtils.renderJson(response, json);
//		
		return new Msg(true);
	}
	
/*	@RequestMapping("init")
	public void init() {
		for(int i=0;i<100;i++){
			Lockers lockers = new Lockers();
			lockers.setNo("G"+(100+i));
			lockersService.saveOrUpdate(lockers);
		}
	}
	@InitBinder
	public void initBinder1(WebDataBinder binder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
	}
	*/

}
