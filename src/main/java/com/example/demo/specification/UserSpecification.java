package com.example.demo.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;

@Component
public class UserSpecification {
	/**
	 * 指定文字をユーザー名に含むアカウントを検索する。
	 */
	public Specification<User> nameLike(String name) {

		// 匿名クラス
		return new Specification<User>() {
			//CriteriaAPI
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 「name LIKE '%name%'」を追加
				return cb.like(root.get("name"), "%" + name + "%");
			}
		};
	}
	/**
	 * 指定文字を年齢に含むアカウントを検索する。
	 */
	public Specification<User> ageEqual(String age) {

		return new Specification<User>() {
			//CriteriaAPI
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				// 数値変換可能かのチェック
				try {
					Integer.parseInt(age);

					// 「age = 'age'」を追加
					return cb.equal(root.get("age"), age);

				} catch (NumberFormatException e) {
					return null;
				}
			}
		};
	}
	/**
	 * 指定文字を性別に含むアカウントを検索する。
	 */
	public Specification<User> genderEqual(String gender) {

		// 匿名クラス
		return new Specification<User>() {
			//CriteriaAPI
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 「age = 'age'」を追加
				return cb.equal(root.get("gender"), gender);
			}
		};
	}
}
