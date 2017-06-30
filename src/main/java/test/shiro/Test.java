package test.shiro;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by HSH on 2017/6/30.
 */
public class Test {
    private Logger log = Logger.getLogger(Test.class);
    @org.junit.Test
    public void test(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager= factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject current = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lonestarr","vespa");
        if (current.isAuthenticated()) {
            try {
                current.login(token);
                token.setRememberMe(true);
                log.info("登录成功");
            } catch (UnknownAccountException e) {
                log.info("用户名不存在");
            } catch (IncorrectCredentialsException e) {
                log.info("密码错误");
            } catch (LockedAccountException e) {
                log.info("用户名已登录");
            }
        }
        if (current.hasRole("goodguy")) {
            log.info("拥有此角色");
        } else {
            log.info("不拥有此角色");
        }
        if (current.isPermitted("winnebago:drive:eagle5")) {
            log.info("拥有此权限");
        } else {
            log.info("不拥有此权限");
        }
    }
}
