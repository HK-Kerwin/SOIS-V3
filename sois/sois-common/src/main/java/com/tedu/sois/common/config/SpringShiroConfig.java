package com.tedu.sois.common.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.LinkedHashMap;

@Configuration
public class SpringShiroConfig {
    /**
     * 会话Session配置实现
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sManager = new DefaultWebSessionManager();
        sManager.setGlobalSessionTimeout(2 * 60 * 60 * 1000);
        //sManager.setSessionIdUrlRewritingEnabled(false);
        return sManager;
    }


    /**
     * 记住我
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(2 * 60 * 60);
        cManager.setCookie(cookie);
        return cManager;
    }


    /**
     * Shiro缓存配置
     *
     * @return
     */
    @Bean
    public CacheManager shiroCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    /**
     * 配置SecurityManager对象(shiro核心安全管理器对象)
     */
    @Bean("securityManager")
    public SecurityManager securityManager(
            Realm realm, CacheManager shiroCacheManager,
            RememberMeManager rememberMeManager,
            SessionManager sessionManager) {
        DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
        sManager.setRealm(realm);
        sManager.setRememberMeManager(rememberMeManager);
        sManager.setCacheManager(shiroCacheManager);
        sManager.setSessionManager(sessionManager);
        return sManager;
    }

    /**
     * 配置ShiroFilterFactoryBean对象，通过此对象
     * 创建过滤器工厂，并指定过滤规则
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
        ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
        sfBean.setSecurityManager(securityManager);
        //
        sfBean.setLoginUrl("/doLoginUI");
        //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //静态资源允许匿名访问:"anon"
        map.put("/bower_components/**", "anon");
        map.put("/build/**", "anon");
        map.put("/dist/**", "anon");
        map.put("/i18n/**", "anon");
        map.put("/layuiadmin/**", "anon");
        map.put("/logout", "logout");

        map.put("/upload/**", "anon");//头像访问路径
        map.put("/user/avatarChange", "anon");//上传头像

        map.put("/home/**", "anon");//主页
        map.put("/", "anon");
        map.put("/doErrorUI", "anon");
        map.put("/common/*", "anon");

        map.put("/training/*", "anon");
        map.put("/stu/*", "anon");
        map.put("/score/*", "anon");
        map.put("/crm/*", "anon");
        map.put("/user/doLogin", "anon");
        //除了匿名访问的资源,其它都要认证("authc")后访问
        //map.put("/**","authc");
        map.put("/**", "user");//记住我时将authc改为user
        sfBean.setFilterChainDefinitionMap(map);
        return sfBean;
    }
    //===========授权配置============

    /**
     * spring 框架管理此对象时,会基于此对象管理
     * Shiro框架中相关API对象的生命周期
     */
    @Bean //<bean id="" class="">
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 配置此对象的目的是,在spring容器启动时,
     * 扫描所有的advisor(顾问)对象,基于advisor
     * 对象中切入点的描述,为目标对象创建代理对象
     */
//	@DependsOn("lifecycleBeanPostProcessor")
//	@Bean
//	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//		return new DefaultAdvisorAutoProxyCreator();
//	}

    /**
     * 配置Advisor对象,此对象中会对切入点,通知等对象进行
     * 相关描述,后续DefaultAdvisorAutoProxyCreator对象
     * 会基于描述,为目标对象创建代理对象
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor( SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 跨域请求COOKIES携带SessionId
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    /**
     * CORS跨域请求解决403
     *
     * @return
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
}














