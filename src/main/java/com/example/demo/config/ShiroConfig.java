package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	// 创建shiroFilter，拦截请求
	@Bean
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		//配置受限资源
		//配置公共资源
		Map<String, String> map = new HashMap<String, String>();
		map.put("/user/wxlogin", "anon");
		map.put("/doc.html/*", "anon");
		map.put("/doc.html", "anon");
		map.put("/wxpay/callback", "anon");
		map.put("/antblockchain/*", "anon");
		map.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;

	}

	@Bean
	public DefaultWebSecurityManager getDefaultSecurityManager(Realm realm, DefaultWebSessionManager defaultWebSessionManager) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 设置realm
		defaultWebSecurityManager.setRealm(realm);
		defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
		return defaultWebSecurityManager;
	}

	@Bean
	public Realm getRealm() {
		return new MiniProgramRealm();
	}
	
	@Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        MiniProgramSessionManager miniProgramSessionManager = new MiniProgramSessionManager();
        return miniProgramSessionManager;
    }

}
