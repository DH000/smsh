package com.lin.service.impl.test;

import org.springframework.stereotype.Service;

import com.lin.entity.test.Tester;
import com.lin.service.impl.BaseService;
import com.lin.service.test.ITesterService;

@Service
public class TesterService extends BaseService<Tester, Integer> implements ITesterService {

}
