package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.specification.UserSpecification;

@Service
public class UserSearchService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserSpecification userSpecification;

	private static final int PAGE_SIZE=20;

	/**
	 * ユーザー情報 ページ表示処理
	 * @return 検索結果
	 */
	public Page<User> searchAll(Pageable pageable) {
		return userRepository.findAllByOrderByUpdateDateDesc(pageable);
	}

	//全件検索
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	//保存
	public User save(User User) {
		return userRepository.saveAndFlush(User);
	}

	//検索
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	//Pageable
	public Page<User> searchUser(int page, String name, String age, String gender){

		// 検索条件を配列に
		String[] keyWordArray = {name, age, gender};

		//todo　ここのisBlankでのnullチェックが効いてない。nullがfalseになる。
		//nullか空文字なら全検索　１語ならWhere句追加　２語以上ならAnd句追加にしたい。
		if(keyWordArray.length == 0) {
			return userRepository.findAll(PageRequest.of(page<=0?0:page, PAGE_SIZE));

		}else if(keyWordArray.length == 1) {
			// 「Select * From account Where (name LIKE '%keyWordArray[0]%' OR age = '%keyWordArray[0]%')
			return userRepository.findAll(Specification
					.where(userSpecification.nameLike(keyWordArray[0])
							.or(userSpecification.ageEqual(keyWordArray[0]))), PageRequest.of(page<=0?0:page, PAGE_SIZE));

		}else {
			Specification<User> specification =
					Specification.where(userSpecification.nameLike(keyWordArray[0])
							.or(userSpecification.ageEqual(keyWordArray[0])).or(userSpecification.genderEqual(keyWordArray[0])));

			// 「Select * From account Where(name LIKE '%keyWordArray[0]%' OR age = '%keyWordArray[0]%') AND(name LIKE '%keyWordArray[i]%' OR age = '%keyWordArray[i]%') AND ・・・
			for(int i = 1; i < keyWordArray.length; i++) {
				specification = specification.and(userSpecification.nameLike(keyWordArray[i])
						.or(userSpecification.ageEqual(keyWordArray[i])).or(userSpecification.genderEqual(keyWordArray[i])));
			}
			return userRepository.findAll(specification, PageRequest.of(page<=0?0:page, PAGE_SIZE));
		}
	}

	//Pageable
	public Page<User> searchUserPage(String name, String age, String gender, Pageable pageable){

		// 検索条件を配列に
		String[] keyWordArray = {name, age, gender};

		//todo　ここのisBlankでのnullチェックが効いてない。nullがfalseになる。
		//nullか空文字なら全検索　１語ならWhere句追加　２語以上ならAnd句追加にしたい。
		if(keyWordArray.length == 0) {
			return userRepository.findAll(pageable);

		}else if(keyWordArray.length == 1) {
			// 「Select * From account Where (name LIKE '%keyWordArray[0]%' OR age = '%keyWordArray[0]%')
			return userRepository.findAll(Specification
					.where(userSpecification.nameLike(keyWordArray[0])
							.or(userSpecification.ageEqual(keyWordArray[0]))), pageable);

		}else {
			Specification<User> specification =
					Specification.where(userSpecification.nameLike(keyWordArray[0])
							.or(userSpecification.ageEqual(keyWordArray[0])).or(userSpecification.genderEqual(keyWordArray[0])));

			// 「Select * From account Where(name LIKE '%keyWordArray[0]%' OR age = '%keyWordArray[0]%') AND(name LIKE '%keyWordArray[i]%' OR age = '%keyWordArray[i]%') AND ・・・
			for(int i = 1; i < keyWordArray.length; i++) {
				specification = specification.and(userSpecification.nameLike(keyWordArray[i])
						.or(userSpecification.ageEqual(keyWordArray[i])).or(userSpecification.genderEqual(keyWordArray[i])));
			}
			return userRepository.findAll(specification, pageable);
		}
	}

	//
	public List<User> searchUserList(String name, String age, String gender){

		// 検索条件を配列に
		String[] keyWordArray = {name, age, gender};

		//todo　ここのisBlankでのnullチェックが効いてない。nullがfalseになる。
		//nullか空文字なら全検索　１語ならWhere句追加　２語以上ならAnd句追加にしたい。
		if(keyWordArray.length == 0) {
			return userRepository.findAll();

		}else if(keyWordArray.length == 1) {
			// 「Select * From account Where (name LIKE '%keyWordArray[0]%' OR age = '%keyWordArray[0]%')
			return userRepository.findAll(Specification
					.where(userSpecification.nameLike(keyWordArray[0])
							.or(userSpecification.ageEqual(keyWordArray[0]))));

		}else {
			Specification<User> specification =
					Specification.where(userSpecification.nameLike(keyWordArray[0])
							.or(userSpecification.ageEqual(keyWordArray[0])).or(userSpecification.genderEqual(keyWordArray[0])));

			// 「Select * From account Where(name LIKE '%keyWordArray[0]%' OR age = '%keyWordArray[0]%') AND(name LIKE '%keyWordArray[i]%' OR age = '%keyWordArray[i]%') AND ・・・
			for(int i = 1; i < keyWordArray.length; i++) {
				specification = specification.and(userSpecification.nameLike(keyWordArray[i])
						.or(userSpecification.ageEqual(keyWordArray[i])).or(userSpecification.genderEqual(keyWordArray[i])));
			}
			return userRepository.findAll(specification);
		}
	}

	/**
	 * ユーザー情報 更新
	 * @param user ユーザー情報
	 */
	public void update(User userUpdateRequest) {
		User user = findById(userUpdateRequest.getId());
		String getName = user.getName();
		String getAge = user.getAge();
		String getGender = user.getGender();
		String getAddress = user.getAddress();
		String getHobby = user.getHobby();
		String getIntro = user.getIntroduction();

		String reqName = userUpdateRequest.getName();
		String reqAge = userUpdateRequest.getAge();
		String reqGender = userUpdateRequest.getGender();
		String reqAddress = userUpdateRequest.getAddress();
		String reqHobby = userUpdateRequest.getHobby();
		String reqIntro = userUpdateRequest.getIntroduction();

		if(Objects.equals(getName ,reqName) && Objects.equals(getAge, reqAge) && Objects.equals(getGender, reqGender) 
				&& Objects.equals(getAddress, reqAddress) && Objects.equals(getHobby, reqHobby) && Objects.equals(getIntro, reqIntro)) {
			;
		}else {
			user.setName(reqName);
			user.setAge(reqAge);
			user.setGender(reqGender);
			user.setAddress(reqAddress);
			user.setHobby(reqHobby);
			user.setIntroduction(reqIntro);
			user.setUpdateDate(new Date());
			userRepository.save(user);
		}

	}
}
