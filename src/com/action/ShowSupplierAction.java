package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Supply;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.service.ShowSupplierService;

@Controller
@RequestMapping("/showSupplier")
public class ShowSupplierAction {
	
	@Autowired
	private ShowSupplierService showSupplierService;
	
	@Autowired
	@RequestMapping("/view")
	public String view(){
		return "gym/showSupplier_view";
	}
	
	@RequestMapping("/list")
	
	public void list(PageRequest pageRequest,HttpServletRequest request,HttpServletResponse response){
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Supply> page = showSupplierService.search2(pageRequest, filters);
		String json = new JSON(page).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
	}

}
