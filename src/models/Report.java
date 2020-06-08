package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "report")
@NamedQueries({
    @NamedQuery(
            name = "getAllReport",
            query = "select r from Report as r order by r.report_id desc"
            ),
    @NamedQuery(
            name = "getReport",
            query = "select r from Report as r where r.report_id = :code"
            )
})

@Entity
public class Report {
    @Id
    @Column(name = "report_id")
    private String report_id;

    @Column(name = "create_at")
    private Timestamp create_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    @Column(name = "report_name")
    private String report_name;

    @Column(name = "create_name")
    private String create_name;


    //getter setter
    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }



}
