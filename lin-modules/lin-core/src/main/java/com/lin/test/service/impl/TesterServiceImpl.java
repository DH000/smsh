package com.lin.test.service.impl;

import org.springframework.stereotype.Service;

import com.lin.entity.test.Tester;
import com.lin.service.impl.BaseServiceImpl;
import com.lin.service.test.TesterService;

@Service
public class TesterServiceImpl extends BaseServiceImpl<Tester, Integer> implements TesterService {
	@Override
	public void forUpdateTest(int id) {
		Tester t = findForUpdate(id);
		t.setName("xxx");
		
		update(t);
	}

}
