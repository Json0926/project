package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.MD5;
import com.kzw.core.util.web.ResponseUtils;
import com.entity.Member;
import com.service.AppUserService;
import com.service.MemberService;


@Controller
@RequestMapping("/user")
public class AppUserAction {

	@Autowired
//	private AppUserService userService;
	private MemberService meberservice;
	private Logger  loger = Logger.getLogger(AppUserAction.class);
	
	@RequestMapping("view")
	public String view() {
		return "system/user_view";
	}
	
	/**
	 * 登陆
	 * */
	/*@RequestMapping("login")
	public String login(HttpServletRequest request, String uname, String passwd) {
		AppUser user = userService.findUniqueBy("uname", uname);
		if(user!=null && user.getPasswd().equals(MD5.encode(passwd))) {
			Hibernate.initialize(user.getDept());
			Hibernate.initialize(user.getSex());
			request.getSession().setAttribute("USER", user);
			return "redirect:/index.jsp";
		}
		
		request.setAttribute("msg", "用户或密码错误！");
		return "forward:/login.jsp";
	}*/
	@RequestMapping("login")
	public String login(HttpServletRequest request,String username, String password) {
//		
		Member user = meberservice.findUniqueBy("username", username);
		
		
		if(user!=null && user.getPassword().equals(password)) {
			request.getSession().setAttribute("USER", user);
			return "redirect:/index.jsp";
		}
		
		request.setAttribute("msg", "用户或密码错误！");
		return "login";
	}
	
	
	/**
	 * 退出
	 * */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("list")
	public void list(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Member> page = meberservice.search2(pageRequest, filters);
		String json = new JSON(page).buildWithFilters(3);
		ResponseUtils.renderJson(response, json);
	}
	
	@ResponseBody
	@RequestMapping("save")
	public Msg save(Member user ) {
		user.setPassword(user.getPassword());
		if (user.getId() == null) {
			meberservice.saveOrUpdate(user);
		} else {
			Member orgUser = meberservice.get(user.getId());
			try {
				BeanUtil.copyNotNullProperties(orgUser, user);
				meberservice.saveOrUpdate(orgUser);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}
	
	@RequestMapping("get/{id}")
	public String get(@PathVariable("id")int id, Model model) {
		Member user = meberservice.get(id);
		model.addAttribute("user", user);
		return "system/user_form";
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public Msg delete(String ids) {
		meberservice.remove(ids);
		return new Msg(true);
	}
}
