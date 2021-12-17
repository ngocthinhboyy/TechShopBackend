package com.techshopbe.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.AuthenticationDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public Object login(@RequestBody AuthenticationDTO authenDTO) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenDTO.getEmail(), authenDTO.getPswd()));
			if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
				throw new BadCredentialsException(null);
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
			Date dateNow = new Date();
			 
			String token = Jwts.builder()
					.setSubject(authenDTO.getEmail())
					.setIssuedAt(dateNow)
					.setExpiration(new Date(dateNow.getTime() + 864000000L))
					.signWith(SignatureAlgorithm.HS512, "ngocthinh")
					.compact();
			UserDTO user = userService.getByEmail(authenDTO.getEmail());
			user.setAccess_token(token);
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		}
		catch(BadCredentialsException e) {
			return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/admin/login")
	public Object adminLogin(@RequestBody AuthenticationDTO authenDTO) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenDTO.getEmail(), authenDTO.getPswd()));
			if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
				throw new BadCredentialsException(null);
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
			Date dateNow = new Date();
			 
			String token = Jwts.builder()
					.setSubject(authenDTO.getEmail())
					.setIssuedAt(dateNow)
					.setExpiration(new Date(dateNow.getTime() + 864000000L))
					.signWith(SignatureAlgorithm.HS512, "ngocthinh")
					.compact();
			UserDTO user = userService.getByEmail(authenDTO.getEmail());
			user.setAccess_token(token);
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		}
		catch(BadCredentialsException e) {
			return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
