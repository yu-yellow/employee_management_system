package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Company;
import utils.DBUtil;

public class CompanyValidator {
    public static List<String> validate(Company c) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(c.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String address_error = _validateAddress(c.getAddress());
        if(!address_error.equals("")) {
            errors.add(address_error);
        }


        return errors;
    }

    // 会社名
    private static String _validateName(String name) {
        // 必須入力チェック
        if(name == null || name.equals("")) {
            return "会社名を入力してください。";
        }

        // すでに登録されている従業員番号との重複チェック
        EntityManager em = DBUtil.createEntityManager();
        long name_count = (long)em.createNamedQuery("checkName", Long.class)
                                       .setParameter("name", name)
                                       .getSingleResult();
        em.close();
        if(name_count > 0) {
            return "入力された会社名の情報はすでに存在しています。";
        }

        return "";
    }

    // 住所
    private static String _validateAddress(String address) {
        // 必須入力チェック
        if(address == null || address.equals("")) {
            return "住所を入力してください。";
        }

        // すでに登録されている住所との重複チェック
        EntityManager em = DBUtil.createEntityManager();
        long address_count = (long)em.createNamedQuery("checkAddress", Long.class)
                                       .setParameter("address", address)
                                       .getSingleResult();
        em.close();
        if(address_count > 0) {
            return "住所が重複しています。";
        }

        return "";
    }


}