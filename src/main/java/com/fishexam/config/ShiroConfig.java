package com.fishexam.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    //国际化语言扩展
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        //设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //添加shiro的内置管理器
        /**
         * anon：无需认证也能访问
         * authc：必须认证了才能访问
         * user：必须拥有了记住我才能访问
         * perms：拥有了对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        //设置需要被拦截的请求
        /**
         * linkedHashMap.put("/user/add","authc");
         * linkedHashMap.put("/user/update","authc");
         * */
//        拥有了对某个资源的权限才能访问:perms[1]:需要字段中包含1
//        linkedHashMap.put("/user/add","perms[1]");
//        linkedHashMap.put("/*", "authc");//login
        linkedHashMap.put("/home/*", "authc");
        linkedHashMap.put("/image/*", "anon");//img
        linkedHashMap.put("/css/*", "anon");//css
        linkedHashMap.put("/js/*", "anon");//js
        linkedHashMap.put("/login", "anon");//login
        linkedHashMap.put("/captchaCode", "anon");//login
        linkedHashMap.put("/register", "anon");//login
        linkedHashMap.put("/**","authc");
        //将请求放入map集合当中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //失败页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建realm对象 需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合shiroDialect:用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
