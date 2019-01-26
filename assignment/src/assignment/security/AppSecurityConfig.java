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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	private static String REALM = "MY_TEST_REALM";
	
	@Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
	
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
        .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and()
        .formLogin().loginPage("/")
        .loginProcessingUrl("/loginForm")
        .permitAll()
        .defaultSuccessUrl("/services", true);
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