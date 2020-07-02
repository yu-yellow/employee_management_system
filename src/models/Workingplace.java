package models;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "workingplace")
@NamedQueries({
    @NamedQuery(
            name = "getWorkingplace",
            query = "SELECT w FROM Workingplace AS w WHERE w.employee.id=:id AND w.delete_flag=0 ORDER BY w.id DESC"
            ),
    @NamedQuery(
            name = "getCount",
            query = "SELECT COUNT(w) FROM Workingplace AS w WHERE w.employee.id=:id AND w.delete_flag=0"
            ),
    @NamedQuery(
            name = "getNowWork",
            query = "SELECT w FROM Workingplace AS w WHERE w.employee.id=:emp_id AND w.end_flag=1"
            )
})

@Entity
public class Workingplace {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name="field_manager", nullable = false, length = 20)
    private String field_manager;

    @Column(name = "open", nullable = false)
    private Time open;

    @Column(name = "close", nullable = false)
    private Time close;

    @Column(name = "breaktime", nullable = false, length = 5)
    private Integer breaktime;

    @Column(name = "start_at", nullable = false)
    private Date start_at;

    @Column(name = "end_at", nullable = false)
    private Date end_at;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "note", nullable = true, length = 200)
    private String note;

    @Column(name = "end_flag", nullable = false)
    private Integer end_flag;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getField_manager() {
        return field_manager;
    }

    public void setField_manager(String field_manager) {
        this.field_manager = field_manager;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    public Integer getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(Integer breaktime) {
        this.breaktime = breaktime;
    }

    public Date getStart_at() {
        return start_at;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getEnd_flag() {
        return end_flag;
    }

    public void setEnd_flag(Integer end_flag) {
        this.end_flag = end_flag;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

}
