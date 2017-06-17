package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Material;
import com.kzw.core.service.DefaultEntityManager;

@Service
@Transactional

public class ShowMaterialService extends DefaultEntityManager<Material, Integer>{
	
	public List<Material> getByIds(String ids){
		if(ids == null || ids.equals(";")|| ids.equals("")) return null;
		List<Material> list = getEntityDao().createQuery("from"+entityClass.getSimpleName()+" where id in "+com.kzw.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}

}