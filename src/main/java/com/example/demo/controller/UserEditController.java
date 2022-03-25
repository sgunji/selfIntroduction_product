package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.getter.Gender;
import com.example.demo.getter.Hobby;
import com.example.demo.helper.DownloadHelper;
import com.example.demo.service.UserEditService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class UserEditController {

	/**
	 * ユーザー情報編集 Service
	 */
	@Autowired
	private UserEditService userEditService;
	
	@Autowired
	DownloadHelper downloadHelper;
	/**
	 * ユーザー情報詳細画面を表示
	 * @param id 表示するユーザーID
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@GetMapping("/user/{id}")
	public String displayView(@PathVariable Long id, Model model) {
		User user = userEditService.findById(id);
		model.addAttribute("userData", user);
		return "user/view";
	}

	/**
	 * ユーザー編集画面を表示
	 * @param id 表示するユーザーID
	 * @return ユーザー編集画面
	 */
	@GetMapping("/user/{id}/edit")
	public ModelAndView displayEditSelct(@PathVariable Long id, ModelAndView mnv) {
		User user = userEditService.findById(id);
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
		userUpdateRequest.setId(user.getId());
		userUpdateRequest.setName(user.getName());
		userUpdateRequest.setAge(user.getAge());
		userUpdateRequest.setGender(user.getGender());
		userUpdateRequest.setAddress(user.getAddress());
		userUpdateRequest.setHobby(user.getHobby());
		userUpdateRequest.setIntroduction(user.getIntroduction());
		mnv.addObject("userUpdateRequest", userUpdateRequest);

		//性別ラジオボタン選択状態判定
		List<Gender> genders = new ArrayList<Gender>() {{
			add(Gender.of("radioA", "男性", "男性"));
			add(Gender.of("radioB", "女性", "女性"));
		}};
		mnv.addObject("genders", genders);

		String gender = user.getGender();
		mnv.addObject("selectGender", gender);

		//趣味チェックボックス選択状態判定
		List<Hobby> hobbys = new ArrayList<Hobby>() {{
			add(Hobby.of("checkA", "映画鑑賞", "映画鑑賞"));
			add(Hobby.of("checkB", "読書", "読書"));
			add(Hobby.of("checkC", "買い物", "買い物"));

		}};
		mnv.addObject("hobbys", hobbys);

		String hobby = user.getHobby();

	    mnv.addObject("selectHobby", hobby);

		mnv.setViewName("user/edit");

		return mnv;
	}

	/**
	 * ユーザー更新
	 * @param userRequest リクエストデータ
	 * @return ユーザー情報詳細画面
	 */
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String update(@ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result) {

		// ユーザー情報の更新
		userEditService.update(userUpdateRequest);
		return String.format("redirect:/user/%d", userUpdateRequest.getId());
	}

	/**
	 * ユーザー情報削除
	 * @param id 表示するユーザーID
	 * @return ユーザー情報詳細画面
	 */
	@GetMapping("/user/{id}/delete")
	public String delete(@PathVariable Long id) {
		// ユーザー情報の削除
		userEditService.delete(id);
		return "redirect:/user/userList";
	}
	
	
	/**
	 * PDFダウンロード
	 */
    @GetMapping("user/{id}/pdfDownload")
    String download(HttpServletResponse response, @PathVariable Long id) throws JRException {
    	
        try (OutputStream os = response.getOutputStream();) {
        	
        	userEditService.pdfDownload(id);
        	
        	Path filePath = Paths.get("UserData.pdf");
        	
            byte[] fb1 = Files.readAllBytes(filePath);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=UserData.pdf");
            response.setContentLength(fb1.length);
            os.write(fb1);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
