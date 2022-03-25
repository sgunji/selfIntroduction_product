package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Prefectures;
import com.example.demo.service.UserService;
/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {
	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	UserService userService;
	/**
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String displayAdd(Model model) {
		model.addAttribute("userRequest", new UserRequest());

		//都道府県マスタよりプルダウン用に取得
		List<Prefectures> prefecturesList = userService.getPrefecturesAll();
		model.addAttribute("prefecturesList", prefecturesList);
		// プルダウンの初期値を設定する場合は指定
		model.addAttribute("selectedValue", 1);

		return "user/add";
	}


	/**
	 * ユーザー新規登録
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String create(@ModelAttribute @Validated UserRequest userRequest, BindingResult result, Model model) {

		// ユーザー情報の登録
		userService.create(userRequest);

		//都道府県マスタからIDで県名取得
		Prefectures prefName = userService.getById(userRequest.getPref());

		//POSTした内容を結果画面へ
		//名前
		model.addAttribute("name",userRequest.getName());
		//年齢
		model.addAttribute("age",userRequest.getAge());
		//性別
		model.addAttribute("gender",userRequest.getGender());
		//住所
		model.addAttribute("address",prefName.getName() + userRequest.getAddress());
		//趣味
		model.addAttribute("hobby",userRequest.getHobby());
		//自己紹介
		model.addAttribute("introduction",userRequest.getIntroduction());
		
		return "user/result";
	}
	// 画面表示とエラー時に戻す画面
	@GetMapping("/user/create")
	public String getSignup(@ModelAttribute UserRequest userRequest, Model model) {
		return "user/add";
	}

}