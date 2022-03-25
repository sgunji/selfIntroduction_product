package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//テーブル名を「@Table」アノテーションで指定
//「@Data」アノテーションを付与すると、このクラス内の全フィールドに対する
//Getterメソッド・Setterメソッドにアクセスができる
@Entity
@Table(name="user_data")
@Data
public class UserData {

    /** ID */
    //主キー項目に「@Id」を付与し、その値を自動生成するにはアノテーション
    //「@GeneratedValue(strategy = GenerationType.AUTO)」を付与する
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** 名前 */
    private String name;

    /** 生年月日_年 */
    //カラム名を「@Column」アノテーションで指定
    @Column(name="birth_year")
    private int birthY;

    /** 生年月日_月 */
    @Column(name="birth_month")
    private int birthM;

    /** 生年月日_日 */
    @Column(name="birth_day")
    private int birthD;

    /** 性別 */
    private String sex;

    /** メモ */
    private String memo;
}