package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * ユーザー情報 リクエストデータ
 */
@Getter
@Setter
@AllArgsConstructor
public class UserObj  {
    /**
     * 名前
     */
    private String pdfName;
    /**
     * 年齢
     */
    private String pdfAge;
    /**
     * 性別
     */
    private String pdfGender;
    /**
     * 住所
     */
    private String pdfAddress;
    /**
     * 趣味
     */
    private String pdfHobby;
    /**
     * 自己紹介
     */
    private String pdfIntro;
}