package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserListSearchService {
    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;
    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    public Page<User> searchAll(Pageable pageable) {
    	return userRepository.findAll(pageable);
    }
    
    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    public List<User> search(String name, String age, String gender) {
    	return userRepository.findAll();
    }
}
