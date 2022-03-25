package com.example.demo.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.experimental.Tolerate;
/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "userint")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonPropertyOrder({"id", "name", "age","gender","address","hobby","introduction","update_date","create_date"})
public class User implements Serializable {
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id;
	/**
	 * 名前
	 */
	@JsonProperty("name")
	@Column(name = "name",nullable = true)
	private String name;
	/**
	 * 年齢
	 */
	@JsonProperty("age")
	@Column(name = "age",nullable = true)
	private String age;
	/**
	 * 性別
	 */
	@JsonProperty("gender")
	@Column(name = "gender",nullable = true)
	private String gender;
	/**
	 * 住所
	 */
	@JsonProperty("address")
	@Column(name = "address",nullable = true)
	private String address;
	/**
	 * 趣味
	 */
	@JsonProperty("hobby")
	@Column(name = "hobby",nullable = true)
	private String hobby;
	/**
	 * 自己紹介
	 */
	@JsonProperty("introduction")
	@Column(name = "introduction",nullable = true)
	private String introduction;
	/**
	 * 更新日時
	 */
	@JsonProperty("update_date")
	@Column(name = "update_date")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date updateDate;
	/**
	 * 登録日時
	 */
	@JsonProperty("create_date")
	@Column(name = "create_date")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date createDate;
	
	@Tolerate
	public User (Long id, String name, String age, String gender, String address, String hobby, String introduction, Date updateDate, Date createDate) {
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