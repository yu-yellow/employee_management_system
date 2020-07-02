package models.validators;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import models.Company;
import models.Employees;
import models.Workingplace;

public class WorkingValidator {
    public static List<String> validate(Workingplace w) {
        List<String> errors = new ArrayList<String>();

        String employee_error = _validateEmployee(w.getEmployee());
        if(!employee_error.equals("")) {
            errors.add(employee_error);
        }

        String company_error = _validateCompany(w.getCompany());
        if(!company_error.equals("")) {
            errors.add(company_error);
        }

        String field_manager_error = _validateField_manager(w.getField_manager());
        if(!field_manager_error.equals("")) {
            errors.add(field_manager_error);
        }

        String open_error = _validateOpen(w.getOpen());
        if(!open_error.equals("")) {
            errors.add(open_error);
        }

        String close_error = _validateClose(w.getClose());
        if(!close_error.equals("")) {
            errors.add(close_error);
        }

        String breaktime_error = _validateBreaktime(w.getBreaktime());
        if(!breaktime_error.equals("")) {
            errors.add(breaktime_error);
        }

        String start_at_error = _validateStart_at(w.getStart_at());
        if(!start_at_error.equals("")) {
            errors.add(start_at_error);
        }

        String end_at_error = _validateEnd_at(w.getEnd_at());
        if(!end_at_error.equals("")) {
            errors.add(end_at_error);
        }

        String end_flag_error = _validateEnd_flag(w.getEnd_flag());
        if(!end_flag_error.equals("")) {
            errors.add(end_flag_error);
        }

        String content_error = _validateContent(w.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }


    // 従業員情報の必須入力チェック
    private static String _validateEmployee(Employees employee) {
        if(employee == null || employee.equals("")) {
            return "従業員情報が未入力です。";
        }
        return "";
    }

    // 勤務先情報の必須入力チェック
    private static String _validateCompany(Company company) {
        if(company == null || company.equals("")) {
            return "勤務先会社名を選択してください。";
        }
        return "";
    }

    // 現場責任者の必須入力チェック
    private static String _validateField_manager(String field_manager) {
        if(field_manager == null || field_manager.equals("")) {
            return "現場責任者を入力してください。";
        }
        return "";
    }


    // 始業時間の必須入力チェック
    private static String _validateOpen(Time open) {
        if(open == null || open.equals("")) {
            return "始業時間を入力してください。";
        }
        return "";
    }

    // 終業時間の必須入力チェック
    private static String _validateClose(Time close) {
        if(close == null || close.equals("")) {
            return "終業時間を入力してください。";
        }
        return "";
    }

    // 休憩時間の必須入力チェック
    private static String _validateBreaktime(Integer breaktime) {
        if(breaktime == null || breaktime.equals("")) {
            return "休憩時間を入力してください。";
        }
        return "";
    }

    // 契約開始日の必須入力チェック
    private static String _validateStart_at(Date start_at) {
        if(start_at == null || start_at.equals("")) {
            return "契約開始日を入力してください。";
        }
        return "";
    }

    // 契約終了日の必須入力チェック
    private static String _validateEnd_at(Date end_at) {
        if(end_at == null || end_at.equals("")) {
            return "契約終了日を選択してください。";
        }
        return "";
    }

    // 契約状況の必須入力チェック
    private static String _validateEnd_flag(Integer end_flag) {
        if(end_flag == null || end_flag.equals("")) {
            return "契約状況を入力してください。";
        }
        return "";
    }

    // 業務内容の必須入力チェック
    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "業務内容を入力してください。";
        }
        return "";
    }


}