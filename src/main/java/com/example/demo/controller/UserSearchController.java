package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Form.UserSearchForm;
import com.example.demo.dto.CSV;
import com.example.demo.dto.csvUpload;
import com.example.demo.entity.User;
import com.example.demo.helper.DownloadHelper;
import com.example.demo.helper.PagenationHelper;
import com.example.demo.service.UserSearchService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Controller
@SessionAttributes(value = "form")
public class UserSearchController {

	@Autowired
	private UserSearchService userSearchService;

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	DownloadHelper downloadHelper;

	@Autowired
	HttpSession session;

	private static final String SESSION_FORM_ID="userSearchForm";

	//全表示 or 検索の判定用
	boolean isAllOrSearch;

	//一覧表示処理
	@RequestMapping(value = "/user/userList", method = RequestMethod.GET)
	public String index(Model model,@ModelAttribute("formModel") User user, @PageableDefault(page = 0, size = 20) Pageable pageable) {

		model.addAttribute("msg", "ユーザー検索");
		model.addAttribute("msg2", "検索条件を入力してください");
		//        List<User> users = userSearchService.findAll();
		//        model.addAttribute("users", users);

		Page<User> users = userSearchService.searchAll(pageable);
		model.addAttribute("userlist", users);
		model.addAttribute("users", users.getContent());
		//		model.addAttribute("page", page);

		return "user/userList";
	}

	/**
	 * CsvMapperで、csvを作成する。
	 * @return csv(String)
	 * @throws JsonProcessingException
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public String getCsvText(@ModelAttribute("csvModel") CSV user, Model model) throws JsonProcessingException {

		CsvMapper mapper = new CsvMapper();
		//文字列にダブルクオートをつける
		mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);
		//ヘッダをつける
		CsvSchema schema = mapper.schemaFor(User.class).withHeader();

		List<User> users = userSearchService.searchUserList(user.getName(),user.getAge(), user.getGender());

		return mapper.writer(schema).writeValueAsString(users);
	}

	//CSVファイルで取得した値のダブルクォーテーションの削除
	public static String trimDoubleQuot(String str) {
		char c = '"';
		if(str == "") {
			return "";
		}else if(str.charAt(0) == c && str.charAt(str.length()-1) == c){
			return str.substring(1, str.length()-1);
		} else {
			return str;
		}
	}

	//DATE型変換
	public static Date stringToDate(String n) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		// Date型変換
		Date formatDate = sdf.parse(n);

		return formatDate;

	}

	//csvアップロード
	@PostMapping(value = "/", params = "upload_file")
	public String uploadFile(@RequestParam("file") MultipartFile uploadFile, User user) throws ParseException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(uploadFile.getInputStream(), StandardCharsets.UTF_8))){
			String line;
			while ((line = br.readLine()) != null) {
				Date now = new Date();
				final String[] split = line.split(",");
				
				//IDが空の時は置き換え
				int splitId;
				if(split[0].equals("")) {
					splitId = Integer.parseInt("0");
				} else {
					splitId = Integer.parseInt(split[0]);
				}
				final csvUpload upload = csvUpload.builder().id(splitId).name(split[1]).age(split[2]).gender(split[3]).address(split[4])
						.hobby(split[5]).introduction(split[6]).updateDate(split[7]).createDate(split[8]).build();
				user.setId(upload.getId());
				user.setName(trimDoubleQuot(upload.getName()));
				user.setAge(trimDoubleQuot(upload.getAge()));
				user.setGender(trimDoubleQuot(upload.getGender()));
				user.setAddress(trimDoubleQuot(upload.getAddress()));
				user.setHobby(trimDoubleQuot(upload.getHobby()));
				user.setIntroduction(trimDoubleQuot(upload.getIntroduction()));
				user.setUpdateDate(now);

				//編集データで登録日がある場合は値を残す処理
				String crDate = trimDoubleQuot(upload.getCreateDate());
				if(crDate.isEmpty()) {
					//新規データの場合は現在日付を入れる
					user.setCreateDate(now);
				} else {
					//編集データで登録日がある場合は値を残す
					Date createDate = stringToDate(crDate);
					user.setCreateDate(createDate);
				}

				userSearchService.save(user);
			}
		} catch (IOException e) {
			throw new RuntimeException("ファイルが読み込めません", e);
		}

		return "redirect:/user/userList";
	}

	/**
	 * csvをダウンロードする。
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download/usercsv", method = RequestMethod.POST)
	public ResponseEntity<byte[]> download(@ModelAttribute("csvModel") CSV user, Model model) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		downloadHelper.addContentDisposition(headers, "登録一覧.csv");
		return new ResponseEntity<>(getCsvText(user, model).getBytes("MS932"), headers, HttpStatus.OK);
	}
	
	//ページング処理
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView get(Model model, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        return search(new UserSearchForm(), model, pageable);
    }

	@RequestMapping(value="/user/pagenate", method = RequestMethod.GET)
	public ModelAndView pagenate(
			@RequestParam("page") int page,
			Model model,@PageableDefault(page = 0, size = 20) Pageable pageable, @ModelAttribute("formModel") UserSearchForm user) {
		UserSearchForm form = (UserSearchForm)session.getAttribute(SESSION_FORM_ID);
		form.setPage(page);
		return search(form, model, pageable);
	}
	
	@RequestMapping(value="/user/userListResult", method = RequestMethod.POST)
	public ModelAndView search(
			@ModelAttribute("formModel") UserSearchForm user, Model model, @PageableDefault(page = 0, size = 20) Pageable pageable) {
		session.setAttribute(SESSION_FORM_ID, user);
		
		model.addAttribute("msg", "ユーザー検索");
		model.addAttribute("msg2", "検索条件を入力してください");

		isAllOrSearch = false;

		Page<User> result = userSearchService.searchUserPage(user.getName(),user.getAge(), user.getGender(), pageable);
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("user/userListResult");
		mv.addObject("isAllOrSearch",isAllOrSearch);
		mv.addObject("form",user);
		mv.addObject("userlist",result);
		mv.addObject("users",result.getContent());
		mv.addObject("page",PagenationHelper.createPagenation(result));
		return mv;
	}

}
