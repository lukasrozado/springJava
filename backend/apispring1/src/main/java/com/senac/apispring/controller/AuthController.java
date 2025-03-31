package com.senac.apispring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.senac.apispring.services.TokenServices;
import com.senac.apispring.user.DadosLoginUser;
import com.senac.apispring.user.User;
import com.senac.apispring.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenServices tokenServices;

	@Autowired
	private UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody @Valid User user) {
		System.out.print(user);
		User savedUser = userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> efetuarLogin(@RequestBody @Valid DadosLoginUser dados) {
		try {
			if (dados.email() == null || dados.senha() == null) {
				throw new IllegalArgumentException("Email or password cannot be null");
			}

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					dados.email(), dados.senha());
			var authentication = authenticationManager.authenticate(authenticationToken);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			User user = (User) userDetailsService.loadUserByUsername(userDetails.getUsername());

			String token = tokenServices.gerarToken(user);

			Map<String, Object> response = new HashMap<>();
			response.put("message", "Login successful");
			response.put("token", token);

			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", "Invalid input: " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (RuntimeException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", "Authentication failed: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	@PostMapping("/autenticacao/validarToken")
	public ResponseEntity<String> validarToken(HttpServletRequest request) {
		System.out.println("testando autenticacao");
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			boolean isValid = tokenServices.isValid(token.substring(7));
			return isValid ? ResponseEntity.ok("Token válido")
					: ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token inválido");
		}
		return ResponseEntity.badRequest().body("Token não fornecido");
	}

	// Adding the OPTIONS Mapping
	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public ResponseEntity<Void> handleOptions(HttpServletRequest request) {
		return ResponseEntity.ok().header("Allow", "GET, POST, OPTIONS, PUT, DELETE").build();
	}
}
