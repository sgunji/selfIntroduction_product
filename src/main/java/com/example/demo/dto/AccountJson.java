package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class AccountJson {

	@JsonPropertyOrder({"id", "username", "password", "enabled", "admin"})
	public class SampleData 
	{
	  private Integer id;
	  private String username;
	  private String password;
	  private String enabled;
	  private String admin;
	  
	}
	
}
