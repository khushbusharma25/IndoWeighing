package com.ind.weighing.Indo_weighing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ind.weighing.Indo_weighing.service.UserService;
import com.ind.weighing.Indo_weighing.utils.HttpAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
    private final static String permitURLS = "/,/**/*.png,/**/*.jpg,/**/*.jpeg,/static/**,/resources/**,/login,/auth,/getAllProducts,/save,/assets/**,*.jsp,*.htm,/**/*.htm,/**/*.jsp,/**/*.css,/**/*.js,/**/*.woff2,/**/*.woff,/favicon.ico";
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    	//auth.authenticationProvider(daoAuthenticationProvider());//user when loginSuccessHandler & loginFailureHandler are created
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authenticationProvider;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public JwtAuthorizationTokenFiler authenticationTokenFilterBean() throws Exception {
        return new JwtAuthorizationTokenFiler(permitURLS);
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
    	// we don't need CSRF because our token is invulnerable
    	.csrf().disable()
    	.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
    	// don't create session
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    	.authorizeRequests()
    	.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
    	.antMatchers(
    			HttpMethod.GET,
    			"/",
    			"/**/*.png",
    			"/**/*.jpg",
    			"/**/*.jpeg",
    			"*.jsp",
    			"*.htm",
    			"/**/*.css",
    			"/**/*.htm",
    			"/**/*.js",
    			"/**/*.woff2",
    			"/**/*.woff",
    			"/favicon.ico"
    			).permitAll()
    	.antMatchers("/save").permitAll()
    	.antMatchers("/").permitAll()
    	.antMatchers("/auth/**").permitAll()
    	.antMatchers("/getAllProducts").permitAll()
    	.antMatchers("/static/**").permitAll()
    	.antMatchers("/resources/**").permitAll()
    	.anyRequest().authenticated()
    	// Custom JWT based security filter
    	.and().addFilterBefore(
    			authenticationTokenFilterBean(),
    			UsernamePasswordAuthenticationFilter.class);
    	// disable page caching
    	httpSecurity.headers().cacheControl();        
		
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/resources/**");
    }            
    
}
