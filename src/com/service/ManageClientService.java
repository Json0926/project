package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Client;
import com.entity.Material;

@Service
@Transactional

public class ManageClientService extends DefaultEntityManager<Client, Integer>{
	public List<Client> getByIds(String ids){
		if(ids == null || ids.equals(";")|| ids.equals("")) return null;
		List<Client> list = getEntityDao().createQuery("from "+entityClass.getSimpleName()+" where id in "+com.kzw.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}
	
    public void update() {
	
}

}
