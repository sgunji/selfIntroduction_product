package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserSearchDao;
import com.example.demo.entity.User;

@Repository
public class UserSearchDaoImpl implements UserSearchDao {
    //Entityを利用するために必要な機能を提供する
    @Autowired
    private EntityManager entityManager;

    public UserSearchDaoImpl() {
        super();
    }

    public UserSearchDaoImpl(EntityManager manager) {
        this();
        entityManager = manager;
    }

    //Daoクラスで用意したsearchメソッドをオーバーライドする
    @SuppressWarnings("unchecked")
    @Override
    public List<User> search(String name, String age, String gender) {

        //StringBuilderでSQL文を連結する
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b From User b WHERE ");

        boolean nameFlg  = false;
        boolean ageFlg = false;
        boolean genderFlg  = false;
        boolean andFlg    = false;

        //genreがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(name)) {
            sql.append("b.name LIKE :name");
            nameFlg = true;
            andFlg   = true;
        }

        //authorがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(age)) {
            if (andFlg) sql.append(" AND ");
            sql.append("b.age LIKE :age");
            ageFlg = true;
            andFlg    = true;
        }

        //titleがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(gender)) {
            if (andFlg) sql.append(" AND ");
            sql.append("b.gender LIKE :gender");
            genderFlg = true;
            andFlg   = true;
        }

        /*
        QueryはSQLでデータを問い合わせるためのクエリ文に相当する機能を持つ
        entityManagerのcreateQueryメソッドを使用する
        sql変数を引数に渡す
        */
        Query query = entityManager.createQuery(sql.toString());

        //上記のif文でtrueになった場合は、各変数に値をセットする
        //今回、あいまい検索したいのでlike句を使用する
        if (nameFlg) query.setParameter("name", "%" + name + "%");
        if (ageFlg) query.setParameter("age", "%" + age + "%");
        if (genderFlg) query.setParameter("gender", "%" + gender + "%");
        return query.getResultList();
    }
    //Daoクラスで用意したsearchメソッドをオーバーライドする
    @SuppressWarnings("unchecked")
    @Override
    public Page<User> searchResult(String name, String age, String gender, Pageable pageable) {

        //StringBuilderでSQL文を連結する
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b From User b WHERE ");

        boolean nameFlg  = false;
        boolean ageFlg = false;
        boolean genderFlg  = false;
        boolean andFlg    = false;

        //genreがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(name)) {
            sql.append("b.name LIKE :name");
            nameFlg = true;
            andFlg   = true;
        }

        //authorがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(age)) {
            if (andFlg) sql.append(" AND ");
            sql.append("b.age LIKE :age");
            ageFlg = true;
            andFlg    = true;
        }

        //titleがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(gender)) {
            if (andFlg) sql.append(" AND ");
            sql.append("b.gender LIKE :gender");
            genderFlg = true;
            andFlg   = true;
        }

        /*
        QueryはSQLでデータを問い合わせるためのクエリ文に相当する機能を持つ
        entityManagerのcreateQueryメソッドを使用する
        sql変数を引数に渡す
        */
        Query query = entityManager.createQuery(sql.toString());
        List<User> resBefore = query.getResultList();
        Page<User> result = new PageImpl<User>(resBefore);

        //上記のif文でtrueになった場合は、各変数に値をセットする
        //今回、あいまい検索したいのでlike句を使用する
        if (nameFlg) query.setParameter("name", "%" + name + "%");
        if (ageFlg) query.setParameter("age", "%" + age + "%");
        if (genderFlg) query.setParameter("gender", "%" + gender + "%");
        return result;
    }
}
