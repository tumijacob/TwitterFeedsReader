package com.matome.test.users.springbootstarter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matome.test.users.springbootstarter.auth.JwtTokenUtil;
import com.matome.test.users.springbootstarter.auth.JwtUser;
import com.matome.test.users.springbootstarter.model.User;
import com.matome.test.users.springbootstarter.service.UserService;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	UserService userService;
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testFinal() {
		return "Success";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		userService.createUser(user);
	}
	
	@RequestMapping(value = "/user/add/{id}", method = RequestMethod.PUT)
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        System.out.println(token);
        return user;
    }
}
