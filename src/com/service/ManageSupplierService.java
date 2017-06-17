package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Lockers;
import com.entity.Supply;
import com.kzw.core.service.DefaultEntityManager;

@Service
@Transactional
public class ManageSupplierService extends DefaultEntityManager<Supply, Integer> {

	public List<Supply> getByIds(String ids) {
		if (ids == null || ids.equals(";") || ids.equals("")) return null;
		List<Supply> list =getEntityDao().createQuery(" from "+entityClass.getSimpleName()+" where id in "+com.kzw.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}
	@Override
	public List<Supply> getAll() {
		// TODO Auto-generated method stub
		return super.getAll();
	}
	
//	public void clear(int id) {
//		Supply supply = get(id);
//		supply.setMember(null);
//		saveOrUpdate(supply);	
//	}
//	
	public void clear(int id) {
		Supply supply = get(id);
		//getEntityDao().delete(id);
		getEntityDao().delete(supply);
	//	saveOrUpdate(supply);	
	}
	
	
}