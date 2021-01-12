package cn.yscme.blog.config.security;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity // 开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${spring.datasource.url}")
	String url;	
	@Value("${spring.datasource.username}")
	String user;	
	@Value("${spring.datasource.password}")
	String password;
	@Resource
	private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Resource
	private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
	@Bean
	public DataSource getDS() {
		// Builder 建造者
		return DataSourceBuilder.create()
				.url(url)
				.username(user)
				.password(password)
				.build();
	}
	//授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// 首页所有人可以访问
        // 其他界面只有对应的角色（权限）才可以访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/insert").permitAll()
                .antMatchers("/blog/listall").permitAll()
                .antMatchers("/blog/**","/user/**").hasAnyRole("USER","ADMIN");
        // 开启自动配置的登录功能
        // 如果没有权限则跳转到 /login 登录页
        // /login?error 重定向到这里表示登录失败 
        //th:href="@{/login}"
        http.formLogin().loginPage("/tologin")
        	.usernameParameter("username").passwordParameter("password")
	        .loginProcessingUrl("/login")
	        .successHandler(myAuthenticationSuccessHandler)
	        .failureHandler(myAuthenticationFailureHandler)
	        .defaultSuccessUrl("/index")
	        .permitAll()
        //开启注销 th:href="@{/logout}" 注销成功后回到首页
        .and().logout().logoutSuccessUrl("/tologin")
        	.permitAll()
        //记住我 <input type="checkbox" name="remember"> 记住我
        .and().rememberMe().rememberMeParameter("remember")
        .and().csrf().disable(); // 关闭 csrf 功能:跨站请求伪造,默认只能通过post方式提交logout请求
    }
    
    // 认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  	
    	auth.jdbcAuthentication()
			.passwordEncoder(new BCryptPasswordEncoder())
			.dataSource(getDS())
			.usersByUsernameQuery("select username,password,state from user where username = ?")
			.authoritiesByUsernameQuery("select username,type from user where username = ?");
    }
}
