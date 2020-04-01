package com.zlfjw.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * shiro认证及授权过程
 */
public class TestShiro {

    SimpleAccountRealm simple = new SimpleAccountRealm();
    @Before
    public void addUser(){
        simple.addAccount("tom","123456","admin");
    }

    @Test
    public void test01(){
        // 1.构建SecurityManager环境
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(simple);
        // 2.主题提交认证请求
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("tom","123456");
        subject.login(token);
        subject.checkRole("admin");
        System.out.println(subject.isAuthenticated());
    }
}
