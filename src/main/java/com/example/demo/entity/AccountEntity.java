package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name="account")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonPropertyOrder({"id", "username", "password", "enabled", "admin"})
public class AccountEntity {
 
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("ID")
    private Integer id;
 
    @Column(name="username")
	@JsonProperty("名前")
    private String userName;
 
    @Column(name="password")
	@JsonProperty("パスワード")
    @JsonIgnore
    private String password;
 
    @Column(name="enabled")
	@JsonProperty("有効フラグ")
    private Boolean enabled;
 
    @Column(name="admin")
	@JsonProperty("管理者権限")
    private Boolean admin;
}