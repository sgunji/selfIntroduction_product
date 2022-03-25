package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class csvUpload {

    long id;

    String name;

    String age;

    String gender;

    String address;

    String hobby;

    String introduction;
    
    String updateDate;
    
    String createDate;
}
