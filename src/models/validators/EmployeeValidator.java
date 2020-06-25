package models.validators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.BelongsNum;
import models.Employees;
import models.Password;
import utils.DBUtil;

public class EmployeeValidator {
    public static List<String> validate(Employees e, Password p, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String code_error = _validateCode(e.getCode(), code_duplicate_check_flag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_kanzi_error = _validateName_kanzi(e.getName_kanzi());
        if(!name_kanzi_error.equals("")) {
            errors.add(name_kanzi_error);
        }

        String name_kana_error = _validateName_kana(e.getName_kana());
        if(!name_kana_error.equals("")) {
            errors.add(name_kana_error);
        }

        String password_error = _validatePassword(p.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        String belongs_error = _validateBelongs(e.getBelongs());
        if(!belongs_error.equals("")) {
            errors.add(belongs_error);
        }

        String birthday_at_error = _validateBirthday_at(e.getBirthday_at());
        if(!birthday_at_error.equals("")) {
            errors.add(birthday_at_error);
        }

        String join_at_error = _validateJoin_at(e.getJoin_at());
        if(!join_at_error.equals("")) {
            errors.add(join_at_error);
        }

        return errors;
    }

    // 従業員番号
    private static String _validateCode(String code, Boolean code_duplicate_check_flag) {
        // 必須入力チェック
        if(code == null || code.equals("")) {
            return "従業員番号を入力してください。";
        }

        // すでに登録されている従業員番号との重複チェック
        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long employees_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("code", code)
                                             .getSingleResult();
            em.close();
            if(employees_count > 0) {
                return "入力された従業員番号の情報はすでに存在しています。";
            }
        }

        return "";
    }

    // 氏名(漢字)の必須入力チェック
    private static String _validateName_kanzi(String name_kanzi) {
        if(name_kanzi == null || name_kanzi.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

 // 氏名(かな)の必須入力チェック
    private static String _validateName_kana(String name_kana) {
        if(name_kana == null || name_kana.equals("")) {
            return "ふりがなを入力してください。";
        }

        return "";
    }

 // 入社日の必須入力チェック
    private static String _validateJoin_at(Date join_at) {
        if(join_at == null || join_at.equals("")) {
            return "入社日を入力してください。";
        }

        return "";
    }

 // 生年月日の必須入力チェック
    private static String _validateBirthday_at(Date birthday_at) {
        if(birthday_at == null || birthday_at.equals("")) {
            return "生年月日を入力してください。";
        }

        return "";
    }

 // 所属部署コードの必須入力チェック
    private static String _validateBelongs(BelongsNum belongs) {
        if(belongs == null || belongs.equals("")) {
            return "所属部署を選択してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}