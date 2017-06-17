package com.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kzw.core.service.DefaultEntityManager;
import com.entity.Member;

@Service
@Transactional
public class AppUserService extends DefaultEntityManager<Member, Integer> {
	
}
