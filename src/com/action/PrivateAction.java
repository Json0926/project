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

import com.kzw.comm.vo.Msg;
import com.kzw.core.orm.Page;
import com.kzw.core.orm.PageRequest;
import com.kzw.core.orm.PropertyFilter;
import com.kzw.core.orm.StringPropertyFilter;
import com.kzw.core.util.BeanUtil;
import com.kzw.core.util.JSON;
import com.kzw.core.util.web.ResponseUtils;
import com.entity.Lockers;
import com.entity.Member;
import com.service.MemberService;

@Controller
@RequestMapping("/private")
public class PrivateAction {
	
	@Autowired
	private MemberService memberService ;
//	
//	@Autowired
//	private LockersService lockersService;
//	
//	@Autowired
//	private CardService cardService;
//	
	
	@RequestMapping("view")
	public String view() {
		return "gym/private_view";
	}
	
/*	@RequestMapping("list/{coachId}")
	public void list(@PathVariable("coachId")int coachId, PageRequest pageRequest, HttpServletResponse response) {
		// 获得查询条件
		Page<Member> page = memberService.search(pageRequest, new PropertyFilter("coach.id", coachId));
		
		String json = new JSON(page).buildWithFilters(3);
		ResponseUtils.renderJson(response, json);
	}*/
	
	@RequestMapping("list")
	public void list(PageRequest pageRequest,HttpServletRequest request, HttpServletResponse response) {
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Member> page = memberService.search2(pageRequest, filters);
		
		Page<Member> showpage = new Page<Member>();
		Member member = (Member)request.getSession().getAttribute("USER");
		List<Member> lm = new ArrayList<>();
		if (member.getManufacturer()!=null) {
			for (Member willshowmember : page) {
				if (willshowmember.getManufacturer()!=null) {
					if (member.getRealname().equals(willshowmember.getRealname())) {
						lm.add(willshowmember);
					}
					
				}
			}
			
		}
		else if (member.getSupply()!=null) {
			for (Member willshowmember : page) {
				if (willshowmember.getSupply()!=null) {
					if (member.getRealname().equals(willshowmember.getRealname())) {
						lm.add(willshowmember);
					}
					
				}
			}
			
		}
		else if (member.getClient()!=null) {
			for (Member willshowmember : page) {
				if (willshowmember.getClient()!=null) {
					
					lm.add(willshowmember);
				}
			}
			
		}
		showpage.setResult(lm);
		
		String json = new JSON(showpage).buildWithFilters(4);
		ResponseUtils.renderJson(response, json);
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Msg save(HttpServletRequest request,Member	member) {
		
		
		System.out.println("save");
		String base = request.getContextPath();

		if (member.getId() == null) {
			memberService.saveOrUpdate(member);
		} else {
			Member orgMember = memberService.get(member.getId());
			try {
				BeanUtil.copyNotNullProperties(orgMember, member);
//				orgMember.getCard().setBeginTime(member.getCard().getBeginTime());
//				orgMember.getCard().setEndTime(member.getCard().getEndTime());
				memberService.saveOrUpdate(orgMember);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}
	//根据会员ID,教练ID保存信息
	@RequestMapping("save/{id}/{coachId}")
	@ResponseBody
	public Msg save2(@PathVariable("id")int id,@PathVariable("coachId")int coachId) {
//		Member m = memberService.get(id);
//		Employee coach =new Employee();
//		coach.setId(coachId);
//		m.setCoach(coach);
//		memberService.saveOrUpdate(m);
		return new Msg(true,"保存成功");
	}
	
	@RequestMapping("get/{id}")
	public String get(@PathVariable("id")int id, Model model) {
		Member member = memberService.get(id);
		model.addAttribute("member", member);
		return "gym/private_form";
	}
	
	
	@ResponseBody
	@RequestMapping("del")
	public Msg delete(String ids) {
		List<Member> members = memberService.getByIds(ids);
		for(Member m:members){
//			if(m.getLockers().size()>0){
//				for(Lockers locker:m.getLockers()){
//					lockersService.clear(locker.getId());//将会员私人租柜会员置空再删除
//					lockersService.saveOrUpdate(locker);
//				}
			
			memberService.remove(m);
			}
		
//			cardService.remove(m.getCard());
		
		//memberService.remove(ids);
		return new Msg(true,"会员信息删除成功");
	}
//	
//	@ResponseBody
//	@RequestMapping("delCoach")
//	public Msg delCoach(String ids) {
//		List<Member> members = memberService.getByIds(ids);
//		for(Member m:members){
//				m.setCoach(null);
//				memberService.saveOrUpdate(m);
//
//		}
//		//memberService.remove(ids);
//		return new Msg(true,"删除会员成功");
//	}
	
	@InitBinder
	public void initBinder1(WebDataBinder binder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
	}
}
