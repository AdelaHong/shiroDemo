package com.shiro;

import java.util.LinkedHashMap;

/**
 * Created by HSH on 2017/7/6.
 */
public class FilterChainDefinitionMapBuilder {

    /**
     * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种 -->
      authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter -->

     /login? 匹配一个字符，只能匹配 login1  不能匹配login123
     /login* 匹配零个或多个字符，login1  login123都能匹配上
     login/**  匹配路径下零个或多个路径    login/目录下的所以方法
     -->
     <property name="filterChainDefinitionMap" ref="filterChainDefinitionMap"></property>
     <bean id="filterChainDefinitionMapBuilder" class="com.shiro.FilterChainDefinitionMapBuilder"></bean>
     <bean id ="filterChainDefinitionMap" factory-bean="filterChainDefinitionMapBuilder" factory-method="filterChainDefinitionMap">
     * @return
     */
    public LinkedHashMap<String,String> bulidFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        map.put("/login.do ","anon");
        map.put("/logout","logout");
        map.put("/admin.do","roles[admin]");
        map.put("/user.do","roles[user]");
        map.put("/user.do ","roles[admin]");
        map.put("/** ","authc");
        return map;
    }
}
