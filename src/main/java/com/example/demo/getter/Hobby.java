package com.example.demo.getter;

import lombok.Getter;

@Getter
public class Hobby {
	
	private String id;
	private String value;
	private String label;

	Hobby(String id, String value, String label) {
		this.id = id;
		this.value = value;
		this.label = label;
	}

	public static Hobby of(String id, String value, String label) {
		return new Hobby(id, value, label);
	}
}