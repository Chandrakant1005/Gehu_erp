package com.danu.gehu_erp;

public class profile_data {
    private boolean saved_or_not=false;
    private String f_name;
    private String l_name;
    private String email;
    private String phone;
    private String course;
    private String specialization;
    private String year;
    private String semester;

    public profile_data(){};

    public profile_data(String f_name, String l_name, String email, String phone, String course, String specialization, String year, String semester, boolean saved_or_not) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.specialization = specialization;
        this.year = year;
        this.semester = semester;
        this.saved_or_not = saved_or_not;

    }

    public String getf_name() {
        return f_name;
    }
    public String getl_name() {
        return l_name;
    }
    public String get_email() {
        return email;
    }
    public String get_course() {
        return course;
    }
    public String get_year() {
        return year;
    }
    public String get_specialization() {
        return specialization;
    }
    public String get_semester() {
        return semester;
    }
    public String get_phone() {
        return phone;
    }
    public boolean get_saved_or_not() {
        return saved_or_not;
    }
    public void setf_name(String f_name) {
        this.f_name = f_name;
    }
    public void setl_name(String l_name) {
        this.l_name = l_name;
    }
    public void set_email(String email) {
        this.email = email;
    }
    public void set_course(String course) {
        this.course = course;
    }
    public void set_year(String year) {
        this.year = year;
        }
    public void set_specialization(String specialization) {
        this.specialization = specialization;
    }
    public void set_semester(String semester) {
        this.semester = semester;
    }
    public void set_phone(String phone) {
        this.phone = phone;
    }
    public void set_saved_or_not(boolean saved_or_not) {
        this.saved_or_not = saved_or_not;
    }
}
