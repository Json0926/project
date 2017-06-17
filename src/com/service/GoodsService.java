package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Goods;



@Service
@Transactional

public class GoodsService extends DefaultEntityManager<Goods, Integer>{
	
	public List<Goods> getByIds(String ids){
		if(ids == null || ids.equals(";")|| ids.equals("")) return null;
		List<Goods> list = getEntityDao().createQuery("from"+entityClass.getSimpleName()+" where id in "+com.kzw.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}

}
