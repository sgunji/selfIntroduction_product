package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserSearchService;

@RestController
public class restAccount {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private UserSearchService userSearchService;
	
	@Autowired
	HttpSession session;
	
	//その場編集でのデータ更新
    @RequestMapping(value="/user/updateInPlace",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
    public List<User> insert(@RequestBody User user) {
    	userSearchService.update(user);
        return Arrays.asList(user);
    }
	
}
