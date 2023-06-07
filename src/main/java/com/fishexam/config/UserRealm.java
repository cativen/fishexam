package com.fishexam.config;

import com.fishexam.pojo.AccountPojo;
import com.fishexam.service.PersonUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的UserRealm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    PersonUserService personUserService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=> 授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //拿到user对象
        AccountPojo principal = (AccountPojo) subject.getPrincipal();
        //判断principal.getLevel()中是否带有info.addStringPermission所需要的信息
        info.addStringPermission(String.valueOf(principal.getLevel()));
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        /**
         *  //获取数据库中的用户名和密码
         String name = "admin";
         String password = "123456";

         UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

         if (!usernamePasswordToken.getUsername().equals(name)){
         System.out.println("================================");
         //return null 会抛出异常 UnknownAccountException
         return null;
         }*/
        System.out.println("usernamePasswordToken.getUsername()=>"+usernamePasswordToken.getUsername());
        String Password = personUserService.selectPasswordByNameEmailPhone(usernamePasswordToken.getUsername());
        if (Password == null) {
            return null;
        }
        //SpringshiroApplication
        /**
         * algorithmName:加密方式
         * source:被加密的密码
         * salt:盐值
         * hashIterations:加密次数
         */
//        String algorithmName = "md5";
//        System.out.println(accountPojo.getPassword());
//        SimpleHash simpleHash = new SimpleHash(algorithmName, accountPojo.getPassword(), "zhuyu", 1);
//        System.out.println(simpleHash);
        //principal:认证的实体信息 可以是username 也可以是数据表对应用户的实体类对象
        //credentials:密码
        //realmName:当前realm 对象的name 调用父类的getName()方法即可
        return new SimpleAuthenticationInfo("", Password, "");
    }
}
