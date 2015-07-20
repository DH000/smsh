package com.lin.service.test.impl;

import org.springframework.stereotype.Service;

import com.lin.entity.test.Tester;
import com.lin.service.impl.BaseService;
import com.lin.service.test.ITesterService;

@Service
public class TesterService extends BaseService<Tester, Integer> implements ITesterService {
	@Override
	public void forUpdateTest(int id) {
		Tester t = findForUpdate(id);
		t.setName("xxx");
		
		update(t);
	}

}
