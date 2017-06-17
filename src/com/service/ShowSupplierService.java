package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Supply;

@Service
@Transactional

public class ShowSupplierService extends DefaultEntityManager<Supply, Integer>{

	public List<Supply> getByIds(String ids){
		if(ids == null|| ids.equals(";")|| ids.equals("")) return null;
		List<Supply> list = getEntityDao().createQuery("from"+entityClass.getSimpleName()+" where id in "+com.kzw.core.util.StringUtil.idStrToIds(ids)).list();
		return list;
	}

	
}
