package com.lin.dao.test.impl;

import org.springframework.stereotype.Repository;

import com.lin.dao.impl.BaseDaoImpl;
import com.lin.dao.test.TesterDao;
import com.lin.entity.test.Tester;

/**
 * 
 * 
 * @ClassName: TesterDao 
 * @Description: 测试dao
 * @author xuelin 
 * @date Jun 29, 2015 3:46:00 PM 
 *
 */
@Repository
public class TesterDaoImpl extends BaseDaoImpl<Tester, Integer>implements TesterDao {

}
