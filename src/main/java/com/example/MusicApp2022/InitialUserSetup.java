package com.example.MusicApp2022;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.MusicApp2022.io.entity.AuthorityEntity;
import com.example.MusicApp2022.io.entity.RoleEntity;
import com.example.MusicApp2022.io.entity.UserEntity;
import com.example.MusicApp2022.io.repository.AuthorityRepository;
import com.example.MusicApp2022.io.repository.RoleRepository;
import com.example.MusicApp2022.io.repository.UserRepository;
import com.example.MusicApp2022.shared.utils.Roles;
import java.util.Arrays;
import com.example.MusicApp2022.shared.utils.Utils;



@Component
@Transactional
public class InitialUserSetup {
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("Ready to print");
		
		AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");
		
		createRole(Roles.ROLE_USER.name(),Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(),Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		
        if(roleAdmin == null) return;
        
        UserEntity adminUser = new UserEntity();
        adminUser.setFirstName("James");
        adminUser.setLastName("Bond");
        adminUser.setEmail("bond@gmail.com");
        adminUser.setEmailVerificationStatus(true);
        adminUser.setEmailVerificationToken("891020kkakfs");
        adminUser.setUserId(utils.generateUserId(30));
        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("007"));
        adminUser.setRoles(Arrays.asList(roleAdmin));

        userRepository.save(adminUser);
               
	}
	
	@Transactional
	private AuthorityEntity createAuthority(String name) {		
	AuthorityEntity authority = authorityRepository.findByName(name);
	
		if(authority == null) {	
			authority = new AuthorityEntity(name);
			authorityRepository.save(authority);
				
		}
		return authority;		
	}
	
	@Transactional
	private RoleEntity createRole(
			String name, Collection<AuthorityEntity> authoities) {		
		
		RoleEntity role = roleRepository.findByName(name);
		
		if(role == null) {
			role = new RoleEntity(name);
			role.setAuthorities(authoities);
			roleRepository.save(role);
		}
		
		return role;
		
	}
}
