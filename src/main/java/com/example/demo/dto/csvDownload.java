package com.example.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


@JsonPropertyOrder({"ID", "名前", "年齢","性別","住所","趣味","自己紹介","更新日時","登録日時"})
@Data
public class csvDownload {
  @JsonProperty("ID")
  private long id;
  @JsonProperty("名前")
  private String name;
  @JsonProperty("年齢")
  private String age;
  @JsonProperty("性別")
  private String gender;
  @JsonProperty("住所")
  private String address;
  @JsonProperty("趣味")
  private String hobby;
  @JsonProperty("自己紹介")
  private String introduction;
  @JsonProperty("更新日時")
  @JsonFormat(pattern = "yyyy/MM/dd")
  private Date modified;
  @JsonProperty("登録日時")
  @JsonFormat(pattern = "yyyy/MM/dd")
  private Date register;

  public csvDownload() {}

  public csvDownload(Long id, String name, String age, String gender, String address, String hobby, String introduction, Date modified, Date register) {
      this.id = id;
      this.name = name;
      this.age = age;
      this.gender = gender;
      this.address = address;
      this.hobby = hobby;
      this.introduction = introduction;
      this.modified = modified;
      this.register = register;
  }
}