package com.ind.weighing.Indo_weighing.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ind.weighing.Indo_weighing.utils.JWTUtils;

public class JwtAuthorizationTokenFiler extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Value("${jwt.header}")
    private String tokenHeader;
    
    List<AntPathRequestMatcher> permitURLsMatchers;
	    
    @Autowired
    private UserDetailsService userDetailsService;
    
    public  JwtAuthorizationTokenFiler(String permitURLs) {
		super();
		if (permitURLs != null && !permitURLs.isEmpty()){
			permitURLsMatchers = new ArrayList<AntPathRequestMatcher>();
			if (permitURLs.contains(",")){
				for (String permitURL: permitURLs.split(",")){
					permitURLsMatchers.add(new AntPathRequestMatcher(permitURL));
				}
				
			} else {
				permitURLsMatchers.add(new AntPathRequestMatcher(permitURLs));
			}
		}
		
	}
    
    protected boolean hasMatched(HttpServletRequest request){
		if (permitURLsMatchers != null && !permitURLsMatchers.isEmpty()){
			for (AntPathRequestMatcher antPathRequestMatcher: permitURLsMatchers){
				if (antPathRequestMatcher.matches(request)){
					return true;
				}
			}
		}
		return false;
	}
    
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		if ( (!hasMatched(request))){
			
				String authToken = request.getHeader(this.tokenHeader);						
				int statusFlag = jwtUtils.validateToken(authToken);	
				String username = jwtUtils.getUsernameFromToken(authToken);
				System.out.println(username+":::userNAme");
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				System.out.println(userDetails+":::userDetails ");
						if (statusFlag == 200) {
							UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
							authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							//logger.info("authenticated user " + username + ", setting security context");
							SecurityContextHolder.getContext().setAuthentication(authentication);
							chain.doFilter(request, response);
						}else {
							// REMOVE TOKEN From LIVE DATA server
							response.resetBuffer();
							HttpServletResponse hsr = (HttpServletResponse) response;
		
							if (statusFlag == 201) {
								 response.getOutputStream().write("Invalid UserName and Password in Token".getBytes());
								 hsr.setStatus(201);
							}
							else if (statusFlag == 202){
								 response.getOutputStream().write("Token Expired".getBytes());
								 hsr.setStatus(202);
							}
							else if (statusFlag == 203){
								// this user logged in from another machine hence now database is holding new IP address and token is holding old IP address becz of that IP
								// address is not matching in this case we need to
								 response.getOutputStream().write("User logged in from another IP address".getBytes());
								 hsr.setStatus(203);
							}					
						}
				}else{
					HttpServletResponse hsr = (HttpServletResponse) response;
					 response.getOutputStream().write("Unauthorized User".getBytes());
					 hsr.setStatus(401);
				}	
			
			
		} else{
			chain.doFilter(request, response);
		}	    
	}
}
