package assignment.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
    private CustomBasicAuthenticationEntryPoint authenticationEntryPoint;
	
	/*@Bean
	public HttpFirewall allowUrlEncodedPercentHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedPercent(true);
	    return firewall;
	}*/
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
	    .usersByUsernameQuery("select email, password, enabled from user where email=?")
	    .authoritiesByUsernameQuery("select email, role from user where email=?");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    	.authorizeRequests()
        .antMatchers("/services").hasAnyAuthority("0", "1", "2", "4")
        .antMatchers("/publisher/**").hasAuthority("1")
        .antMatchers("/professor/**").hasAuthority("0")
        .antMatchers("/secretariat/**").hasAuthority("2")
        .antMatchers("/admin/**").hasAuthority("4")
        .antMatchers("/student/**").hasAuthority("3")
        .anyRequest().authenticated()
        .and()
        .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .formLogin().loginPage("/")
        .loginProcessingUrl("/loginForm")
        .permitAll()
        .defaultSuccessUrl("/services", true)
        .and()
        .logout().permitAll();
    }
    

	@Override
    public void configure(WebSecurity web) throws Exception {
             web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers(HttpMethod.OPTIONS, "/**");
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}