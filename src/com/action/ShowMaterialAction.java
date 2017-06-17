package com.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.ShowMaterialService;

@Controller
@RequestMapping("/showMaterial")
public class ShowMaterialAction {
	@Autowired
	private ShowMaterialService showMaterialService;
	
	@RequestMapping("/view")
	public String view(){
		return "manufacture/showMaterial_view";
	}
}
