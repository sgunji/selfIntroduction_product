package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.User;

public interface UserSearchDao extends Serializable{
	   public List<User> search(String name, String age, String gender);
	   
	   public Page<User> searchResult(String name, String age, String gender, Pageable pageable);
	   //public Page<User> searchResult(String name, String age, String gender);
}
