package com.example.demo.Form;

import lombok.Data;

@Data
public class UserSearchForm {
	
    private int page;

    private String name;
    private String age;
    private String gender;
    
    public void setPage(int page) {
        this.page = page;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
	
}
