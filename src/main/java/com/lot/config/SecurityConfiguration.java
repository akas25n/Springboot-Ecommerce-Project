package com.lot.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    
    @Autowired
	private UserAuthenticationSuccessHandler successHandler;

 // @Value("${spring.queries.users-query}")
    private String usersQuery="select email, password, enabled from user where email=?";

   //@Value("${spring.queries.roles-query}")
    private String rolesQuery="select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
        authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/my/account/**").permitAll()
        .antMatchers("/lot/login").permitAll()
        .antMatchers("/articles/**").permitAll()
        .antMatchers("/lot/registration").permitAll()
        
        //----------------------------------------------------------------------------------------------------changed
        .antMatchers("/confirm").permitAll()
      //----------------------------------------------------------------------------------------------------changed
        .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
        .authenticated().and().csrf().disable().formLogin()
       
        .loginPage("/lot/login").failureUrl("/lot/login?error=true")
        .successHandler(successHandler)
        //.defaultSuccessUrl("/lot/home")
       
        .usernameParameter("email")
        .passwordParameter("password")
        .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/").and().exceptionHandling()
        .accessDeniedPage("/access-denied");
    }
    
    

    
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}

/*
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserAuthenticationSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

 // @Value("${spring.queries.users-query}")
    private String usersQuery = "SELECT email, password, enabled FROM user WHERE email = ?";

    //@Value("${spring.queries.roles-query}")
    private String rolesQuery = "SELECT u.email, r.role FROM user u INNER JOIN user_role ur ON(u.user_id=ur.user_id) INNER JOIN role r ON(ur.role_id = r.role_id) WHERE u.email = ?";


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
        
//        auth.inMemoryAuthentication()
//        	.withUser("user").password("password").roles("USER")
//        	.and()
//        	.withUser("admin").password("password").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/lot/login").permitAll()
                .antMatchers("/articles/**").permitAll()
                .antMatchers("/lot/registration").permitAll()
                
                //----------------------------------------------------------------------------------------------------changed
                .antMatchers("/confirm").permitAll()
              //----------------------------------------------------------------------------------------------------changed
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/lot/login").failureUrl("/lot/login?error=true")
                .successHandler(successHandler)
                //.defaultSuccessUrl("/lot/home")
               
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
*/