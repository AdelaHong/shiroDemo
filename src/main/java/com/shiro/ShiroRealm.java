package com.shiro;

import com.service.ITestService;
import com.service.impl.TestServiceImpl;
import com.vo.User;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by HSH on 2017/7/3.
 */
public class ShiroRealm extends AuthorizingRealm {
    private static Logger log= Logger.getLogger(AuthorizingRealm.class);

    private ITestService testService;

    public ITestService getTestService() {
        return testService;
    }

    public void setTestService(ITestService testService) {
        this.testService = testService;
    }

    /**
     * 为当前登录成功的用户授予角色和权限
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //AuthorizationInfo 封装了当前用户的角色与权限
        //PrincipalCollection 用户身份
        String userName = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            authorizationInfo.setRoles(testService.getUserRoles(userName));
            log.info("用户的角色："+ testService.getUserRoles(userName).toString());
            authorizationInfo.setStringPermissions(testService.getUserPermissions(userName));
            log.info("用户的权限："+ testService.getUserPermissions(userName).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    /**
     * 验证当前登录的用户
     * SimpleAuthenticationInfo的实现类，封装正确的用户名密码
     * AuthenticationToken 用户输入的用户名密码
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String)token.getPrincipal();
        User user = testService.queryInfoByUsername(userName);

        if(user != null){
            //盐值加密
            ByteSource salt = ByteSource.Util.bytes(user.getUserName());
            //数据库目前存的是明文，所以用md5加密转换
            SimpleHash sh = new SimpleHash("md5",user.getPassword(),salt,2);
            //SimpleAuthenticationInfo   salt是给AuthenticationToken中token用的，也就是前端用户输入的密码
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),sh,salt,"xx");
            return authcInfo;
        }
        return null;
    }
}
