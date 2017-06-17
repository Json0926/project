package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PrinterLocation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Manufacturer;
import com.entity.Material;
import com.entity.Member;
import com.entity.Supply;
import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.service.ManageManuService;
import com.service.ManageSupplierService;
import com.service.MaterialService;
import com.service.MemberService;

@Controller("MaterialAction")
@RequestMapping("/supMaterial")
public class MaterialAction {

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private ManageSupplierService manageSupplierService;
	
	@Autowired
	private ManageManuService manageManuService;
	
	@Autowired
	@RequestMapping("/view")
	public String view(){
		return "gym/supMaterial_view";
	}
	
/*	@RequestMapping("proMaterial/view")
	public String product_view(){
		
		return "gym/proMaterial_view";
	}*/
	
	@RequestMapping("/list")
	
	public void list(PageRequest pageRequest,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Material> page = materialService.search2(pageRequest,filters);
/*		Member member = (Member) session.getAttribute("Member");
		System.out.println("@@@@@"+member.getRealname());
		List<Material> lm = new ArrayList<>();
		for (Material material : page) {
			if (material.getSupplierName()==member.getRealname()) {
				lm.add(material);
			}
		}
		page.setResult(lm);*/
		String json = new JSON(page).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
		
	}
	
/*	@RequestMapping("proMaterial/list")
	
	public void prolist(PageRequest pageRequest,HttpServletRequest request,HttpServletResponse response){
		
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Material> page = materialService.search2(pageRequest,filters);
		String json = new JSON(page).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
		
	}*/
	
	/*@RequestMapping("save")
	@ResponseBody
	public Msg save(HttpServletRequest request,Material material){
		String base = request.getContextPath();
		if(material.getId() == null){
			materialService.saveOrUpdate(material);
		}else {
			Material orgMaterial = materialService.get(material.getId());
			try{
				BeanUtil.copyNotNullProperties(orgMaterial, material);
				materialService.saveOrUpdate(orgMaterial);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return new Msg(true);
	}*/
	
	@RequestMapping("/save")
	@ResponseBody
	public Msg save(HttpServletRequest request,Material	material) {
		
		
		System.out.println("save");
		String base = request.getContextPath();

		if (material.getId() == null) {
			materialService.saveOrUpdate(material);
		} else {
			Material orgMaterial = materialService.get(material.getId());
			try {
				BeanUtil.copyNotNullProperties(orgMaterial, material);
				materialService.saveOrUpdate(orgMaterial);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}
	
	@RequestMapping("/get/{id}")
	public String get(@PathVariable("id")int id,Model model){
		Material material = materialService.get(id);
		List<String> gysqymc = new ArrayList<String>();
		List<String> manuName = new ArrayList<String>();
		
		for (Supply	 iterable_element : manageSupplierService.getAll()) {
			gysqymc.add(iterable_element.getRealname());
		}
		
		for (Manufacturer	 iterable_element : manageManuService.getAll()) {
			manuName.add(iterable_element.getRealname());
		}
	
		
		model.addAttribute("gysqymc",gysqymc);
		model.addAttribute("material",material);
		model.addAttribute("manuName", manuName);
		return "gym/supMaterial_form";
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public Msg delete(String ids) {
		List<Material> materials = materialService.getByIds(ids);
		for(Material m:materials){		
			materialService.remove(m);
			}
		
//			cardService.remove(m.getCard());
		
		//memberService.remove(ids);
		return new Msg(true,"原材料删除成功");
	}

}
