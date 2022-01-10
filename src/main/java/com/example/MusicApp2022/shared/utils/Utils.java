package com.example.MusicApp2022.shared.utils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.MusicApp2022.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utils {
	
	 private final Random RANDOM = new SecureRandom();
	    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
	    // generate User id
	    public String generateUserId(int length) {
	        return generateRandomString(length);
	    }
	    
	    //  generate Address id
	    public String generateAddressId(int length) {
	        return generateRandomString(length);
	    }
	    
	    // generate City id 
	    public String generateCityId(int length) {
			return generateRandomString(length);
		}

	    
	    // generate Festival id
		public String generateFestivalId(int length) {
			return generateRandomString(length);
		}
		
		// generate Reservation id
		public String generateReservationId(int length) {
			return generateRandomString(length);
		}
	
	    
	   
	    // generate RANDOM String
	    private String generateRandomString(int length) {
	        StringBuilder returnValue = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	        }

	        return new String(returnValue);
	        
	    }
	    
	 // check if token has expired
		public static boolean hasTokenExpired(String token) {

			boolean returnValue = false;

			try {

				Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token)
						.getBody();

				Date tokenExpirationDate = claims.getExpiration();
				Date todayDate = new Date();

				returnValue = tokenExpirationDate.before(todayDate);

			} catch (ExpiredJwtException ex) {

				returnValue = true;
			}

			return returnValue;
		}

		// generate Email Verification Token
		public String generateEmailVerificationToken(String userId) {
			String token = Jwts.builder().setSubject(userId)
					.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
			return token;

		}
        
		public String generatePasswordResetToken(String userId) {

			String token = Jwts.builder().setSubject(userId)
					.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.PASSWORD_RESET_EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
			return token;

		}


}
