package assignment.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
	       .usersByUsernameQuery(
	        "select email, password, enabled from user where email=?")
	       .authoritiesByUsernameQuery(
	        "select email, role from user where email=?");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .anyRequest().authenticated()
        .antMatchers("/services").hasAnyRole("0,1,2,4")
        .antMatchers("/publisher/**").hasRole("1")
        .antMatchers("/professor/**").hasRole("0")
        .antMatchers("/secretariat/**").hasRole("2")
        .antMatchers("/admin/**").hasRole("4")
        .and()
        .formLogin().loginPage("/")
        .loginProcessingUrl("/loginForm")
        .permitAll()
        .defaultSuccessUrl("/services", true)
        .and()
        .exceptionHandling()
        .accessDeniedPage("/403");
    }
    

	@Override
    public void configure(WebSecurity web) throws Exception {
             web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/student/**");
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}