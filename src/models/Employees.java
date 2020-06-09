package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "employees")
@NamedQueries({
    @NamedQuery(
            name = "getAllEmployees",
            query = "SELECT e FROM Employees AS e ORDER BY e.id DESC"
            ),
    @NamedQuery(
            name = "getEmployeesCount",
            query = "SELECT COUNT(e) FROM Employees AS e"
            ),
    @NamedQuery(
            name = "checkRegisteredCode",
            query = "SELECT COUNT(e) FROM Employees AS e WHERE e.code=:code"
            ),
    @NamedQuery(
            name = "checkLogin",
            query = "SELECT e FROM Employees AS e WHERE e.delete_flag=0"
            ),
    @NamedQuery(
            name = "searchEmployees",
            query = "SELECT e FROM Employees AS e WHERE e.code=:code OR e.name_kanzi=:name OR e.name_kana=:name OR e.belongs_num=:belongs"
            )
})

@Entity
public class Employees{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name_kanzi", nullable = false)
    private String name_kanzi;

    @Column(name = "name_kana", nullable = false)
    private String name_kana;

    @Column(name ="admin_flag", nullable = false)
    private Integer admin_flag;

    @Column(name = "join_at", nullable = false)
    private Date join_at;

    @Column(name = "leave_at")
    private Date leave_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    @Column(name ="birthday_at", nullable = false)
    private Date birthday_at;

    @Column(name = "belongs_num", nullable = false)
    private String belongs_num;

    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName_kanzi() {
        return name_kanzi;
    }

    public void setName_kanzi(String name_kanzi) {
        this.name_kanzi = name_kanzi;
    }

    public String getName_kana() {
        return name_kana;
    }

    public void setName_kana(String name_kana) {
        this.name_kana = name_kana;
    }

    public Integer getAdmin_flag() {
        return admin_flag;
    }

    public void setAdmin_flag(Integer admin_flag) {
        this.admin_flag = admin_flag;
    }

    public Date getJoin_at() {
        return join_at;
    }

    public void setJoin_at(Date join_at) {
        this.join_at = join_at;
    }

    public Date getLeave_at() {
        return leave_at;
    }

    public void setLeave_at(Date leave_at) {
        this.leave_at = leave_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

    public Date getBirthday_at() {
        return birthday_at;
    }

    public void setBirthday_at(Date birthday_at) {
        this.birthday_at = birthday_at;
    }

    public String getBelongs_num() {
        return belongs_num;
    }

    public void setBelongs_num(String belongs_num) {
        this.belongs_num = belongs_num;
    }


}