package com.example.demo.helper;

import org.springframework.data.domain.Page;

import com.example.demo.dto.Pagination;

public class PagenationHelper {
    public static Pagination createPagenation(@SuppressWarnings("rawtypes") Page pageObject) {
    	
        Pagination pagination = new Pagination();
        pagination.setFirst(pageObject.isFirst());
        pagination.setLast(pageObject.isLast());
        pagination.setNumber(pageObject.getNumber());
        pagination.setNumberOfElements(pageObject.getNumberOfElements());
        pagination.setSize(pageObject.getSize());
        pagination.setTotalElements(pageObject.getTotalElements());
        pagination.setTotalPages(pageObject.getTotalPages());
        pagination.setNumberOfElements(pageObject.getNumberOfElements());
        
        return pagination;
    }
}
