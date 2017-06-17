package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Manufacturer;
import com.kzw.core.service.DefaultEntityManager;

@Service
@Transactional

public class ManageManuService extends DefaultEntityManager<Manufacturer, Integer>{
	
	public List<Manufacturer> getByIds(String ids){
		if(ids == null || ids.equals(";")|| ids.equals("")) return null;
		List<Manufacturer> list = getEntityDao().createQuery("from"+entityClass.getSimpleName()+" where id in "+com.kzw.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}

}
