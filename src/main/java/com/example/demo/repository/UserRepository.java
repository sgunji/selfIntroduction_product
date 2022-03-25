package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User>{
	//ユーザーリスト全件表示
	public Page<User> findAll(Pageable pageable);
	//降順
	public Page<User> findAllByOrderByUpdateDateDesc(Pageable pageable);
	//検索結果降順
	//public Page<User> findAllByOrderByUpdateDateDesc(Specification<User> specification, Pageable pageable);

	//検索結果ページング
	//public Page<User> findAll(Specification<User> and, Pageable pageable);

}