package shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by HSH on 2017/6/29.
 */
public class TestShiro {
    private static Logger log = Logger.getLogger(TestShiro.class);
    @Test
    public  void main(){
        //1.获取安全管理器
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager= factory.getInstance();
       //2.设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到subject对象
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        if (currentUser.isAuthenticated() == false) {
            UsernamePasswordToken token = new UsernamePasswordToken("root","secret1");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                log.info("登录成功");
            } catch (UnknownAccountException e) {
                log.info("用户名不存在");
            } catch (IncorrectCredentialsException e) {
                log.info("密码错误");
            } catch (LockedAccountException e) {
                log.info("已登录一个用户");
            }
        }
    }

}
