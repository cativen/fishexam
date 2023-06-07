package com.fishexam.pojo;

/**
 * @program: FishExam
 * @description:
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-26 08:41
 **/
public class DoctorPojo {
    private int doctorId;
    private String doctorNumber;
    private String doctorName;
    private String doctorPhoto;
    private String doctorPost;
    private String doctorEmail;
    private String doctorPhone;
    private int doctorAge;
    private int doctorGender;
    private String doctorLevel;
    private String doctorDemo1;
    private String doctorDemo2;
    private String doctorDemo3;

    @Override
    public String toString() {
        return "DoctorPojo{" +
                "doctorId=" + doctorId +
                ", doctorNumber='" + doctorNumber + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorPhoto='" + doctorPhoto + '\'' +
                ", doctorPost='" + doctorPost + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", doctorPhone='" + doctorPhone + '\'' +
                ", doctorAge=" + doctorAge +
                ", doctorGender=" + doctorGender +
                ", doctorLevel='" + doctorLevel + '\'' +
                ", doctorDemo1='" + doctorDemo1 + '\'' +
                ", doctorDemo2='" + doctorDemo2 + '\'' +
                ", doctorDemo3='" + doctorDemo3 + '\'' +
                '}';
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }

    public String getDoctorPost() {
        return doctorPost;
    }

    public void setDoctorPost(String doctorPost) {
        this.doctorPost = doctorPost;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public int getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(int doctorAge) {
        this.doctorAge = doctorAge;
    }

    public int getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(int doctorGender) {
        this.doctorGender = doctorGender;
    }

    public String getDoctorLevel() {
        return doctorLevel;
    }

    public void setDoctorLevel(String doctorLevel) {
        this.doctorLevel = doctorLevel;
    }

    public String getDoctorDemo1() {
        return doctorDemo1;
    }

    public void setDoctorDemo1(String doctorDemo1) {
        this.doctorDemo1 = doctorDemo1;
    }

    public String getDoctorDemo2() {
        return doctorDemo2;
    }

    public void setDoctorDemo2(String doctorDemo2) {
        this.doctorDemo2 = doctorDemo2;
    }

    public String getDoctorDemo3() {
        return doctorDemo3;
    }

    public void setDoctorDemo3(String doctorDemo3) {
        this.doctorDemo3 = doctorDemo3;
    }

    public DoctorPojo(int doctorId, String doctorNumber, String doctorName, String doctorPhoto, String doctorPost, String doctorEmail, String doctorPhone, int doctorAge, int doctorGender, String doctorLevel, String doctorDemo1, String doctorDemo2, String doctorDemo3) {
        this.doctorId = doctorId;
        this.doctorNumber = doctorNumber;
        this.doctorName = doctorName;
        this.doctorPhoto = doctorPhoto;
        this.doctorPost = doctorPost;
        this.doctorEmail = doctorEmail;
        this.doctorPhone = doctorPhone;
        this.doctorAge = doctorAge;
        this.doctorGender = doctorGender;
        this.doctorLevel = doctorLevel;
        this.doctorDemo1 = doctorDemo1;
        this.doctorDemo2 = doctorDemo2;
        this.doctorDemo3 = doctorDemo3;
    }

    public DoctorPojo() {
        super();
    }
}
