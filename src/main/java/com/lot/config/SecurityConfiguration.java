package com.lot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Autowired
	private UserAuthenticationSuccessHandler successHandler;

	// @Value("${spring.queries.users-query}")
	private String usersQuery = "select email, password, enabled from user where email=?";

	// @Value("${spring.queries.roles-query}")
	private String rolesQuery = "select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/my/account/**").permitAll()
				.antMatchers("/lot/login").permitAll().antMatchers("/articles/**").permitAll()
				.antMatchers("/lot/registration").permitAll().antMatchers("/reset/password").permitAll()

				// ----------------------------------------------------------------------------------------------------changed
				.antMatchers("/confirm").permitAll()
				// ----------------------------------------------------------------------------------------------------changed
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated().and().csrf().disable()
				.formLogin()

				.loginPage("/lot/login").failureUrl("/lot/login?error=true").successHandler(successHandler)
				// .defaultSuccessUrl("/lot/home")

				.usernameParameter("email").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
