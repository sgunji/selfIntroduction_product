package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 都道府県マスタ Entity
 */
@Entity
@Data
@Table(name = "prefectures")
public class Prefectures {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 都道府県名
     */
    @Column(name = "name")
    private String name;
    /**
     * 都道府県カナ
     */
    @Column(name = "name_kana")
    private String name_kana;
}
