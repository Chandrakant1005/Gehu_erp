package com.danu.gehu_erp;

public class profile_data {
    private String profileImageUrl;
    public String _fname;
    private String email;
    private String phone;
    private String course;
    private String specialization;
    private String year;
    private String semester;


    public profile_data(){};

    public profile_data(String ImageUrl,String fname,String email, String phone, String course, String specialization, String year, String semester) {
        this.profileImageUrl = ImageUrl;
        this._fname = fname;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.specialization = specialization;
        this.year = year;
        this.semester = semester;
    }

    //getters
    public String getProfileImageUrl(){return profileImageUrl;}
    public String getFname(){return _fname;}
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

    //setters
    public void setProfileImageUrl(String url){this.profileImageUrl = url;}
    public void set_fname(String f_name) {
        this._fname = f_name;
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

}
