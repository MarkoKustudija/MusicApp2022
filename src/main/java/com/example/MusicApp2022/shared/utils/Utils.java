package com.example.MusicApp2022.shared.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

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

}
