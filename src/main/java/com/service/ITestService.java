package com.service;

import com.vo.User;

import java.util.Set;

/**
 * Created by HSH on 2017/6/30.
 */
public interface ITestService {

    int query();

    User queryInfoByUsername(String userName);


    /**
     * 获取用户的角色
     * @param userName
     * @return
     */
    Set<String> getUserRoles(String userName);


    /**
     * 获取用户的权限
     * @param userName
     * @return
     */
    Set<String> getUserPermissions(String userName);
}
