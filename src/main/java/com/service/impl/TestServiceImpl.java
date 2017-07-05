package com.service.impl;

import com.mapper.TestMapper;
import com.service.ITestService;
import com.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by HSH on 2017/6/30.
 */
@Service("testService")
public class TestServiceImpl implements ITestService{
    @Autowired
    TestMapper testMapper;

    public int query() {
        return testMapper.query();
    }

    public User queryInfoByUsername(String userName) {
        return testMapper.queryInfoByUsername(userName);
    }


    /**
     * 获取用户的角色
     * @param userName
     * @return
     */
    public Set<String> getUserRoles(String userName) {
        return testMapper.getUserRoles(userName);
    }


    /**
     * 获取用户的权限
     * @param userName
     * @return
     */
    public Set<String> getUserPermissions(String userName) {
        return testMapper.getUserPermissions(userName);
    }
}
