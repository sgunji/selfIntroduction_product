package com.example.demo.dto;
import java.io.Serializable;

import lombok.Data;
/**
 * ユーザー情報 リクエストデータ
 */
@Data
public class UserRequest implements Serializable {
    /**
     * 名前
     */
    private String name;
    /**
     * 年齢
     */
    private String age;
    /**
     * 性別
     */
    private String gender;
    /**
     * 県
     */
    private Long pref;
    /**
     * 住所
     */
    private String address;
    /**
     * 趣味
     */
    private String hobby;
    /**
     * 自己紹介
     */
    private String introduction;
}