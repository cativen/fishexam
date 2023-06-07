package com.fishexam.controller;

import com.fishexam.util.EmailUtils;
import com.fishexam.util.PasswordLock;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class LoginController {
    @Autowired
    PasswordLock passwordLock;

    @RequestMapping("/toLogin")
    public String logins() {
        return "login/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(String username, String password, Model model,boolean rememberMe) {
        //密码进行MD5加密 次数为三次  盐值为：fish
        String passWordMax = this.passwordLock.passwordLocks(password);
        //读取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登陆数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, passWordMax);
        token.setRememberMe(rememberMe);
        System.out.println(token);
        //执行登录的方法
        try {
            subject.login(token);
            Session session = subject.getSession();
            session.setAttribute("user",username);
            return "success";
        } catch (UnknownAccountException e) {
            //用户名错误异常
            model.addAttribute("errors", "用户名错误");
            System.out.println("用户名错误");
            return "用户名错误";
        } catch (IncorrectCredentialsException ice) {
            //密码错误异常
            model.addAttribute("errors", "密码错误");
            System.out.println("密码错误");
            return "密码错误";
        } catch (LockedAccountException lae) {
            //账号被冻结
            model.addAttribute("errors", "账号被冻结");
            System.out.println("账号被冻结");
            return "账号被冻结";
        }
    }

    public static void main(String[] args) {
        String passWordMax = passwordLocks("catnip");
        System.out.println(passWordMax);
    }

    /**
     * 加密格式
     */
    public static final String algorithmName = "md5";

    /**
     * 密码加密
     *
     * @param password 明文密码
     * @return 经过加密后的密码
     */
    public static String passwordLocks(String password) {
        // md5加密方法,salt为盐值,hashIterations为加密次数
        return String.valueOf(new SimpleHash(algorithmName, password, "fish", 3));
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized() {
        return "未经授权无法访问";
    }



    @RequestMapping("/logout")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login/login";
    }

}
