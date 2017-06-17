package com.action;

import java.util.ArrayList;
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
import com.entity.Member;
import com.entity.Supply;
import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.service.ManageClientService;
import com.service.MemberService;

@Controller
@RequestMapping("/manageClient")
public class ManageClientAction {

	@Autowired
	private ManageClientService manageClientService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/view")
	public String view(){
		return "manufacture/manageClient_view";
	}
	
	@RequestMapping("/list")
	public void list(PageRequest pageRequest,HttpServletRequest request,HttpServletResponse response){
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Client> page = manageClientService.search2(pageRequest,filters);
		String json = new JSON(page).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
	}
	
	
	
	@RequestMapping("save")
	@ResponseBody
	public Msg save(HttpServletRequest request, Client client){
		String base = request.getContextPath();
		if(client.getId() == null){
			manageClientService.saveOrUpdate(client);
		}else{
			Client orgClient = manageClientService.get(client.getId());
			try{
				BeanUtil.copyNotNullProperties(orgClient,client);
				manageClientService.saveOrUpdate(orgClient);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return new Msg(true);
	}
	
	@RequestMapping("get/{id}")
	public String get(@PathVariable("id")int id, Model model) {
		List<String> clientName = new ArrayList<String>();
		
		for (Client	 iterable_element : manageClientService.getAll()) {
			clientName.add(iterable_element.getRealname());
		}
		Client client = manageClientService.get(id);
		model.addAttribute("clientName", clientName);
		model.addAttribute("client",client);
		return "manufacture/manageClient_form";
	}
	
	
	
	@RequestMapping("/add")
	public String add( Model model) {
		List<String> clientName = new ArrayList<String>();
		
		for (Client	 iterable_element : manageClientService.getAll()) {
			clientName.add(iterable_element.getRealname());
		}
		model.addAttribute("clientName", clientName);
		
		return "manufacture/manageClient_form";
//		pages/manufacture/manageClient_form.jsp
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Msg delete(String ids){
		
		
		List<Client> clients = manageClientService.getByIds(ids);
		for(Client m:clients){
			manageClientService.remove(m);
		}
		return new Msg(true,"信息删除成功");
	}
}
