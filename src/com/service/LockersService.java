package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kzw.core.service.DefaultEntityManager;
import com.entity.Lockers;
import com.entity.Department;

@Service
@Transactional
public class LockersService extends DefaultEntityManager<Lockers, Integer> {

	public void clear(int id) {
		Lockers lockers = get(id);
//		lockers.setAmount(null);
//		lockers.setBeginTime(null);
//		lockers.setEndTime(null);
		lockers.setMember(null);
		saveOrUpdate(lockers);	
	}

	

}
