package com.example.demo.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Prefectures;
import com.example.demo.entity.User;
import com.example.demo.repository.PrefecturesRepository;
import com.example.demo.repository.UserRepository;
/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;
    /**
     * ユーザー情報新規登録
     * @param user ユーザー情報
     */
    public void create(UserRequest userRequest) {
        userRepository.save(CreateUser(userRequest));
    }
    /**
     * ユーザーTBLエンティティの生成
     * @param userRequest ユーザー情報リクエストデータ
     * @return ユーザーTBLエンティティ
     */
    /**
     * 都道府県マスタ Repository
     */
    @Autowired
    private PrefecturesRepository prefecturesRepository;
    
    private User CreateUser(UserRequest userRequest) {
    	//都道府県マスタからIDで県名取得
    	Prefectures prefName = prefecturesRepository.getById(userRequest.getPref());
    	String getHobby = userRequest.getHobby();
    	if(getHobby.contains(",")){
    		getHobby = getHobby.replace(",", "、");
    	}
    	
        Date now = new Date();
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setAddress(prefName.getName() + userRequest.getAddress());
        user.setGender(userRequest.getGender());
        user.setHobby(getHobby);
        user.setIntroduction(userRequest.getIntroduction());
        user.setCreateDate(now);
        user.setUpdateDate(now);
        return user;
    }
    /**
     * 都道府県マスタ 全検索
     * @return 検索結果
     */
    public List<Prefectures> getPrefecturesAll() {
        return prefecturesRepository.findAll();
    }
 
    //都道府県マスタ1件検索
    public Prefectures getById(Long id) {    	
        return prefecturesRepository.getById(id);
    }

}