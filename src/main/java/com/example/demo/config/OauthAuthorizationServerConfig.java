package com.example.demo.config;

import com.example.demo.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OauthAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 这个地方需要注意一下，这个bean必须自行创建出来，参照WebSecurityConfig类
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailService userDetailsService;

    /**
     * 配置访问端点和令牌服务
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 注意这个allowFormAuthenticationForClients，开启表单验证
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 用来配置客户端详情服务
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // client信息，可以存储数据库，可以redis，也可以存储内存，看你心情
        clients.inMemory()
                .withClient("system")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .scopes("app");
    }

    /**
     * 配置token
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 直接存储内存
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .authenticationManager(authenticationManager)
                .tokenStore(new InMemoryTokenStore())
                .userDetailsService(userDetailsService);

    }
}
