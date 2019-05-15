package com.ind.weighing.Indo_weighing.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ind.weighing.Indo_weighing.domain.UserDTO;
import com.ind.weighing.Indo_weighing.repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JWTUtils implements Serializable{
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_EXPIRED = "exp";
    static final String CLAIM_PASSWORD = "password";
    static final String CLAIM_MAC_ID = "macId";
	
    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";
    
   
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;   

    @Autowired
    private UserRepo userRepo;
    
	 public String generateToken(UserDetails userDetails) {
		 Map<String, Object> claims = new HashMap<>();

		 claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());	
		 claims.put(CLAIM_PASSWORD, userDetails.getPassword());

		 final Date createdDate = new Date();
		 claims.put(CLAIM_KEY_CREATED, createdDate);

		 final String token = doGenerateToken(claims);

		 return token;        
	 }
	 
	 private String doGenerateToken(Map<String, Object> claims) {
		 final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		 final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

//		 log.debug("doGenerateToken " + createdDate);

		 return Jwts.builder()
				 .setClaims(claims)
				 .setExpiration(expirationDate)
				 .signWith(SignatureAlgorithm.HS512, secret)
				 .compact();
	 }
	 
	 private Claims getClaimsFromToken(String token) {
		 Claims claims;
		 try {
			 claims = Jwts.parser()
					 .setSigningKey(secret)
					 .parseClaimsJws(token)
					 .getBody();
		 } catch (Exception e) {
			 claims = null;
		 }
		 return claims;
	 }
		    
	 public String getUsernameFromToken(String token) {
		 String username;
		 try {
			 final Claims claims = getClaimsFromToken(token);
			 username = (String)claims.get(CLAIM_KEY_USERNAME);
		 } catch (Exception e) {
			 username = null;
		 }
		 return username;
	 }
		    
	 public String getPasswordFromToken(String token) {
		 String password;
		 try {
			 final Claims claims = getClaimsFromToken(token);
			 password = (String)claims.get(CLAIM_PASSWORD);
		 } catch (Exception e) {
			 password = null;
		 }
		 return password;
	 }


	 /**
	  * statusFlag = 200 --- Verification done successfully
	  * statusFlag = 201 --- Invalid UserName
	  * statusFlag = 202 --- Token Expired
	  * statusFlag = 203 --- Trying to login using different IP address
	  * @param token
	  * @param userDetails
	  * @param ipAddressFromToken
	  * @return
	  */ 
	 public int validateToken(String token) {
		 int statusFlag=200;

		 String username = getUsernameFromToken(token);
		 String password = getPasswordFromToken(token);

		 if (username != null && password!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			 UserDTO regDetailsList =  userRepo.findByUsernameAndPassword(username, password);
			 if(regDetailsList==null)
				 statusFlag = 201;   
			 else if(isTokenExpired(token)){
				 statusFlag = 202;   
			 }
		 }	    	

		 //log.info("user-" +  user.getUsername() +"--ipAddressFromDb----------"+ipAddressFromDb + "----ipAddressFromToken----" + ipAddressFromToken + "--statusFlag--" + statusFlag);
		 return statusFlag;
	 }

	 private Boolean isTokenExpired(String token) {
		 final Date expiration = getExpirationDateFromToken(token);
		 return expiration.before(new Date());
	 }

	 public Date getExpirationDateFromToken(String token) {
		 Date expiration;
		 try {
			 final Claims claims = getClaimsFromToken(token);
			 expiration = claims.getExpiration();
		 } catch (Exception e) {
			 expiration = null;
		 }
		 return expiration;
	 }
}
