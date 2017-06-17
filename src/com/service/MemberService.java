package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Member;
import com.kzw.core.service.DefaultEntityManager;

@Service
@Transactional
public class MemberService extends DefaultEntityManager<Member, Integer> {

	public List<Member> getByIds(String ids) {
		if (ids == null || ids.equals(";") || ids.equals("")) return null;
		List<Member> list =getEntityDao().createQuery(" from "+entityClass.getSimpleName()+" where id in "+com.kzw.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}
	
}
