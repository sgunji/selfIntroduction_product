package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "userint")
@JsonPropertyOrder({"ID", "名前", "年齢","性別","住所","趣味","自己紹介","更新日時","登録日時"})
public class CsvUser {
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer id;
	/**
	 * 名前
	 */
	@JsonProperty("名前")
	@Column(name = "name",nullable = true)
	private String name;
	/**
	 * 年齢
	 */
	@JsonProperty("年齢")
	@Column(name = "age",nullable = true)
	private String age;
	/**
	 * 性別
	 */
	@JsonProperty("性別")
	@Column(name = "gender",nullable = true)
	private String gender;
	/**
	 * 住所
	 */
	@JsonProperty("住所")
	@Column(name = "address",nullable = true)
	private String address;
	/**
	 * 趣味
	 */
	@JsonProperty("趣味")
	@Column(name = "hobby",nullable = true)
	private String hobby;
	/**
	 * 自己紹介
	 */
	@JsonProperty("自己紹介")
	@Column(name = "introduction",nullable = true)
	private String introduction;
	/**
	 * 更新日時
	 */
	@JsonProperty("更新日時")
	@Column(name = "update_date")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date updateDate;
	/**
	 * 登録日時
	 */
	@JsonProperty("登録日時")
	@Column(name = "create_date")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date createDate;

	public CsvUser (Integer id, String name, String age, String gender, String address, String hobby, String introduction, Date updateDate, Date createDate) {
	      this.id = id;
	      this.name = name;
	      this.age = age;
	      this.gender = gender;
	      this.address = address;
	      this.hobby = hobby;
	      this.introduction = introduction;
	      this.updateDate = updateDate;
	      this.createDate = createDate;
	}

}
