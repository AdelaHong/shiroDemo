package com.service.impl;

import com.mapper.TestMapper;
import com.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HSH on 2017/6/30.
 */
@Service
public class TestService implements ITestService{
    @Autowired
    TestMapper testMapper;

    @Override
    public int query() {
        return testMapper.query();
    }
}
