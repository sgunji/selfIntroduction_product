package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.Member;
import com.example.demo.dto.UserRequest;
import com.example.demo.helper.DownloadHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


@Controller
public class CsvController {

    @Autowired
    DownloadHelper downloadHelper;

	/**
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/user/csvTest", method = RequestMethod.GET)
	public String displayAdd(Model model) {
		model.addAttribute("userRequest", new UserRequest());

		return "user/csvTest";
	}


    /**
     * CsvMapperで、csvを作成する。
     * @return csv(String)
     * @throws JsonProcessingException
     */
    public String getCsvText() throws JsonProcessingException {
        CsvMapper mapper = new CsvMapper();
        //文字列にダブルクオートをつける
        mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);
        //ヘッダをつける
        CsvSchema schema = mapper.schemaFor(Member.class).withHeader();
        //メンバーデータをダウンロードするイメージ。本来はDBからデータを取得する。
        List<Member> members = new ArrayList<Member>();
        members.add(new Member(1L, "user01", "プロフィール１", new Date()));
        members.add(new Member(2L, "user02", "プロフィール２", new Date()));
        members.add(new Member(3L, "user03", "プロフィール３", new Date()));
        return mapper.writer(schema).writeValueAsString(members);
    }

    /**
     * csvをダウンロードする。
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download/csv", method = RequestMethod.POST)
    public ResponseEntity<byte[]> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        downloadHelper.addContentDisposition(headers, "日本語ファイル名.csv");
        return new ResponseEntity<>(getCsvText().getBytes("MS932"), headers, HttpStatus.OK);
    }
}