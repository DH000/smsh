package com.lin.service.test;

import com.lin.entity.test.Tester;
import com.lin.service.BaseService;

/**
 */
public interface TesterService extends BaseService<Tester, Integer> {

	public void forUpdateTest(int id);
	
}
